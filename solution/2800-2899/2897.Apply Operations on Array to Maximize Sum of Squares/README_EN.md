# [2897. Apply Operations on Array to Maximize Sum of Squares](https://leetcode.com/problems/apply-operations-on-array-to-maximize-sum-of-squares)

[中文文档](/solution/2800-2899/2897.Apply%20Operations%20on%20Array%20to%20Maximize%20Sum%20of%20Squares/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>You can do the following operation on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any two distinct indices <code>i</code> and <code>j</code> and <strong>simultaneously</strong> update the values of <code>nums[i]</code> to <code>(nums[i] AND nums[j])</code> and <code>nums[j]</code> to <code>(nums[i] OR nums[j])</code>. Here, <code>OR</code> denotes the bitwise <code>OR</code> operation, and <code>AND</code> denotes the bitwise <code>AND</code> operation.</li>
</ul>

<p>You have to choose <code>k</code> elements from the final array and calculate the sum of their <strong>squares</strong>.</p>

<p>Return <em>the <strong>maximum</strong> sum of squares you can achieve</em>.</p>

<p>Since the answer can be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,5,8], k = 2
<strong>Output:</strong> 261
<strong>Explanation:</strong> We can do the following operations on the array:
- Choose i = 0 and j = 3, then change nums[0] to (2 AND 8) = 0 and nums[3] to (2 OR 8) = 10. The resulting array is nums = [0,6,5,10].
- Choose i = 2 and j = 3, then change nums[2] to (5 AND 10) = 0 and nums[3] to (5 OR 10) = 15. The resulting array is nums = [0,6,0,15].
We can choose the elements 15 and 6 from the final array. The sum of squares is 15<sup>2</sup> + 6<sup>2</sup> = 261.
It can be shown that this is the maximum value we can get.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,4,7], k = 3
<strong>Output:</strong> 90
<strong>Explanation:</strong> We do not need to apply any operations.
We can choose the elements 7, 5, and 4 with a sum of squares: 7<sup>2</sup> + 5<sup>2</sup> + 4<sup>2</sup> = 90.
It can be shown that this is the maximum value we can get.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
