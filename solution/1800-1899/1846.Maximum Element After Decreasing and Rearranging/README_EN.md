# [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging)

[中文文档](/solution/1800-1899/1846.Maximum%20Element%20After%20Decreasing%20and%20Rearranging/README.md)

## Description

<p>You are given an array of positive integers <code>arr</code>. Perform some operations (possibly none) on <code>arr</code> so that it satisfies these conditions:</p>

<ul>
	<li>The value of the <strong>first</strong> element in <code>arr</code> must be <code>1</code>.</li>
	<li>The absolute difference between any 2 adjacent elements must be <strong>less than or equal to </strong><code>1</code>. In other words, <code>abs(arr[i] - arr[i - 1]) &lt;= 1</code> for each <code>i</code> where <code>1 &lt;= i &lt; arr.length</code> (<strong>0-indexed</strong>). <code>abs(x)</code> is the absolute value of <code>x</code>.</li>
</ul>

<p>There are 2 types of operations that you can perform any number of times:</p>

<ul>
	<li><strong>Decrease</strong> the value of any element of <code>arr</code> to a <strong>smaller positive integer</strong>.</li>
	<li><strong>Rearrange</strong> the elements of <code>arr</code> to be in any order.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> possible value of an element in </em><code>arr</code><em> after performing the operations to satisfy the conditions</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,1,2,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
We can satisfy the conditions by rearranging <code>arr</code> so it becomes <code>[1,2,2,2,1]</code>.
The largest element in <code>arr</code> is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [100,1,1000]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
One possible way to satisfy the conditions is by doing the following:
1. Rearrange <code>arr</code> so it becomes <code>[1,100,1000]</code>.
2. Decrease the value of the second element to 2.
3. Decrease the value of the third element to 3.
Now <code>arr = [1,2,3], which </code>satisfies the conditions.
The largest element in <code>arr is 3.</code>
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The array already satisfies the conditions, and the largest element is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
