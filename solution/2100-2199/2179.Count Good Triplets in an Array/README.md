# [2179. 统计数组中好三元组数目](https://leetcode-cn.com/problems/count-good-triplets-in-an-array)

[English Version](/solution/2100-2199/2179.Count%20Good%20Triplets%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，两者都是&nbsp;<code>[0, 1, ..., n - 1]</code>&nbsp;的&nbsp;<strong>排列</strong>&nbsp;。</p>

<p><strong>好三元组&nbsp;</strong>指的是&nbsp;<code>3</code>&nbsp;个&nbsp;<strong>互不相同</strong>&nbsp;的值，且它们在数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;中出现顺序保持一致。换句话说，如果我们将&nbsp;<code>pos1<sub>v</sub></code> 记为值&nbsp;<code>v</code>&nbsp;在&nbsp;<code>nums1</code>&nbsp;中出现的位置，<code>pos2<sub>v</sub></code>&nbsp;为值&nbsp;<code>v</code>&nbsp;在&nbsp;<code>nums2</code>&nbsp;中的位置，那么一个好三元组定义为&nbsp;<code>0 &lt;= x, y, z &lt;= n - 1</code>&nbsp;，且&nbsp;<code>pos1<sub>x</sub> &lt; pos1<sub>y</sub> &lt; pos1<sub>z</sub></code> 和&nbsp;<code>pos2<sub>x</sub> &lt; pos2<sub>y</sub> &lt; pos2<sub>z</sub></code>&nbsp;都成立的&nbsp;<code>(x, y, z)</code>&nbsp;。</p>

<p>请你返回好三元组的 <strong>总数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [2,0,1,3], nums2 = [0,1,2,3]
<b>输出：</b>1
<b>解释：</b>
总共有 4 个三元组 (x,y,z) 满足 pos1<sub>x</sub> &lt; pos1<sub>y</sub> &lt; pos1<sub>z&nbsp;</sub>，分别是 (2,0,1) ，(2,0,3) ，(2,1,3) 和 (0,1,3) 。
这些三元组中，只有 (0,1,3) 满足 pos2<sub>x</sub> &lt; pos2<sub>y</sub> &lt; pos2<sub>z</sub>&nbsp;。所以只有 1 个好三元组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
<b>输出：</b>4
<b>解释：</b>总共有 4 个好三元组 (4,0,3) ，(4,0,2) ，(4,1,3) 和 (4,1,2) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= n - 1</code></li>
	<li><code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;是&nbsp;<code>[0, 1, ..., n - 1]</code> 的排列。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

本题可以用树状数组解决。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 `O(log n)`。

对于本题，我们先用 pos 记录每个数在 nums2 中的位置，然后依次对 nums1 中的每个元素进行处理。

考虑**以当前数字作为三元组中间数字**的好三元组的数目。第一个数字需要是之前已经遍历过的，并且在 nums2 中的位置比当前数字更靠前的；第三个数字需要是当前还没有遍历过的，并且在 nums2 中的位置比当前数字更靠后的。

以 `nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]`为例，考虑我们的遍历过程：

1. 首先处理 4，此时 nums2 中出现情况为 `[4,X,X,X,X]`，4 之前有值的个数是 0，4 之后没有值的个数有 4 个。因此以 4 为中间数字能形成 0 个好三元组。
1. 接下来是 0，此时 nums2 中出现情况为 `[4,X,0,X,X]`，0 之前有值的个数是 1，0 之后没有值的个数有 2 个。因此以 0 为中间数字能形成 2 个好三元组。
1. 接下来是 1，此时 nums2 中出现情况为 `[4,1,0,X,X]`，1 之前有值的个数是 1，0 之后没有值的个数有 2 个。因此以 1 为中间数字能形成 2 个好三元组。
1. ...
1. 最后是 2，此时 nums2 中出现情况为 `[4,1,0,2,3]`，2 之前有值的个数是 4，2 之后没有值的个数是 0。因此以 2 为中间数字能形成 0 个好三元组。

我们可以用树状数组来更新 nums2 中各个位置数字的出现情况，快速算出每个数字左侧 1 的个数，以及右侧 0 的个数。

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
    def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        pos = {v: i for i, v in enumerate(nums2, 1)}
        ans = 0
        n = len(nums1)
        tree = BinaryIndexedTree(n)
        for num in nums1:
            p = pos[num]
            left = tree.query(p)
            right = n - p - (tree.query(n) - tree.query(p))
            ans += left * right
            tree.update(p, 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            pos[nums2[i]] = i + 1;
        }
        long ans = 0;
        for (int num : nums1) {
            int p = pos[num];
            long left = tree.query(p);
            long right = n - p - (tree.query(n) - tree.query(p));
            ans += left * right;
            tree.update(p, 1);
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

### **C++**

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int delta) {
        while (x <= n)
        {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
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
    long long goodTriplets(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> pos(n);
        for (int i = 0; i < n; ++i) pos[nums2[i]] = i + 1;
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        long long ans = 0;
        for (int& num : nums1)
        {
            int p = pos[num];
            int left = tree->query(p);
            int right = n - p - (tree->query(n) - tree->query(p));
            ans += 1ll * left * right;
            tree->update(p, 1);
        }
        return ans;
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

func goodTriplets(nums1 []int, nums2 []int) int64 {
	n := len(nums1)
	pos := make([]int, n)
	for i, v := range nums2 {
		pos[v] = i + 1
	}
	tree := newBinaryIndexedTree(n)
	var ans int64
	for _, num := range nums1 {
		p := pos[num]
		left := tree.query(p)
		right := n - p - (tree.query(n) - tree.query(p))
		ans += int64(left) * int64(right)
		tree.update(p, 1)
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
