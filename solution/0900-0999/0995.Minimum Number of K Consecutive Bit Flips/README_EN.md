# [995. Minimum Number of K Consecutive Bit Flips](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips)

[中文文档](/solution/0900-0999/0995.Minimum%20Number%20of%20K%20Consecutive%20Bit%20Flips/README.md)

## Description

<p>In an array <code>A</code> containing only 0s and 1s, a <i><code>K</code>-bit flip&nbsp;</i>consists of choosing a (contiguous) subarray of length <code>K</code> and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.</p>

<p>Return the minimum number of <code>K</code>-bit flips required so that there is no 0 in the array.&nbsp; If it is not possible, return <code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">[0,1,0]</span>, K = <span id="example-input-1-2">1</span>

<strong>Output: </strong><span id="example-output-1">2</span>

<strong>Explanation: </strong>Flip A[0], then flip A[2].

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-2-1">[1,1,0]</span>, K = <span id="example-input-2-2">2</span>

<strong>Output: </strong><span id="example-output-2">-1</span>

<strong>Explanation:</strong>&nbsp;No matter how we flip subarrays of size 2, we can&#39;t make the array become [1,1,1].

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-3-1">[0,0,0,1,0,1,1,0]</span>, K = <span id="example-input-3-2">3</span>

<strong>Output: </strong><span id="example-output-3">3</span>

<strong>Explanation:</strong>

Flip A[0],A[1],A[2]:&nbsp;A becomes [1,1,1,1,0,1,1,0]

Flip A[4],A[5],A[6]:&nbsp;A becomes [1,1,1,1,1,0,0,0]

Flip A[5],A[6],A[7]:&nbsp;A becomes [1,1,1,1,1,1,1,1]

</pre>

<p>&nbsp;</p>

</div>

</div>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;=&nbsp;30000</code></li>
	<li><code>1 &lt;= K &lt;= A.length</code></li>
</ol>

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
