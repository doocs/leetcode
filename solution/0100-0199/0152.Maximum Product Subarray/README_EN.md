# [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray)

[中文文档](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README.md)

## Description

<p>Given an integer array <code>nums</code>, find a contiguous non-empty subarray within the array that has the largest product, and return <em>the product</em>.</p>

<p>It is <strong>guaranteed</strong> that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> [2,3] has the largest product 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The result cannot be 2, because [-2,-1] is not a subarray.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>


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
