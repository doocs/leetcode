# [683. K 个关闭的灯泡](https://leetcode.cn/problems/k-empty-slots)

[English Version](/solution/0600-0699/0683.K%20Empty%20Slots/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>n</code>&nbsp;个灯泡排成一行，编号从 <code>1</code> 到<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;。最初，所有灯泡都关闭。每天&nbsp;<strong>只打开一个</strong>&nbsp;灯泡，直到<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;天后所有灯泡都打开。</p>

<p>给你一个长度为<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;的灯泡数组 <code>blubs</code> ，其中 <code>bulls[i] = x</code> 意味着在第 <code>(i+1)</code> 天，我们会把在位置 <code>x</code> 的灯泡打开，其中 <code>i</code> <strong>从 0 开始</strong>，<code>x</code> <strong>从 1 开始</strong>。</p>

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def kEmptySlots(self, bulbs: List[int], k: int) -> int:
        n = len(bulbs)
        tree = BinaryIndexedTree(n)
        for i, x in enumerate(bulbs, 1):
            tree.update(x, 1)
            case1 = (
                x - k - 1 > 0
                and tree.query(x - k - 1) - tree.query(x - k - 2) == 1
                and tree.query(x - 1) - tree.query(x - k - 1) == 0
            )
            case2 = (
                x + k + 1 <= n
                and tree.query(x + k + 1) - tree.query(x + k) == 1
                and tree.query(x + k) - tree.query(x) == 0
            )
            if case1 or case2:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            int x = bulbs[i];
            tree.update(x, 1);
            boolean case1 = x - k - 1 > 0 && tree.query(x - k - 1) - tree.query(x - k - 2) == 1
                && tree.query(x - 1) - tree.query(x - k - 1) == 0;
            boolean case2 = x + k + 1 <= n && tree.query(x + k + 1) - tree.query(x + k) == 1
                && tree.query(x + k) - tree.query(x) == 0;
            if (case1 || case2) {
                return i + 1;
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
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

### **C++**

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    int kEmptySlots(vector<int>& bulbs, int k) {
        int n = bulbs.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            int x = bulbs[i];
            tree->update(x, 1);
            bool case1 = x - k - 1 > 0 && tree->query(x - k - 1) - tree->query(x - k - 2) == 1 && tree->query(x - 1) - tree->query(x - k - 1) == 0;
            bool case2 = x + k + 1 <= n && tree->query(x + k + 1) - tree->query(x + k) == 1 && tree->query(x + k) - tree->query(x) == 0;
            if (case1 || case2) return i + 1;
        }
        return -1;
    }
};
```

### **Go**

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func kEmptySlots(bulbs []int, k int) int {
	n := len(bulbs)
	tree := newBinaryIndexedTree(n)
	for i, x := range bulbs {
		tree.update(x, 1)
		case1 := x-k-1 > 0 && tree.query(x-k-1)-tree.query(x-k-2) == 1 && tree.query(x-1)-tree.query(x-k-1) == 0
		case2 := x+k+1 <= n && tree.query(x+k+1)-tree.query(x+k) == 1 && tree.query(x+k)-tree.query(x) == 0
		if case1 || case2 {
			return i + 1
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
