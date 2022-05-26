# [674. Longest Continuous Increasing Subsequence](https://leetcode.com/problems/longest-continuous-increasing-subsequence)

[中文文档](/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README.md)

## Description

<p>

Given an unsorted array of integers, find the length of longest <code>continuous</code> increasing subsequence (subarray).

</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [1,3,5,4,7]

<b>Output:</b> 3

<b>Explanation:</b> The longest continuous increasing subsequence is [1,3,5], its length is 3. 

Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> [2,2,2,2,2]

<b>Output:</b> 1

<b>Explanation:</b> The longest continuous increasing subsequence is [2], its length is 1. 

</pre>

</p>

<p><b>Note:</b>

Length of the array will not exceed 10,000.

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        res = f = 1
        for i in range(1, n):
            f = 1 + (f if nums[i - 1] < nums[i] else 0)
            res = max(res, f)
        return res
```

### **Java**

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int n;
        if ((n = nums.length) < 2) return n;
        int res = 1, f = 1;
        for (int i = 1; i < n; ++i) {
            f = 1 + (nums[i - 1] < nums[i] ? f : 0);
            res = Math.max(res, f);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
