# [2518. Number of Great Partitions](https://leetcode.com/problems/number-of-great-partitions)

[中文文档](/solution/2500-2599/2518.Number%20of%20Great%20Partitions/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers and an integer <code>k</code>.</p>

<p><strong>Partition</strong> the array into two ordered <strong>groups</strong> such that each element is in exactly <strong>one</strong> group. A partition is called great if the <strong>sum</strong> of elements of each group is greater than or equal to <code>k</code>.</p>

<p>Return <em>the number of <strong>distinct</strong> great partitions</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two partitions are considered distinct if some element <code>nums[i]</code> is in different groups in the two partitions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and ([4], [1,2,3]).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3], k = 4
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no great partitions for this array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,6], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can either put nums[0] in the first partition or in the second partition.
The great partitions will be ([6], [6]) and ([6], [6]).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPartitions(self, nums: List[int], k: int) -> int:
        if sum(nums) < k * 2:
            return 0
        mod = 10**9 + 7
        n = len(nums)
        f = [[0] * k for _ in range(n + 1)]
        f[0][0] = 1
        ans = 1
        for i in range(1, n + 1):
            ans = ans * 2 % mod
            for j in range(k):
                f[i][j] = f[i - 1][j]
                if j >= nums[i - 1]:
                    f[i][j] = (f[i][j] + f[i - 1][j - nums[i - 1]]) % mod
        return (ans - sum(f[-1]) * 2 + mod) % mod
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPartitions(int[] nums, int k) {
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s < k * 2) {
            return 0;
        }
        int n = nums.length;
        long[][] f = new long[n + 1][k];
        f[0][0] = 1;
        long ans = 1;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            ans = ans * 2 % MOD;
            for (int j = 0; j < k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= v) {
                    f[i][j] = (f[i][j] + f[i - 1][j - v]) % MOD;
                }
            }
        }
        for (int j = 0; j < k; ++j) {
            ans = (ans - f[n][j] * 2 % MOD + MOD) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countPartitions(vector<int>& nums, int k) {
        long s = accumulate(nums.begin(), nums.end(), 0l);
        if (s < k * 2) return 0;
        int n = nums.size();
        long f[n + 1][k];
        int ans = 1;
        memset(f, 0, sizeof f);
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            ans = ans * 2 % mod;
            for (int j = 0; j < k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= v) {
                    f[i][j] = (f[i][j] + f[i - 1][j - v]) % mod;
                }
            }
        }
        for (int j = 0; j < k; ++j) {
            ans = (ans - f[n][j] * 2 % mod + mod) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func countPartitions(nums []int, k int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < k*2 {
		return 0
	}
	const mod int = 1e9 + 7
	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k)
	}
	f[0][0] = 1
	ans := 1
	for i := 1; i <= n; i++ {
		v := nums[i-1]
		ans = ans * 2 % mod
		for j := 0; j < k; j++ {
			f[i][j] = f[i-1][j]
			if j >= v {
				f[i][j] = (f[i][j] + f[i-1][j-v]) % mod
			}
		}
	}
	for j := 0; j < k; j++ {
		ans = (ans - f[n][j]*2%mod + mod) % mod
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
