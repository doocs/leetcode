# [494. 目标和](https://leetcode-cn.com/problems/target-sum)

[English Version](/solution/0400-0499/0494.Target%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号&nbsp;<code>+</code>&nbsp;和&nbsp;<code>-</code>。对于数组中的任意一个整数，你都可以从&nbsp;<code>+</code>&nbsp;或&nbsp;<code>-</code>中选择一个符号添加在前面。</p>

<p>返回可以使最终数组和为目标数 S 的所有添加符号的方法数。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>nums: [1, 1, 1, 1, 1], S: 3
<strong>输出：</strong>5
<strong>解释：</strong>

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

一共有5种方法让最终目标和为3。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>数组非空，且长度不会超过 20 。</li>
	<li>初始的数组的和不会超过 1000 。</li>
	<li>保证返回的最终结果能被 32 位整数存下。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

类似背包问题，只不过下标可能会出现负数，需要特殊处理。

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **...**

```

```

<!-- tabs:end -->
