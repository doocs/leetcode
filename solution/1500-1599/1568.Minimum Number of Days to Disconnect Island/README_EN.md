# [1568. Minimum Number of Days to Disconnect Island](https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island)

[中文文档](/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/README.md)

## Description

<p>You are given an <code>m x n</code> binary grid <code>grid</code> where <code>1</code> represents land and <code>0</code> represents water. An <strong>island</strong> is a maximal <strong>4-directionally</strong> (horizontal or vertical) connected group of <code>1</code>&#39;s.</p>

<p>The grid is said to be <strong>connected</strong> if we have <strong>exactly one island</strong>, otherwise is said <strong>disconnected</strong>.</p>

<p>In one day, we are allowed to change <strong>any </strong>single land cell <code>(1)</code> into a water cell <code>(0)</code>.</p>

<p>Return <em>the minimum number of days to disconnect the grid</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/images/land1.jpg" style="width: 500px; height: 169px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]

<strong>Output:</strong> 2
<strong>Explanation:</strong> We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.

</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/images/land2.jpg" style="width: 404px; height: 85px;" />
<pre>
<strong>Input:</strong> grid = [[1,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Grid of full water is also disconnected ([[1,1]] -&gt; [[0,0]]), 0 islands.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 30</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
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
