# [1262. 可被三整除的最大和](https://leetcode.cn/problems/greatest-sum-divisible-by-three)

[English Version](/solution/1200-1299/1262.Greatest%20Sum%20Divisible%20by%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，请你找出并返回能被三整除的元素最大和。</p>

<ol>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,6,5,1,8]
<strong>输出：</strong>18
<strong>解释：</strong>选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4]
<strong>输出：</strong>0
<strong>解释：</strong>4 不能被 3 整除，所以无法选出数字，返回 0。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,4]
<strong>输出：</strong>12
<strong>解释：</strong>选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 4 * 10^4</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
