# [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence)

[中文文档](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)

## Description

<p>Given an unsorted array of integers, find the length of longest increasing subsequence.</p>

<p><b>Example:</b></p>

<pre>

<b>Input:</b> <code>[10,9,2,5,3,7,101,18]

</code><b>Output: </b>4 

<strong>Explanation: </strong>The longest increasing subsequence is <code>[2,3,7,101]</code>, therefore the length is <code>4</code>. </pre>

<p><strong>Note: </strong></p>

<ul>
    <li>There may be more than one LIS combination, it is only necessary for you to return the length.</li>
    <li>Your algorithm should run in O(<i>n<sup>2</sup></i>) complexity.</li>
</ul>

<p><b>Follow up:</b> Could you improve it to O(<i>n</i> log <i>n</i>) time complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        dp = [1 for _ in nums]
        res = 1
        for i in range(1, n):
            for j in range(0, i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
            res = max(res, dp[i])
        return res
```

### **Java**

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n;
        if ((n = nums.length) < 2) return n;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
