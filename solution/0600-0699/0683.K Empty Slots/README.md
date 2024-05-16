---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0683.K%20Empty%20Slots/README.md
tags:
    - 树状数组
    - 数组
    - 有序集合
    - 滑动窗口
---

<!-- problem:start -->

# [683. K 个关闭的灯泡 🔒](https://leetcode.cn/problems/k-empty-slots)

[English Version](/solution/0600-0699/0683.K%20Empty%20Slots/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>n</code>&nbsp;个灯泡排成一行，编号从 <code>1</code> 到<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;。最初，所有灯泡都关闭。每天&nbsp;<strong>只打开一个</strong>&nbsp;灯泡，直到<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;天后所有灯泡都打开。</p>

<p>给你一个长度为<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;的灯泡数组 <code>blubs</code> ，其中 <code>bulbs[i] = x</code> 意味着在第 <code>(i+1)</code> 天，我们会把在位置 <code>x</code> 的灯泡打开，其中 <code>i</code> <strong>从 0 开始</strong>，<code>x</code> <strong>从 1 开始</strong>。</p>

<p>给你一个整数<meta charset="UTF-8" />&nbsp;<code>k</code>&nbsp;，请返回<em>恰好有两个打开的灯泡，且它们中间 <strong>正好</strong> 有<meta charset="UTF-8" />&nbsp;<code>k</code>&nbsp;个&nbsp;<strong>全部关闭的</strong> 灯泡的 <strong>最小的天数</strong> </em>。<em>如果不存在这种情况，返回 <code>-1</code> 。</em></p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
bulbs = [1,3,2]，k = 1
<b>输出：</b>2
<b>解释：</b>
第一天 bulbs[0] = 1，打开第一个灯泡 [1,0,0]
第二天 bulbs[1] = 3，打开第三个灯泡 [1,0,1]
第三天 bulbs[2] = 2，打开第二个灯泡 [1,1,1]
返回2，因为在第二天，两个打开的灯泡之间恰好有一个关闭的灯泡。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>bulbs = [1,2,3]，k = 1
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == bulbs.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= bulbs[i] &lt;= n</code></li>
	<li><code>bulbs</code> 是一个由从 <code>1</code> 到 <code>n</code> 的数字构成的排列</li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组

我们可以使用树状数组来维护区间和，每一次打开灯泡，我们就在树状数组中更新对应位置的值，然后查询当前位置左边 $k$ 个灯泡是否都是关闭的，并且第 $k+1$ 个灯泡是否已经打开；或者查询当前位置右边 $k$ 个灯泡是否都是关闭的，并且第 $k+1$ 个灯泡是否已经打开。如果满足这两个条件之一，那么就说明当前位置是一个符合要求的位置，我们就可以返回当前的天数。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是灯泡的数量。

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def kEmptySlots(self, bulbs: List[int], k: int) -> int:
        n = len(bulbs)
        tree = BinaryIndexedTree(n)
        vis = [False] * (n + 1)
        for i, x in enumerate(bulbs, 1):
            tree.update(x, 1)
            vis[x] = True
            y = x - k - 1
            if y > 0 and vis[y] and tree.query(x - 1) - tree.query(y) == 0:
                return i
            y = x + k + 1
            if y <= n and vis[y] and tree.query(y - 1) - tree.query(x) == 0:
                return i
        return -1
```

```java
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        boolean[] vis = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            int x = bulbs[i - 1];
            tree.update(x, 1);
            vis[x] = true;
            int y = x - k - 1;
            if (y > 0 && vis[y] && tree.query(x - 1) - tree.query(y) == 0) {
                return i;
            }
            y = x + k + 1;
            if (y <= n && vis[y] && tree.query(y - 1) - tree.query(x) == 0) {
                return i;
            }
        }
        return -1;
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    public int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
}
```

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class Solution {
public:
    int kEmptySlots(vector<int>& bulbs, int k) {
        int n = bulbs.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        bool vis[n + 1];
        memset(vis, false, sizeof(vis));
        for (int i = 1; i <= n; ++i) {
            int x = bulbs[i - 1];
            tree->update(x, 1);
            vis[x] = true;
            int y = x - k - 1;
            if (y > 0 && vis[y] && tree->query(x - 1) - tree->query(y) == 0) {
                return i;
            }
            y = x + k + 1;
            if (y <= n && vis[y] && tree->query(y - 1) - tree->query(x) == 0) {
                return i;
            }
        }
        return -1;
    }
};
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for ; x <= this.n; x += x & -x {
		this.c[x] += delta
	}
}

func (this *BinaryIndexedTree) query(x int) (s int) {
	for ; x > 0; x -= x & -x {
		s += this.c[x]
	}
	return
}

func kEmptySlots(bulbs []int, k int) int {
	n := len(bulbs)
	tree := newBinaryIndexedTree(n)
	vis := make([]bool, n+1)
	for i, x := range bulbs {
		tree.update(x, 1)
		vis[x] = true
		i++
		y := x - k - 1
		if y > 0 && vis[y] && tree.query(x-1)-tree.query(y) == 0 {
			return i
		}
		y = x + k + 1
		if y <= n && vis[y] && tree.query(y-1)-tree.query(x) == 0 {
			return i
		}
	}
	return -1
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    public update(x: number, delta: number) {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    public query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function kEmptySlots(bulbs: number[], k: number): number {
    const n = bulbs.length;
    const tree = new BinaryIndexedTree(n);
    const vis: boolean[] = Array(n + 1).fill(false);
    for (let i = 1; i <= n; ++i) {
        const x = bulbs[i - 1];
        tree.update(x, 1);
        vis[x] = true;
        let y = x - k - 1;
        if (y > 0 && vis[y] && tree.query(x - 1) - tree.query(y) === 0) {
            return i;
        }
        y = x + k + 1;
        if (y <= n && vis[y] && tree.query(y - 1) - tree.query(x) === 0) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
