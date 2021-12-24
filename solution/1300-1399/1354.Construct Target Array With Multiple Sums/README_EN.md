# [1354. Construct Target Array With Multiple Sums](https://leetcode.com/problems/construct-target-array-with-multiple-sums)

[中文文档](/solution/1300-1399/1354.Construct%20Target%20Array%20With%20Multiple%20Sums/README.md)

## Description

<p>Given an array of integers&nbsp;<code>target</code>. From a starting array, <code>A</code>&nbsp;consisting of all 1&#39;s, you may perform the following procedure :</p>

<ul>
	<li>let <code>x</code> be the sum of all elements currently in your array.</li>
	<li>choose index <code>i</code>, such that&nbsp;<code>0 &lt;= i &lt; target.size</code> and set the value of <code>A</code> at index <code>i</code> to <code>x</code>.</li>
	<li>You may repeat this procedure&nbsp;as many times as needed.</li>
</ul>

<p>Return True if it is possible to construct the <code>target</code> array from <code>A</code> otherwise&nbsp;return False.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [9,3,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> Start with [1, 1, 1] 
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [1,1,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> Impossible to create target array from [1,1,1,1].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [8,5]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>N == target.length</code></li>
	<li><code>1 &lt;= target.length&nbsp;&lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= target[i] &lt;= 10^9</code></li>
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
