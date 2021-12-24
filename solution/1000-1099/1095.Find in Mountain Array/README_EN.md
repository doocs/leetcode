# [1095. Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array)

[中文文档](/solution/1000-1099/1095.Find%20in%20Mountain%20Array/README.md)

## Description

<p><em>(This problem is an&nbsp;<strong>interactive problem</strong>.)</em></p>

<p>You may recall that an array&nbsp;<code>A</code> is a <em>mountain array</em> if and only if:</p>

<ul>
	<li><code>A.length &gt;= 3</code></li>
	<li>There exists some&nbsp;<code>i</code>&nbsp;with&nbsp;<code>0 &lt; i&nbsp;&lt; A.length - 1</code>&nbsp;such that:
	<ul>
		<li><code>A[0] &lt; A[1] &lt; ... A[i-1] &lt; A[i]</code></li>
		<li><code>A[i] &gt; A[i+1] &gt; ... &gt; A[A.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>Given a mountain&nbsp;array <code>mountainArr</code>, return the <strong>minimum</strong>&nbsp;<code>index</code> such that <code>mountainArr.get(index) == target</code>.&nbsp; If such an <code>index</code>&nbsp;doesn&#39;t exist, return <code>-1</code>.</p>

<p><strong>You can&#39;t access the mountain array directly.</strong>&nbsp; You may only access the array using a&nbsp;<code>MountainArray</code>&nbsp;interface:</p>

<ul>
	<li><code>MountainArray.get(k)</code> returns the element of the array at index <code>k</code>&nbsp;(0-indexed).</li>
	<li><code>MountainArray.length()</code>&nbsp;returns the length of the array.</li>
</ul>

<p>Submissions making more than <code>100</code> calls to&nbsp;<code>MountainArray.get</code>&nbsp;will be judged <em>Wrong Answer</em>.&nbsp; Also, any solutions that attempt to circumvent the judge&nbsp;will result in disqualification.</p>

<ol>
</ol>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> array = [1,2,3,4,5,3,1], target = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> array = [0,1,2,4,2,1], target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> 3 does not exist in <code>the array,</code> so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= mountain_arr.length() &lt;= 10000</code></li>
	<li><code>0 &lt;= target &lt;= 10^9</code></li>
	<li><code>0 &lt;= mountain_arr.get(index) &lt;=&nbsp;10^9</code></li>
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
