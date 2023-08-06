# [2811. Check if it is Possible to Split Array](https://leetcode.com/problems/check-if-it-is-possible-to-split-array)

[中文文档](/solution/2800-2899/2811.Check%20if%20it%20is%20Possible%20to%20Split%20Array/README.md)

## Description

<p>You are given an array <code>nums</code> of length <code>n</code> and an integer <code>m</code>. You need to determine if it is possible to split the array into <code>n</code> <strong>non-empty</strong> arrays by performing a series of steps.</p>

<p>In each step, you can select an existing array (which may be the result of previous steps) with a length of <strong>at least two</strong> and split it into <strong>two </strong>subarrays, if, <strong>for each </strong>resulting subarray, <strong>at least</strong> one of the following holds:</p>

<ul>
	<li>The length of the subarray is one, or</li>
	<li>The sum of elements of the subarray is <strong>greater than or equal</strong> to <code>m</code>.</li>
</ul>

<p>Return <code>true</code><em> if you can split the given array into </em><code>n</code><em> arrays, otherwise return</em> <code>false</code>.</p>

<p><strong>Note:</strong> A subarray is <em>a contiguous non-empty sequence of elements within an array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2, 2, 1], m = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> We can split the array into [2, 2] and [1] in the first step. Then, in the second step, we can split [2, 2] into [2] and [2]. As a result, the answer is true.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2, 1, 3], m = 5 
<strong>Output:</strong> false
<strong>Explanation: </strong>We can try splitting the array in two different ways: the first way is to have [2, 1] and [3], and the second way is to have [2] and [1, 3]. However, both of these ways are not valid. So, the answer is false.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2, 3, 3, 2, 3], m = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> We can split the array into [2, 3, 3, 2] and [3] in the first step. Then, in the second step, we can split [2, 3, 3, 2] into [2, 3, 3] and [2]. Then, in the third step, we can split [2, 3, 3] into [2] and [3, 3]. And in the last step we can split [3, 3] into [3] and [3]. As a result, the answer is true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 200</code></li>
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
