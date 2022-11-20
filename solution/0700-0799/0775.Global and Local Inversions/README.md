# [775. 全局倒置与局部倒置](https://leetcode.cn/problems/global-and-local-inversions)

[English Version](/solution/0700-0799/0775.Global%20and%20Local%20Inversions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，表示由范围 <code>[0, n - 1]</code> 内所有整数组成的一个排列。</p>

<p><strong>全局倒置</strong> 的数目等于满足下述条件不同下标对 <code>(i, j)</code> 的数目：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; n</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
</ul>

<p><strong>局部倒置</strong> 的数目等于满足下述条件的下标 <code>i</code> 的数目：</p>

<ul>
	<li><code>0 &lt;= i &lt; n - 1</code></li>
	<li><code>nums[i] &gt; nums[i + 1]</code></li>
</ul>

<p>当数组 <code>nums</code> 中 <strong>全局倒置</strong> 的数量等于 <strong>局部倒置</strong> 的数量时，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,2]
<strong>输出：</strong>true
<strong>解释：</strong>有 1 个全局倒置，和 1 个局部倒置。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,0]
<strong>输出：</strong>false
<strong>解释：</strong>有 2 个全局倒置，和 1 个局部倒置。
</pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
	<li><code>nums</code> 是范围 <code>[0, n - 1]</code> 内所有数字组成的一个排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：维护前缀最大值**

根据题意，我们可以发现，一个数组中的局部倒置一定是全局倒置，但是全局倒置不一定是局部倒置。也就是说，全局倒置的数量一定大于等于局部倒置的数量。

因此，我们枚举每个数 $nums[i]$，其中 $2 \leq i \leq n - 1$，维护前缀数组 $nums[0,..i-2]$ 中的最大值，记为 $mx$。如果存在 $mx$ 大于 $nums[i]$，则说明全局倒置的数量大于局部倒置的数量，返回 `false` 即可。

遍历结束后，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：树状数组**

这道题目实际上是一个“逆序对”问题。

局部倒置的数量等于相邻元素之间逆序对的个数，可以在遍历数组 `nums` 的过程中直接求出；而全局倒置的数量等于逆序对的个数，求解逆序对个数的一个常用做法是使用树状数组。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. 单点更新：即函数 `update(x, delta)`，把序列 $x$ 位置的数加上一个值 $delta$。时间复杂度 $O(\log n)$。
1. 前缀和查询：即函数 `query(x)`，查询序列 `[1,...x]` 区间的区间和，即位置 $x$ 的前缀和。时间复杂度 $O(\log n)$。

对于本题，我们定义一个变量 $cnt$ 记录局部倒置的数量与全局倒置的数量之差。如果遍历过程中，$cnt$ 的值小于 $0$，则说明全局倒置的数量大于局部倒置的数量，返回 `false` 即可。

时间复杂度 $O(n\times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isIdealPermutation(self, nums: List[int]) -> bool:
        mx = 0
        for i in range(2, len(nums)):
            if (mx := max(mx, nums[i - 2])) > nums[i]:
                return False
        return True
```

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
    def isIdealPermutation(self, nums: List[int]) -> bool:
        n = len(nums)
        tree = BinaryIndexedTree(n)
        cnt = 0
        for i, v in enumerate(nums):
            cnt += (i < n - 1 and v > nums[i + 1])
            cnt -= (i - tree.query(v))
            if cnt < 0:
                return False
            tree.update(v + 1, 1)
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int mx = 0;
        for (int i = 2; i < nums.length; ++i) {
            mx = Math.max(mx, nums[i - 2]);
            if (mx > nums[i]) {
                return false;
            }
        }
        return true;
    }
}
```

```java
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
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int cnt = 0;
        for (int i = 0; i < n && cnt >= 0; ++i) {
            cnt += (i < n - 1 && nums[i] > nums[i + 1] ? 1 : 0);
            cnt -= (i - tree.query(nums[i]));
            tree.update(nums[i] + 1, 1);
        }
        return cnt == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isIdealPermutation(vector<int>& nums) {
        int mx = 0;
        for (int i = 2; i < nums.size(); ++i) {
            mx = max(mx, nums[i - 2]);
            if (mx > nums[i]) return false;
        }
        return true;
    }
};
```

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n) : n(_n), c(_n + 1) {}

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
    bool isIdealPermutation(vector<int>& nums) {
        int n = nums.size();
        BinaryIndexedTree tree(n);
        long cnt = 0;
        for (int i = 0; i < n && ~cnt; ++i) {
            cnt += (i < n - 1 && nums[i] > nums[i + 1]);
            cnt -= (i - tree.query(nums[i]));
            tree.update(nums[i] + 1, 1);
        }
        return cnt == 0;
    }
};
```

### **Go**

```go
func isIdealPermutation(nums []int) bool {
	mx := 0
	for i := 2; i < len(nums); i++ {
		mx = max(mx, nums[i-2])
		if mx > nums[i] {
			return false
		}
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func isIdealPermutation(nums []int) bool {
	n := len(nums)
	tree := newBinaryIndexedTree(n)
	cnt := 0
	for i, v := range nums {
		if i < n-1 && v > nums[i+1] {
			cnt++
		}
		cnt -= (i - tree.query(v))
		if cnt < 0 {
			break
		}
		tree.update(v+1, 1)
	}
	return cnt == 0
}

type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	return BinaryIndexedTree{n, c}
}

func (this BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
