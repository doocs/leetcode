# [1129. Shortest Path with Alternating Colors](https://leetcode.com/problems/shortest-path-with-alternating-colors)

[中文文档](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README.md)

## Description

<p>You are given an integer <code>n</code>, the number of nodes in a directed graph where the nodes are labeled from <code>0</code> to <code>n - 1</code>. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.</p>

<p>You are given two arrays <code>redEdges</code> and <code>blueEdges</code> where:</p>

<ul>
	<li><code>redEdges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is a directed red edge from node <code>a<sub>i</sub></code> to node <code>b<sub>i</sub></code> in the graph, and</li>
	<li><code>blueEdges[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> indicates that there is a directed blue edge from node <code>u<sub>j</sub></code> to node <code>v<sub>j</sub></code> in the graph.</li>
</ul>

<p>Return an array <code>answer</code> of length <code>n</code>, where each <code>answer[x]</code> is the length of the shortest path from node <code>0</code> to node <code>x</code> such that the edge colors alternate along the path, or <code>-1</code> if such a path does not exist.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
<strong>Output:</strong> [0,1,-1]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
<strong>Output:</strong> [0,1,-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= redEdges.length,&nbsp;blueEdges.length &lt;= 400</code></li>
	<li><code>redEdges[i].length == blueEdges[j].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>, u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
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
