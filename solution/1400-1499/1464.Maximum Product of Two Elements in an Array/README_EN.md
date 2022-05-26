# [1464. Maximum Product of Two Elements in an Array](https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array)

[中文文档](/solution/1400-1499/1464.Maximum%20Product%20of%20Two%20Elements%20in%20an%20Array/README.md)

## Description

Given the array of integers <code>nums</code>, you will choose two different indices <code>i</code> and <code>j</code> of that array. <em>Return the maximum value of</em> <code>(nums[i]-1)\*(nums[j]-1)</code>.

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,2]
<strong>Output:</strong> 12 
<strong>Explanation:</strong> If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,4,5]
<strong>Output:</strong> 16
<strong>Explanation:</strong> Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,7]
<strong>Output:</strong> 12
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^3</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        i = 0 if nums[0] > nums[1] else 1
        j = 1 - i
        for k in range(2, len(nums)):
            if nums[k] > nums[i]:
                j = k
                i, j = j, i
            elif nums[k] > nums[j]:
                j = k
        return (nums[i] - 1) * (nums[j] - 1)
```

### **Java**

```java
class Solution {
    public int maxProduct(int[] nums) {
        int i = nums[0] > nums[1] ? 0 : 1;
        int j = 1 - i;
        for (int k = 2; k < nums.length; ++k) {
            if (nums[k] > nums[i]) {
                j = k;
                int t = i;
                i = j;
                j = t;
            } else if (nums[k] > nums[j]) {
                j = k;
            }
        }
        return (nums[i] - 1) * (nums[j] - 1);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
