# [786. K-th Smallest Prime Fraction](https://leetcode.com/problems/k-th-smallest-prime-fraction)

[中文文档](/solution/0700-0799/0786.K-th%20Smallest%20Prime%20Fraction/README.md)

## Description

<p>You are given a sorted integer array <code>arr</code> containing <code>1</code> and <strong>prime</strong> numbers, where all the integers of <code>arr</code> are unique. You are also given an integer <code>k</code>.</p>

<p>For every <code>i</code> and <code>j</code> where <code>0 &lt;= i &lt; j &lt; arr.length</code>, we consider the fraction <code>arr[i] / arr[j]</code>.</p>

<p>Return <em>the</em> <code>k<sup>th</sup></code> <em>smallest fraction considered</em>. Return your answer as an array of integers of size <code>2</code>, where <code>answer[0] == arr[i]</code> and <code>answer[1] == arr[j]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,5], k = 3
<strong>Output:</strong> [2,5]
<strong>Explanation:</strong> The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,7], k = 1
<strong>Output:</strong> [1,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr[0] == 1</code></li>
	<li><code>arr[i]</code> is a <strong>prime</strong> number for <code>i &gt; 0</code>.</li>
	<li>All the numbers of <code>arr</code> are <strong>unique</strong> and sorted in <strong>strictly increasing</strong> order.</li>
	<li><code>1 &lt;= k &lt;= arr.length * (arr.length - 1) / 2</code></li>
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
