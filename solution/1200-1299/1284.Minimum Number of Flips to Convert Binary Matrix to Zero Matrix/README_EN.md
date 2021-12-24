# [1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix](https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix)

[中文文档](/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/README.md)

## Description

<p>Given a <code>m x n</code> binary matrix <code>mat</code>. In one step, you can choose one cell and flip it and all the four neighbours of it&nbsp;if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.</p>

<p>Return the <em>minimum number of steps</em> required to convert <code>mat</code>&nbsp;to a zero matrix or <strong>-1</strong> if you cannot.</p>

<p>Binary matrix is a matrix with all cells equal to 0 or 1 only.</p>

<p>Zero matrix is a matrix with all cells equal to 0.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/images/matrix.png" style="width: 409px; height: 86px;" />
<pre>
<strong>Input:</strong> mat = [[0,0],[0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Given matrix is a zero matrix. We don&#39;t need to change it.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,1,1],[1,0,1],[0,0,0]]
<strong>Output:</strong> 6
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,0,0],[1,0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Given matrix can&#39;t be a zero matrix
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;mat.length</code></li>
	<li><code>n ==&nbsp;mat[0].length</code></li>
	<li><code>1 &lt;= m&nbsp;&lt;= 3</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 3</code></li>
	<li><code>mat[i][j]</code> is 0 or 1.</li>
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
