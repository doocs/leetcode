# [2031. 1 比 0 多的子数组个数](https://leetcode.cn/problems/count-subarrays-with-more-ones-than-zeros)

[English Version](/solution/2000-2099/2031.Count%20Subarrays%20With%20More%20Ones%20Than%20Zeros/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个只包含 <code>0</code> 和 <code>1</code> 的数组 <code>nums</code>，请返回 <code>1</code> 的数量 <strong>大于 </strong><code>0</code> 的数量的子数组的个数。由于答案可能很大，请返回答案对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取余</strong>&nbsp;的结果。</p>

<p>一个 <strong>子数组</strong> 指的是原数组中连续的一个子序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [0,1,1,0,1]
<strong>输出:</strong> 9
<strong>解释:</strong>
长度为 1 的、1 的数量大于 0 的数量的子数组有: [1], [1], [1]
长度为 2 的、1 的数量大于 0 的数量的子数组有: [1,1]
长度为 3 的、1 的数量大于 0 的数量的子数组有: [0,1,1], [1,1,0], [1,0,1]
长度为 4 的、1 的数量大于 0 的数量的子数组有: [1,1,0,1]
长度为 5 的、1 的数量大于 0 的数量的子数组有: [0,1,1,0,1]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [0]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有子数组的 1 的数量大于 0 的数量。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> nums = [1]
<strong>输出:</strong> 1
<strong>解释:</strong>
长度为 1 的、1 的数量大于 0 的数量的子数组有: [1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树状数组**

树状数组。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 `O(log n)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class BinaryIndexedTree:
    def __init__(self, n):
        n += int(1e5 + 1)
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        x += int(1e5 + 1)
        return x & -x

    def update(self, x, delta):
        x += int(1e5 + 1)
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        x += int(1e5 + 1)
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def subarraysWithMoreZerosThanOnes(self, nums: List[int]) -> int:
        n = len(nums)
        s = [0]
        for v in nums:
            s.append(s[-1] + (v or -1))
        tree = BinaryIndexedTree(n + 1)
        MOD = int(1e9 + 7)
        ans = 0
        for v in s:
            ans = (ans + tree.query(v - 1)) % MOD
            tree.update(v, 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        n += (int) 1e5 + 1;
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        x += (int) 1e5 + 1;
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        x += (int) 1e5 + 1;
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        x += (int) 1e5 + 1;
        return x & -x;
    }
}

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (nums[i] == 1 ? 1 : -1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(n + 1);
        int ans = 0;
        for (int v : s) {
            ans = (ans + tree.query(v - 1)) % MOD;
            tree.update(v, 1);
        }
        return ans;
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
        : n(_n + 1e5 + 1)
        , c(_n + 1 + 1e5 + 1) { }

    void update(int x, int delta) {
        x += 1e5 + 1;
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        x += 1e5 + 1;
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        x += 1e5 + 1;
        return x & -x;
    }
};

class Solution {
public:
    int subarraysWithMoreZerosThanOnes(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + (nums[i] == 1 ? 1 : -1);
        BinaryIndexedTree* tree = new BinaryIndexedTree(n + 1);
        int ans = 0;
        const int MOD = 1e9 + 7;
        for (int v : s) {
            ans = (ans + tree->query(v - 1)) % MOD;
            tree->update(v, 1);
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
	n += 1e5 + 1
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	x += 1e5 + 1
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	x += 1e5 + 1
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	x += 1e5 + 1
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func subarraysWithMoreZerosThanOnes(nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		if v == 0 {
			v = -1
		}
		s[i+1] = s[i] + v
	}
	tree := newBinaryIndexedTree(n + 1)
	ans := 0
	mod := int(1e9 + 7)
	for _, v := range s {
		ans = (ans + tree.query(v-1)) % mod
		tree.update(v, 1)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
