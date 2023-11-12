# [2934. Minimum Operations to Maximize Last Elements in Arrays](https://leetcode.com/problems/minimum-operations-to-maximize-last-elements-in-arrays)

[中文文档](/solution/2900-2999/2934.Minimum%20Operations%20to%20Maximize%20Last%20Elements%20in%20Arrays/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays, <code>nums1</code> and <code>nums2</code>, both having length <code>n</code>.</p>

<p>You are allowed to perform a series of <strong>operations</strong> (<strong>possibly none</strong>).</p>

<p>In an operation, you select an index <code>i</code> in the range <code>[0, n - 1]</code> and <strong>swap</strong> the values of <code>nums1[i]</code> and <code>nums2[i]</code>.</p>

<p>Your task is to find the <strong>minimum</strong> number of operations required to satisfy the following conditions:</p>

<ul>
	<li><code>nums1[n - 1]</code> is equal to the <strong>maximum value</strong> among all elements of <code>nums1</code>, i.e., <code>nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1])</code>.</li>
	<li><code>nums2[n - 1]</code> is equal to the <strong>maximum</strong> <strong>value</strong> among all elements of <code>nums2</code>, i.e., <code>nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1])</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>minimum</strong> number of operations needed to meet <strong>both</strong> conditions</em>, <em>or </em><code>-1</code><em> if it is <strong>impossible</strong> to satisfy both conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,7], nums2 = [4,5,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, an operation can be performed using index i = 2.
When nums1[2] and nums2[2] are swapped, nums1 becomes [1,2,3] and nums2 becomes [4,5,7].
Both conditions are now satisfied.
It can be shown that the minimum number of operations needed to be performed is 1.
So, the answer is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,3,4,5,9], nums2 = [8,8,4,4,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, the following operations can be performed:
First operation using index i = 4.
When nums1[4] and nums2[4] are swapped, nums1 becomes [2,3,4,5,4], and nums2 becomes [8,8,4,4,9].
Another operation using index i = 3.
When nums1[3] and nums2[3] are swapped, nums1 becomes [2,3,4,4,4], and nums2 becomes [8,8,4,5,9].
Both conditions are now satisfied.
It can be shown that the minimum number of operations needed to be performed is 2.
So, the answer is 2.   
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,5,4], nums2 = [2,5,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> In this example, it is not possible to satisfy both conditions. 
So, the answer is -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length == nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
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
