# [2572. Count the Number of Square-Free Subsets](https://leetcode.com/problems/count-the-number-of-square-free-subsets)

[中文文档](/solution/2500-2599/2572.Count%20the%20Number%20of%20Square-Free%20Subsets/README.md)

## Description

<p>You are given a positive integer <strong>0-indexed</strong>&nbsp;array <code>nums</code>.</p>

<p>A subset of the array <code>nums</code> is <strong>square-free</strong> if the product of its elements is a <strong>square-free integer</strong>.</p>

<p>A <strong>square-free integer</strong> is an integer that is divisible by no square number other than <code>1</code>.</p>

<p>Return <em>the number of square-free non-empty subsets of the array</em> <strong>nums</strong>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>non-empty</strong>&nbsp;<strong>subset</strong> of <code>nums</code> is an array that can be obtained by deleting some (possibly none but not all) elements from <code>nums</code>. Two subsets are different if and only if the chosen indices to delete are different.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 square-free subsets in this example:
- The subset consisting of the 0<sup>th</sup> element [3]. The product of its elements is 3, which is a square-free integer.
- The subset consisting of the 3<sup>rd</sup> element [5]. The product of its elements is 5, which is a square-free integer.
- The subset consisting of 0<sup>th</sup> and 3<sup>rd</sup> elements [3,5]. The product of its elements is 15, which is a square-free integer.
It can be proven that there are no more than 3 square-free subsets in the given array.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is 1 square-free subset in this example:
- The subset consisting of the 0<sup>th</sup> element [1]. The product of its elements is 1, which is a square-free integer.
It can be proven that there is no more than 1 square-free subset in the given array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
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
