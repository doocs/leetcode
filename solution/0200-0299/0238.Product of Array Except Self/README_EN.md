# [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self)

[中文文档](/solution/0200-0299/0238.Product%20of%20Array%20Except%20Self/README.md)

## Description

<p>Given an array <code>nums</code> of <em>n</em> integers where <em>n</em> &gt; 1, &nbsp;return an array <code>output</code> such that <code>output[i]</code> is equal to the product of all the elements of <code>nums</code> except <code>nums[i]</code>.</p>

<p><b>Example:</b></p>

<pre>
<b>Input:</b>  <code>[1,2,3,4]</code>
<b>Output:</b> <code>[24,12,8,6]</code>
</pre>

<p><strong>Constraint:</strong>&nbsp;It&#39;s guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.</p>

<p><strong>Note: </strong>Please solve it <strong>without division</strong> and in O(<em>n</em>).</p>

<p><strong>Follow up:</strong><br />
Could you solve it with constant space complexity? (The output array <strong>does not</strong> count as extra space for the purpose of space complexity analysis.)</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        output = [1 for _ in nums]
        left = right = 1
        for i in range(n):
            output[i] = left
            left *= nums[i]
        for i in range(n - 1, -1, -1):
            output[i] *= right
            right *= nums[i]
        return output
```

### **Java**

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            output[i] = left;
            left *= nums[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            output[i] *= right;
            right *= nums[i];
        }
        return output;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function (nums) {
  const n = nums.length;
  let output = new Array(n);
  for (let i = 0, left = 1; i < n; ++i) {
    output[i] = left;
    left *= nums[i];
  }
  for (let i = n - 1, right = 1; i >= 0; --i) {
    output[i] *= right;
    right *= nums[i];
  }
  return output;
};
```

### **...**

```

```

<!-- tabs:end -->
