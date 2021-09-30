# [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum)

[中文文档](/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README.md)

## Description

<p>Given a <strong>non-empty</strong> array <code>nums</code> containing <strong>only positive integers</strong>, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,11,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be partitioned as [1, 5, 5] and [11].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,5]
<strong>Output:</strong> false
<strong>Explanation:</strong> The array cannot be partitioned into equal sum subsets.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [[False] * n for _ in range(m)]
        for i in range(m):
            dp[i][0] = True
        if nums[0] < n:
            dp[0][nums[0]] = True

        for i in range(1, m):
            for j in range(n):
                dp[i][j] = dp[i - 1][j]
                if not dp[i][j] and nums[i] <= j:
                    dp[i][j] = dp[i - 1][j - nums[i]]
        return dp[-1][-1]
```

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [False] * n
        dp[0] = True
        if nums[0] < n:
            dp[nums[0]] = True

        for i in range(1, m):
            for j in range(n - 1, nums[i] - 1, -1):
                dp[j] = dp[j] or dp[j - nums[i]]
        return dp[-1]
```

### **Java**

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length, n = (s >> 1) + 1;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        if (nums[0] < n) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = 0;
        for (int x : nums) s += x;
        if (s % 2 != 0) return false;
        int m = nums.size(), n = (s >> 1) + 1;
        vector<bool> dp(n);
        dp[0] = true;
        if (nums[0] < n) dp[nums[0]] = true;
        for (int i = 1; i < m; ++i)
        {
            for (int j = n - 1; j >= nums[i]; --j)
            {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
};
```

### **Go**

```go
func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), (s>>1)+1
	dp := make([]bool, n)
	dp[0] = true
	if nums[0] < n {
		dp[nums[0]] = true
	}
	for i := 1; i < m; i++ {
		for j := n - 1; j >= nums[i]; j-- {
			dp[j] = dp[j] || dp[j-nums[i]]
		}
	}
	return dp[n-1]
}
```

### **...**

```

```

<!-- tabs:end -->
