# [1950. Maximum of Minimum Values in All Subarrays](https://leetcode.com/problems/maximum-of-minimum-values-in-all-subarrays)

[中文文档](/solution/1900-1999/1950.Maximum%20of%20Minimum%20Values%20in%20All%20Subarrays/README.md)

## Description

<p>You are given an integer array <code>nums</code> of size <code>n</code>. You are asked to solve <code>n</code> queries for each integer <code>i</code> in the range <code>0 &lt;= i &lt; n</code>.</p>

<p>To solve the <code>i<sup>th</sup></code> query:</p>

<ol>
	<li>Find the <strong>minimum value</strong> in each possible subarray of size <code>i + 1</code> of the array <code>nums</code>.</li>
	<li>Find the <strong>maximum</strong> of those minimum values. This maximum is the <strong>answer</strong> to the query.</li>
</ol>

<p>Return <em>a <strong>0-indexed</strong> integer array</em> <code>ans</code> <em>of size </em><code>n</code> <em>such that </em><code>ans[i]</code> <em>is the answer to the </em><code>i<sup>th</sup></code> <em>query</em>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements in an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,4]
<strong>Output:</strong> [4,2,1,0]
<strong>Explanation:</strong>
i=0:
- The subarrays of size 1 are [0], [1], [2], [4]. The minimum values are 0, 1, 2, 4.
- The maximum of the minimum values is 4.
i=1:
- The subarrays of size 2 are [0,1], [1,2], [2,4]. The minimum values are 0, 1, 2.
- The maximum of the minimum values is 2.
i=2:
- The subarrays of size 3 are [0,1,2], [1,2,4]. The minimum values are 0, 1.
- The maximum of the minimum values is 1.
i=3:
- There is one subarray of size 4, which is [0,1,2,4]. The minimum value is 0.
- There is only one value, so the maximum is 0.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,50,10]
<strong>Output:</strong> [50,20,10,10]
<strong>Explanation:</strong>
i=0:
- The subarrays of size 1 are [10], [20], [50], [10]. The minimum values are 10, 20, 50, 10.
- The maximum of the minimum values is 50.
i=1:
- The subarrays of size 2 are [10,20], [20,50], [50,10]. The minimum values are 10, 20, 10.
- The maximum of the minimum values is 20.
i=2:
- The subarrays of size 3 are [10,20,50], [20,50,10]. The minimum values are 10, 10.
- The maximum of the minimum values is 10.
i=3:
- There is one subarray of size 4, which is [10,20,50,10]. The minimum value is 10.
- There is only one value, so the maximum is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
