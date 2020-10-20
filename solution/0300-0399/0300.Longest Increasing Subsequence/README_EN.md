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

```

### **Java**

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

### **...**

```

```

<!-- tabs:end -->
