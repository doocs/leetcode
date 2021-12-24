# [1568. Minimum Number of Days to Disconnect Island](https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island)

[中文文档](/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/README.md)

## Description

<p>Given a 2D&nbsp;<code>grid</code> consisting&nbsp;of <code>1</code>s (land)&nbsp;and <code>0</code>s (water).&nbsp; An <em>island</em> is a maximal 4-directionally (horizontal or vertical) connected group of <code>1</code>s.</p>

<p>The grid is said to be <strong>connected</strong> if we have <strong>exactly one&nbsp;island</strong>, otherwise is said <strong>disconnected</strong>.</p>

<p>In one day, we are allowed to change <strong>any </strong>single land cell <code>(1)</code> into a water cell <code>(0)</code>.</p>

<p>Return <em>the minimum number of days</em> to disconnect the grid.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/images/1926_island.png" style="width: 498px; height: 139px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>Grid of full water is also disconnected ([[1,1]] -&gt; [[0,0]]), 0 islands.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,1,0]]
<strong>Output:</strong> 0
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,0,1,1],
&nbsp;              [1,1,1,1,1],
&nbsp;              [1,1,0,1,1],
&nbsp;              [1,1,0,1,1]]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,0,1,1],
&nbsp;              [1,1,1,1,1],
&nbsp;              [1,1,0,1,1],
&nbsp;              [1,1,1,1,1]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 30</code></li>
	<li><code>grid[i][j]</code>&nbsp;is <code>0</code> or <code>1</code>.</li>
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
