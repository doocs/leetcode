# [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum)

[中文文档](/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <code>true</code> <em>if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,11,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be partitioned as [1, 5, 5] and [11].
</pre>

<p><strong class="example">Example 2:</strong></p>

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

Dynamic programming. It is similar to the 0-1 Knapsack problem.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        m, n = len(nums), s >> 1
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for i in range(1, m + 1):
            for j in range(n + 1):
                dp[i][j] = dp[i - 1][j]
                if not dp[i][j] and nums[i - 1] <= j:
                    dp[i][j] = dp[i - 1][j - nums[i - 1]]
        return dp[-1][-1]
```

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        n = s >> 1
        dp = [False] * (n + 1)
        dp[0] = True
        for v in nums:
            for j in range(n, v - 1, -1):
                dp[j] = dp[j] or dp[j - v]
        return dp[-1]
```

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        target = s >> 1

        @cache
        def dfs(i, s):
            nonlocal target
            if s > target or i >= len(nums):
                return False
            if s == target:
                return True
            return dfs(i + 1, s) or dfs(i + 1, s + nums[i])

        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length;
        int n = s >> 1;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[m][n];
    }
}
```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int n = s >> 1;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int v : nums) {
            for (int j = n; j >= v; --j) {
                dp[j] = dp[j] || dp[j - v];
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 != 0) return false;
        int m = nums.size(), n = s >> 1;
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1));
        dp[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && nums[i - 1] <= j) dp[i][j] = dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[m][n];
    }
};
```

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 != 0) return false;
        int n = s >> 1;
        vector<bool> dp(n + 1);
        dp[0] = true;
        for (int& v : nums)
            for (int j = n; j >= v; --j)
                dp[j] = dp[j] || dp[j - v];
        return dp[n];
    }
};
```

### **Go**

```go
func canPartition(nums []int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), s>>1
	dp := make([][]bool, m+1)
	for i := range dp {
		dp[i] = make([]bool, n+1)
	}
	dp[0][0] = true
	for i := 1; i <= m; i++ {
		for j := 0; j < n; j++ {
			dp[i][j] = dp[i-1][j]
			if !dp[i][j] && nums[i-1] <= j {
				dp[i][j] = dp[i-1][j-nums[i-1]]
			}
		}
	}
	return dp[m][n]
}
```

```go
func canPartition(nums []int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%2 != 0 {
		return false
	}
	n := s >> 1
	dp := make([]bool, n+1)
	dp[0] = true
	for _, v := range nums {
		for j := n; j >= v; j-- {
			dp[j] = dp[j] || dp[j-v]
		}
	}
	return dp[n]
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function (nums) {
    let s = 0;
    for (let v of nums) {
        s += v;
    }
    if (s % 2 != 0) {
        return false;
    }
    const m = nums.length;
    const n = s >> 1;
    const dp = new Array(n + 1).fill(false);
    dp[0] = true;
    for (let i = 1; i <= m; ++i) {
        for (let j = n; j >= nums[i - 1]; --j) {
            dp[j] = dp[j] || dp[j - nums[i - 1]];
        }
    }
    return dp[n];
};
```

### **...**

```

```

<!-- tabs:end -->
