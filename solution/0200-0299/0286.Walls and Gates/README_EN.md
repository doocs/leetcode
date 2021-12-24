# [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates)

[中文文档](/solution/0200-0299/0286.Walls%20and%20Gates/README.md)

## Description

<p>You are given an <code>m x n</code> grid <code>rooms</code>&nbsp;initialized with these three possible values.</p>

<ul>
	<li><code>-1</code>&nbsp;A wall or an obstacle.</li>
	<li><code>0</code> A gate.</li>
	<li><code>INF</code> Infinity means an empty room. We use the value <code>2<sup>31</sup> - 1 = 2147483647</code> to represent <code>INF</code> as you may assume that the distance to a gate is less than <code>2147483647</code>.</li>
</ul>

<p>Fill each empty room with the distance to <em>its nearest gate</em>. If it is impossible to reach a gate, it should be filled with <code>INF</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0286.Walls%20and%20Gates/images/grid.jpg" style="width: 500px; height: 223px;" />
<pre>
<strong>Input:</strong> rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
<strong>Output:</strong> [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[-1]]
<strong>Output:</strong> [[-1]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[2147483647]]
<strong>Output:</strong> [[2147483647]]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[0]]
<strong>Output:</strong> [[0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == rooms.length</code></li>
	<li><code>n == rooms[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 250</code></li>
	<li><code>rooms[i][j]</code> is <code>-1</code>, <code>0</code>, or <code>2<sup>31</sup> - 1</code>.</li>
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
