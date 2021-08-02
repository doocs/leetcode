# [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self)

[中文文档](/solution/0200-0299/0238.Product%20of%20Array%20Except%20Self/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>an array</em> <code>answer</code> <em>such that</em> <code>answer[i]</code> <em>is equal to the product of all the elements of</em> <code>nums</code> <em>except</em> <code>nums[i]</code>.</p>

<p>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> [24,12,8,6]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [-1,1,0,-3,3]
<strong>Output:</strong> [0,0,9,0,0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-30 &lt;= nums[i] &lt;= 30</code></li>
	<li>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Could you solve it in <code>O(n)</code> time complexity and without using division?</li>
	<li>Could you solve it with <code>O(1)</code> constant space complexity? (The output array <strong>does not</strong> count as extra space for space complexity analysis.)</li>
</ul>


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

### **Go**

```go
func productExceptSelf(nums []int) []int {
	n := len(nums)

	l := make([]int, n)
	l[0] = 1
	for i := 1; i < n; i++ {
		l[i] = l[i-1] * nums[i-1]
	}

	r := make([]int, n)
	r[n-1] = 1
	for i := n - 2; i >= 0; i-- {
		r[i] = r[i+1] * nums[i+1]
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		ans[i] = l[i] * r[i]
	}

	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
