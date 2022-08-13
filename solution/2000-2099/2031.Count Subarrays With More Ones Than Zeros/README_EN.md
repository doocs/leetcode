# [2031. Count Subarrays With More Ones Than Zeros](https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros)

[中文文档](/solution/2000-2099/2031.Count%20Subarrays%20With%20More%20Ones%20Than%20Zeros/README.md)

## Description

<p>You are given a binary array <code>nums</code> containing only the integers <code>0</code> and <code>1</code>. Return<em> the number of <strong>subarrays</strong> in nums that have <strong>more</strong> </em><code>1</code>&#39;<em>s than </em><code>0</code><em>&#39;s. Since the answer may be very large, return it <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,0,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The subarrays of size 1 that have more ones than zeros are: [1], [1], [1]
The subarrays of size 2 that have more ones than zeros are: [1,1]
The subarrays of size 3 that have more ones than zeros are: [0,1,1], [1,1,0], [1,0,1]
The subarrays of size 4 that have more ones than zeros are: [1,1,0,1]
The subarrays of size 5 that have more ones than zeros are: [0,1,1,0,1]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No subarrays have more ones than zeros.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The subarrays of size 1 that have more ones than zeros are: [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

## Solutions

Binary Indexed Tree.

<!-- tabs:start -->

### **Python3**

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
