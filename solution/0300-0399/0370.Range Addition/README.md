# [370. 区间加法 🔒](https://leetcode.cn/problems/range-addition)

[English Version](/solution/0300-0399/0370.Range%20Addition/README_EN.md)

<!-- tags:数组,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你有一个长度为&nbsp;<em><strong>n</strong></em>&nbsp;的数组，初始情况下所有的数字均为&nbsp;<strong>0</strong>，你将会被给出&nbsp;<em><strong>k</strong></em>​​​​​​<em>​</em> 个更新的操作。</p>

<p>其中，每个操作会被表示为一个三元组：<strong>[startIndex, endIndex, inc]</strong>，你需要将子数组&nbsp;<strong>A[startIndex ... endIndex]</strong>（包括 startIndex 和 endIndex）增加&nbsp;<strong>inc</strong>。</p>

<p>请你返回&nbsp;<strong><em>k</em></strong>&nbsp;次操作后的数组。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入: </strong>length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
<strong>输出: </strong>[-2,0,3,5,3]
</pre>

<p><strong>解释:</strong></p>

<pre>初始状态:
[0,0,0,0,0]

进行了操作 [1,3,2] 后的状态:
[0,2,2,2,0]

进行了操作 [2,4,3] 后的状态:
[0,2,5,5,3]

进行了操作 [0,2,-2] 后的状态:
[-2,0,3,5,3]
</pre>

## 解法

### 方法一：差分数组

差分数组模板题。

我们定义 $d$ 为差分数组。给区间 $[l,..r]$ 中的每一个数加上 $c$，那么有 $d[l] += c$，并且 $d[r+1] -= c$。最后我们对差分数组求前缀和，即可得到原数组。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        d = [0] * length
        for l, r, c in updates:
            d[l] += c
            if r + 1 < length:
                d[r + 1] -= c
        return list(accumulate(d))
```

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] d = new int[length];
        for (var e : updates) {
            int l = e[0], r = e[1], c = e[2];
            d[l] += c;
            if (r + 1 < length) {
                d[r + 1] -= c;
            }
        }
        for (int i = 1; i < length; ++i) {
            d[i] += d[i - 1];
        }
        return d;
    }
}
```

```cpp
class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> d(length);
        for (auto& e : updates) {
            int l = e[0], r = e[1], c = e[2];
            d[l] += c;
            if (r + 1 < length) d[r + 1] -= c;
        }
        for (int i = 1; i < length; ++i) d[i] += d[i - 1];
        return d;
    }
};
```

```go
func getModifiedArray(length int, updates [][]int) []int {
	d := make([]int, length)
	for _, e := range updates {
		l, r, c := e[0], e[1], e[2]
		d[l] += c
		if r+1 < length {
			d[r+1] -= c
		}
	}
	for i := 1; i < length; i++ {
		d[i] += d[i-1]
	}
	return d
}
```

```js
/**
 * @param {number} length
 * @param {number[][]} updates
 * @return {number[]}
 */
var getModifiedArray = function (length, updates) {
    const d = new Array(length).fill(0);
    for (const [l, r, c] of updates) {
        d[l] += c;
        if (r + 1 < length) {
            d[r + 1] -= c;
        }
    }
    for (let i = 1; i < length; ++i) {
        d[i] += d[i - 1];
    }
    return d;
};
```

<!-- tabs:end -->

### 方法二：树状数组 + 差分思想

时间复杂度 $O(n\times \log n)$。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 $x$ 位置的数加上一个值 $delta$；
1. **前缀和查询** `query(x)`：查询序列 $[1,...x]$ 区间的区间和，即位置 $x$ 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

<!-- tabs:start -->

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
        while x:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        tree = BinaryIndexedTree(length)
        for start, end, inc in updates:
            tree.update(start + 1, inc)
            tree.update(end + 2, -inc)
        return [tree.query(i + 1) for i in range(length)]
```

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        BinaryIndexedTree tree = new BinaryIndexedTree(length);
        for (int[] e : updates) {
            int start = e[0], end = e[1], inc = e[2];
            tree.update(start + 1, inc);
            tree.update(end + 2, -inc);
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            ans[i] = tree.query(i + 1);
        }
        return ans;
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

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

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
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(length);
        for (auto& e : updates) {
            int start = e[0], end = e[1], inc = e[2];
            tree->update(start + 1, inc);
            tree->update(end + 2, -inc);
        }
        vector<int> ans;
        for (int i = 0; i < length; ++i) ans.push_back(tree->query(i + 1));
        return ans;
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

func getModifiedArray(length int, updates [][]int) []int {
	tree := newBinaryIndexedTree(length)
	for _, e := range updates {
		start, end, inc := e[0], e[1], e[2]
		tree.update(start+1, inc)
		tree.update(end+2, -inc)
	}
	ans := make([]int, length)
	for i := range ans {
		ans[i] = tree.query(i + 1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
