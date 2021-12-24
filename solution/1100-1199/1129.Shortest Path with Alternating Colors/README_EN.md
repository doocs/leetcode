# [1129. Shortest Path with Alternating Colors](https://leetcode.com/problems/shortest-path-with-alternating-colors)

[中文文档](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README.md)

## Description

<p>Consider a directed graph, with nodes labelled <code>0, 1, ..., n-1</code>.&nbsp; In this graph, each edge is either red or blue, and there could&nbsp;be self-edges or parallel edges.</p>

<p>Each <code>[i, j]</code> in <code>red_edges</code> denotes a red directed edge from node <code>i</code> to node <code>j</code>.&nbsp; Similarly, each <code>[i, j]</code> in <code>blue_edges</code> denotes a blue directed edge from node <code>i</code> to node <code>j</code>.</p>

<p>Return an array <code>answer</code>&nbsp;of length <code>n</code>,&nbsp;where each&nbsp;<code>answer[X]</code>&nbsp;is&nbsp;the length of the shortest path from node <code>0</code>&nbsp;to node <code>X</code>&nbsp;such that the edge colors alternate along the path (or <code>-1</code> if such a path doesn&#39;t exist).</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> n = 3, red_edges = [[0,1],[1,2]], blue_edges = []

<strong>Output:</strong> [0,1,-1]

</pre><p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]

<strong>Output:</strong> [0,1,-1]

</pre><p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]

<strong>Output:</strong> [0,-1,-1]

</pre><p><strong>Example 4:</strong></p>

<pre><strong>Input:</strong> n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]

<strong>Output:</strong> [0,1,2]

</pre><p><strong>Example 5:</strong></p>

<pre><strong>Input:</strong> n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]

<strong>Output:</strong> [0,1,1]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>red_edges.length &lt;= 400</code></li>
	<li><code>blue_edges.length &lt;= 400</code></li>
	<li><code>red_edges[i].length == blue_edges[i].length == 2</code></li>
	<li><code>0 &lt;= red_edges[i][j], blue_edges[i][j] &lt; n</code></li>
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
