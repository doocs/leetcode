# [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个无序的整数数组，找到其中最长上升子序列的长度。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>[10,9,2,5,3,7,101,18]
</code><strong>输出: </strong>4 
<strong>解释: </strong>最长的上升子序列是&nbsp;<code>[2,3,7,101]，</code>它的长度是 <code>4</code>。</pre>

<p><strong>说明:</strong></p>

<ul>
	<li>可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。</li>
	<li>你算法的时间复杂度应该为&nbsp;O(<em>n<sup>2</sup></em>) 。</li>
</ul>

<p><strong>进阶:</strong> 你能将算法的时间复杂度降低到&nbsp;O(<em>n</em> log <em>n</em>) 吗?</p>



## 解法
<!-- 这里可写通用的实现逻辑 -->
动态规划求解。

定义 `dp[i]` 为以 `nums[i]` 结尾的最长子序列的长度。即题目求的是 `dp[i]` （`i ∈[0, n-1]`）的最大值。

状态转移方程为：

`dp[i] = max(dp[j]) + 1`，其中 `0≤j<i` 且 `nums[j]<nums[i]`。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        dp = [0 for _ in range(n)]
        dp[0] = 1
        res = 1
        for i in range(n):
            max_val = 0
            for j in range(0, i):
                if nums[j] < nums[i]:
                    max_val = max(max_val, dp[j])
                dp[i] = max_val + 1
                res = max(res, dp[i])
        return res
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < n; ++i) {
            int maxVal = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    maxVal = Math.max(maxVal, dp[j]);
                }
            }
            dp[i] = maxVal + 1;
            res = Math.max(res, dp[i]);
        }
        return res;

    }
}
```

### ...
```

```
