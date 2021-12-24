# [1293. Shortest Path in a Grid with Obstacles Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination)

[中文文档](/solution/1200-1299/1293.Shortest%20Path%20in%20a%20Grid%20with%20Obstacles%20Elimination/README.md)

## Description

<p>Given a <code>m * n</code> grid, where each cell is either <code>0</code> (empty)&nbsp;or <code>1</code> (obstacle).&nbsp;In one step, you can move up, down, left or right from and to an empty cell.</p>

<p>Return the minimum number of steps to walk from the upper left corner&nbsp;<code>(0, 0)</code>&nbsp;to the lower right corner&nbsp;<code>(m-1, n-1)</code> given that you can eliminate&nbsp;<strong>at most</strong> <code>k</code> obstacles. If it is not possible to find such&nbsp;walk return -1.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
grid = 
[[0,0,0],
&nbsp;[1,1,0],
 [0,0,0],
&nbsp;[0,1,1],
 [0,0,0]], 
k = 1
<strong>Output:</strong> 6
<strong>Explanation: 
</strong>The shortest path without eliminating any obstacle is 10.&nbsp;
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is <code>(0,0) -&gt; (0,1) -&gt; (0,2) -&gt; (1,2) -&gt; (2,2) -&gt; <strong>(3,2)</strong> -&gt; (4,2)</code>.
</pre>

<p>&nbsp;</p>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
grid = 
[[0,1,1],
&nbsp;[1,1,1],
&nbsp;[1,0,0]], 
k = 1
<strong>Output:</strong> -1
<strong>Explanation: 
</strong>We need to eliminate at least two obstacles to find such a walk.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length&nbsp;== m</code></li>
	<li><code>grid[0].length&nbsp;== n</code></li>
	<li><code>1 &lt;= m, n &lt;= 40</code></li>
	<li><code>1 &lt;= k &lt;= m*n</code></li>
	<li><code>grid[i][j] == 0 <strong>or</strong> 1</code></li>
	<li><code>grid[0][0] == grid[m-1][n-1] == 0</code></li>
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
