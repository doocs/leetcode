# [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray)

[中文文档](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README.md)

## Description

<p>Given an integer array&nbsp;<code>nums</code>, find the contiguous subarray within an array (containing at least one number) which has the largest product.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [2,3,-2,4]

<strong>Output:</strong> <code>6</code>

<strong>Explanation:</strong>&nbsp;[2,3] has the largest product 6.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [-2,0,-1]

<strong>Output:</strong> 0

<strong>Explanation:</strong>&nbsp;The result cannot be 2, because [-2,-1] is not a subarray.</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maxf = minf = nums[0]
        res, n = nums[0], len(nums)
        for i in range(1, n):
            p, q = maxf, minf
            maxf = max(nums[i], p * nums[i], q * nums[i])
            minf = min(nums[i], p * nums[i], q * nums[i])
            res = max(res, maxf)
        return res
```

### **Java**

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0];
        int res = nums[0], n = nums.length;
        for (int i = 1; i < n; ++i) {
            int p = maxf, q = minf;
            maxf = Math.max(nums[i], Math.max(p * nums[i], q * nums[i]));
            minf = Math.min(nums[i], Math.min(p * nums[i], q * nums[i]));
            res = Math.max(res, maxf);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
