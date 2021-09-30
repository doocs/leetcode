# [494. Target Sum](https://leetcode.com/problems/target-sum)

[中文文档](/solution/0400-0499/0494.Target%20Sum/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>

<p>You want to build an <strong>expression</strong> out of nums by adding one of the symbols <code>&#39;+&#39;</code> and <code>&#39;-&#39;</code> before each integer in nums and then concatenate all the integers.</p>

<ul>
	<li>For example, if <code>nums = [2, 1]</code>, you can add a <code>&#39;+&#39;</code> before <code>2</code> and a <code>&#39;-&#39;</code> before <code>1</code> and concatenate them to build the expression <code>&quot;+2-1&quot;</code>.</li>
</ul>

<p>Return the number of different <strong>expressions</strong> that you can build, which evaluates to <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], target = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], target = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
</ul>

## Solutions

It is similar to the backpack problem, except that the index may appear negative, which requires special handling.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        if target < -1000 or target > 1000:
            return 0
        n = len(nums)
        dp = [[0] * 2001 for i in range(n)]
        dp[0][nums[0] + 1000] += 1
        dp[0][-nums[0] + 1000] += 1
        for i in range(1, n):
            for j in range(-1000, 1001):
                if dp[i - 1][j + 1000] > 0:
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
        return dp[n - 1][target + 1000]
```

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s - target < 0 or (s - target) % 2 != 0:
            return 0
        target = (s - target) // 2 + 1
        n = len(nums) + 1
        dp = [[0] * target for _ in range(n)]
        dp[0][0] = 1
        for i in range(1, n):
            for j in range(target):
                dp[i][j] = dp[i - 1][j]
                if nums[i - 1] <= j:
                    dp[i][j] += dp[i - 1][j - nums[i - 1]]
        return dp[-1][-1]
```

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s - target < 0 or (s - target) % 2 != 0:
            return 0
        target = (s - target) // 2 + 1
        n = len(nums) + 1
        dp = [0] * target
        dp[0] = 1
        for i in range(1, n):
            for j in range(target - 1, nums[i - 1] - 1, -1):
                dp[j] += dp[j - nums[i - 1]]
        return dp[-1]
```

### **Java**

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        if (target < -1000 || target > 1000) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][2001];

        dp[0][nums[0] + 1000] += 1;
        dp[0][-nums[0] + 1000] += 1;

        for (int i = 1; i < n; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (dp[i - 1][j + 1000] > 0) {
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000];
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000];
                }
            }
        }
        return dp[n - 1][target + 1000];
    }
}
```

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s - target < 0 || (s - target) % 2 != 0) {
            return 0;
        }
        target = (s - target) / 2 + 1;
        int[] dp = new int[target];
        dp[0] = 1;
        for (int i = 1; i < nums.length + 1; ++i) {
            for (int j = target - 1; j >= nums[i - 1]; --j) {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[target - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = 0;
        for (int x : nums) s += x;
        if (s - target < 0 || (s - target) % 2 != 0) return 0;
        target = (s - target) / 2 + 1;
        vector<int> dp(target);
        dp[0] = 1;
        for (int i = 1; i < nums.size() + 1; ++i)
        {
            for (int j = target - 1; j >= nums[i - 1]; --j)
            {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[target - 1];
    }
};
```

### **Go**

```go
func findTargetSumWays(nums []int, target int) int {
	if target < -1000 || target > 1000 {
		return 0
	}
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 2001)
	}
	dp[0][nums[0]+1000] += 1
	dp[0][-nums[0]+1000] += 1
	for i := 1; i < n; i++ {
		for j := -1000; j <= 1000; j++ {
			if dp[i-1][j+1000] > 0 {
				dp[i][j+nums[i]+1000] += dp[i-1][j+1000]
				dp[i][j-nums[i]+1000] += dp[i-1][j+1000]
			}
		}
	}
	return dp[n-1][target+1000]
}
```

```go
func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s-target < 0 || (s-target)%2 != 0 {
		return 0
	}
	target = (s-target)/2 + 1
	dp := make([]int, target)
	dp[0] = 1
	for i := 1; i < len(nums)+1; i++ {
		for j := target - 1; j >= nums[i-1]; j-- {
			dp[j] += dp[j-nums[i-1]]
		}
	}
	return dp[target-1]
}
```

### **...**

```

```

<!-- tabs:end -->
