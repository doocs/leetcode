# [1649. 通过指令创建有序数组](https://leetcode.cn/problems/create-sorted-array-through-instructions)

[English Version](/solution/1600-1699/1649.Create%20Sorted%20Array%20through%20Instructions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>instructions</code> ，你需要根据 <code>instructions</code> 中的元素创建一个有序数组。一开始你有一个空的数组 <code>nums</code> ，你需要 <strong>从左到右</strong> 遍历 <code>instructions</code> 中的元素，将它们依次插入 <code>nums</code> 数组中。每一次插入操作的 <strong>代价</strong> 是以下两者的 <strong>较小值</strong> ：</p>

<ul>
	<li><code>nums</code> 中 <strong>严格小于 </strong> <code>instructions[i]</code> 的数字数目。</li>
	<li><code>nums</code> 中 <strong>严格大于 </strong> <code>instructions[i]</code> 的数字数目。</li>
</ul>

<p>比方说，如果要将 <code>3</code> 插入到 <code>nums = [1,2,3,5]</code> ，那么插入操作的 <strong>代价</strong> 为 <code>min(2, 1)</code> (元素 <code>1</code> 和  <code>2</code> 小于 <code>3</code> ，元素 <code>5</code> 大于 <code>3</code> ），插入后 <code>nums</code> 变成 <code>[1,2,3,3,5]</code> 。</p>

<p>请你返回将 <code>instructions</code> 中所有元素依次插入 <code>nums</code> 后的 <strong>总最小代价 </strong>。由于答案会很大，请将它对 <code>10<sup>9</sup> + 7</code> <b>取余</b> 后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>instructions = [1,5,6,2]
<b>输出：</b>1
<b>解释：</b>一开始 nums = [] 。
插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
插入 5 ，代价为 min(1, 0) = 0 ，现在 nums = [1,5] 。
插入 6 ，代价为 min(2, 0) = 0 ，现在 nums = [1,5,6] 。
插入 2 ，代价为 min(1, 2) = 1 ，现在 nums = [1,2,5,6] 。
总代价为 0 + 0 + 0 + 1 = 1 。</pre>

<p><strong>示例 2:</strong></p>

<pre><b>输入：</b>instructions = [1,2,3,6,5,4]
<b>输出：</b>3
<b>解释：</b>一开始 nums = [] 。
插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
插入 2 ，代价为 min(1, 0) = 0 ，现在 nums = [1,2] 。
插入 3 ，代价为 min(2, 0) = 0 ，现在 nums = [1,2,3] 。
插入 6 ，代价为 min(3, 0) = 0 ，现在 nums = [1,2,3,6] 。
插入 5 ，代价为 min(3, 1) = 1 ，现在 nums = [1,2,3,5,6] 。
插入 4 ，代价为 min(3, 2) = 2 ，现在 nums = [1,2,3,4,5,6] 。
总代价为 0 + 0 + 0 + 0 + 1 + 2 = 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>instructions = [1,3,3,3,2,4,2,1,2]
<b>输出：</b>4
<b>解释：</b>一开始 nums = [] 。
插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3] 。
插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3] 。
插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3,3] 。
插入 2 ，代价为 min(1, 3) = 1 ，现在 nums = [1,2,3,3,3] 。
插入 4 ，代价为 min(5, 0) = 0 ，现在 nums = [1,2,3,3,3,4] 。
​​​​​插入 2 ，代价为 min(1, 4) = 1 ，现在 nums = [1,2,2,3,3,3,4] 。
插入 1 ，代价为 min(0, 6) = 0 ，现在 nums = [1,1,2,2,3,3,3,4] 。
插入 2 ，代价为 min(2, 4) = 2 ，现在 nums = [1,1,2,2,2,3,3,3,4] 。
总代价为 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= instructions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= instructions[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

树状数组最基本的功能就是求比某点 x 小的点的个数（这里的比较是抽象的概念，可以是数的大小、坐标的大小、质量的大小等等）。

比如给定数组 `a[5] = {2, 5, 3, 4, 1}`，求 `b[i] = 位置 i 左边小于等于 a[i] 的数的个数`。对于此例，`b[5] = {0, 1, 1, 2, 0}`。

解决方案是直接遍历数组，每个位置先求出 `query(a[i])`，然后再修改树状数组 `update(a[i], 1)` 即可。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

**方法二：线段树**

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 `log(width)`。更新某个元素的值，只需要更新 `log(width)` 个区间，并且这些区间都包含在一个包含该元素的大区间内。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 `[1, N]`；
-   线段树的每个叶子节点代表一个长度为 1 的元区间 `[x, x]`；
-   对于每个内部节点 `[l, r]`，它的左儿子是 `[l, mid]`，右儿子是 `[mid + 1, r]`, 其中 `mid = ⌊(l + r) / 2⌋` (即向下取整)。

> 本题线段树 Python3 代码 TLE，Java、C++ 代码 AC。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

树状数组：

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] += v
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def createSortedArray(self, instructions: List[int]) -> int:
        m = max(instructions)
        tree = BinaryIndexedTree(m)
        ans = 0
        mod = 10 ** 9 + 7
        for i, x in enumerate(instructions):
            cost = min(tree.query(x - 1), i - tree.query(x))
            ans += cost
            tree.update(x, 1)
        return ans % mod
```

线段树：

```python
class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.v = 0

class SegmentTree:
    def __init__(self, n):
        self.tr = [Node() for _ in range(4 * n)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l = l
        self.tr[u].r = r
        if l == r:
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)

    def modify(self, u, x, v):
        if self.tr[u].l == x and self.tr[u].r == x:
            self.tr[u].v += v
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def pushup(self, u):
        self.tr[u].v = self.tr[u << 1].v + self.tr[u << 1 | 1].v

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].v
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        v = 0
        if l <= mid:
            v = self.query(u << 1, l, r)
        if r > mid:
            v += self.query(u << 1 | 1, l, r)
        return v

class Solution:
    def createSortedArray(self, instructions: List[int]) -> int:
        n = max(instructions)
        tree = SegmentTree(n)
        ans = 0
        for num in instructions:
            a = tree.query(1, 1, num - 1)
            b = tree.query(1, 1, n) - tree.query(1, 1, num)
            ans += min(a, b)
            tree.modify(1, num, 1)
        return ans % int((1e9 + 7))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

树状数组：

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int createSortedArray(int[] instructions) {
        int m = 0;
        for (int x : instructions) {
            m = Math.max(m, x);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        int ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = 0; i < instructions.length; ++i) {
            int x = instructions[i];
            int cost = Math.min(tree.query(x - 1), i - tree.query(x));
            ans = (ans + cost) % mod;
            tree.update(x, 1);
        }
        return ans;
    }
}
```

线段树：

```java
class Solution {
    public int createSortedArray(int[] instructions) {
        int n = 100010;
        int mod = (int) 1e9 + 7;
        SegmentTree tree = new SegmentTree(n);
        int ans = 0;
        for (int num : instructions) {
            int a = tree.query(1, 1, num - 1);
            int b = tree.query(1, 1, n) - tree.query(1, 1, num);
            ans += Math.min(a, b);
            ans %= mod;
            tree.modify(1, num, 1);
        }
        return ans;
    }
}

class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    private Node[] tr;

    public SegmentTree(int n) {
        tr = new Node[4 * n];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    public void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v += v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public void pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v += query(u << 1, l, r);
        }
        if (r > mid) {
            v += query(u << 1 | 1, l, r);
        }
        return v;
    }
}
```

### **C++**

树状数组：

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int createSortedArray(vector<int>& instructions) {
        int m = *max_element(instructions.begin(), instructions.end());
        BinaryIndexedTree tree(m);
        const int mod = 1e9 + 7;
        int ans = 0;
        for (int i = 0; i < instructions.size(); ++i) {
            int x = instructions[i];
            int cost = min(tree.query(x - 1), i - tree.query(x));
            ans = (ans + cost) % mod;
            tree.update(x, 1);
        }
        return ans;
    }
};
```

线段树：

```cpp
class Node {
public:
    int l;
    int r;
    int v;
};

class SegmentTree {
public:
    vector<Node*> tr;

    SegmentTree(int n) {
        tr.resize(4 * n);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    void modify(int u, int x, int v) {
        if (tr[u]->l == x && tr[u]->r == x)
        {
            tr[u]->v += v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid) modify(u << 1, x, v);
        else modify(u << 1 | 1, x, v);
        pushup(u);
    }

    void pushup(int u) {
        tr[u]->v = tr[u << 1]->v + tr[u << 1 | 1]->v;
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u]->v;
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int v = 0;
        if (l <= mid) v = query(u << 1, l, r);
        if (r > mid) v += query(u << 1 | 1, l, r);
        return v;
    }
};

class Solution {
public:
    int createSortedArray(vector<int>& instructions) {
        int n = *max_element(instructions.begin(), instructions.end());
        int mod = 1e9 + 7;
        SegmentTree* tree = new SegmentTree(n);
        int ans = 0;
        for (int num : instructions)
        {
            int a = tree->query(1, 1, num - 1);
            int b = tree->query(1, 1, n) - tree->query(1, 1, num);
            ans += min(a, b);
            ans %= mod;
            tree->modify(1, num, 1);
        }
        return ans;
    }
};
```

### **Go**

树状数组：

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
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}

func createSortedArray(instructions []int) (ans int) {
	m := 0
	for _, x := range instructions {
		m = max(m, x)
	}
	tree := newBinaryIndexedTree(m)
	const mod = 1e9 + 7
	for i, x := range instructions {
		cost := min(tree.query(x-1), i-tree.query(x))
		ans = (ans + cost) % mod
		tree.update(x, 1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    public update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] += v;
            x += x & -x;
        }
    }

    public query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function createSortedArray(instructions: number[]): number {
    const m = Math.max(...instructions);
    const tree = new BinaryIndexedTree(m);
    let ans = 0;
    const mod = 10 ** 9 + 7;
    for (let i = 0; i < instructions.length; ++i) {
        const x = instructions[i];
        const cost = Math.min(tree.query(x - 1), i - tree.query(x));
        ans = (ans + cost) % mod;
        tree.update(x, 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
