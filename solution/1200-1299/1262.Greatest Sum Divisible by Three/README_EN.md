# [1262. Greatest Sum Divisible by Three](https://leetcode.com/problems/greatest-sum-divisible-by-three)

[中文文档](/solution/1200-1299/1262.Greatest%20Sum%20Divisible%20by%20Three/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the <strong>maximum possible sum </strong>of elements of the array such that it is divisible by three</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,5,1,8]
<strong>Output:</strong> 18
<strong>Explanation:</strong> Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since 4 is not divisible by 3, do not pick any number.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,4]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [[0] * 3 for _ in range(n + 1)]
        for i in range(1, n + 1):
            dp[i][0] = dp[i - 1][0]
            dp[i][1] = dp[i - 1][1]
            dp[i][2] = dp[i - 1][2]
            if nums[i - 1] % 3 == 0:
                dp[i][0] += nums[i - 1]
                if dp[i][1]:
                    dp[i][1] += nums[i - 1]
                if dp[i][2]:
                    dp[i][2] += nums[i - 1]
            elif nums[i - 1] % 3 == 1:
                if dp[i - 1][2]:
                    dp[i][0] = max(dp[i][0], dp[i - 1][2] + nums[i - 1])
                dp[i][1] = max(dp[i][1], dp[i - 1][0] + nums[i - 1])
                if dp[i - 1][1]:
                    dp[i][2] = max(dp[i][2], dp[i - 1][1] + nums[i - 1])
            else:
                if dp[i - 1][1]:
                    dp[i][0] = max(dp[i][0], dp[i - 1][1] + nums[i - 1])
                if dp[i - 1][2]:
                    dp[i][1] = max(dp[i][1], dp[i - 1][2] + nums[i - 1])
                dp[i][2] = max(dp[i][2], dp[i - 1][0] + nums[i - 1])
        return dp[n][0]
```

### **Java**

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            int v = nums[i - 1] % 3;
            if (v == 0) {
                dp[i][0] += nums[i - 1];
                if (dp[i - 1][1] != 0) {
                    dp[i][1] += nums[i - 1];
                }
                if (dp[i - 1][2] != 0) {
                    dp[i][2] += nums[i - 1];
                }
            } else if (v == 1) {
                if (dp[i - 1][2] != 0) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 1][2] + nums[i - 1]);
                }
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + nums[i - 1]);
                if (dp[i - 1][1] != 0) {
                    dp[i][2] = Math.max(dp[i][2], dp[i - 1][1] + nums[i - 1]);
                } 
            } else {
                if (dp[i - 1][1] != 0) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + nums[i - 1]);
                }
                if (dp[i - 1][2] != 0) {
                    dp[i][1] = Math.max(dp[i][1], dp[i - 1][2] + nums[i - 1]);
                } 
                dp[i][2] = Math.max(dp[i][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        return dp[n][0];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
