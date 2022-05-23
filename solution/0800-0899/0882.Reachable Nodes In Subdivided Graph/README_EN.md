# [882. Reachable Nodes In Subdivided Graph](https://leetcode.com/problems/reachable-nodes-in-subdivided-graph)

[中文文档](/solution/0800-0899/0882.Reachable%20Nodes%20In%20Subdivided%20Graph/README.md)

## Description

<p>You are given an undirected graph (the <strong>&quot;original graph&quot;</strong>) with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You decide to <strong>subdivide</strong> each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.</p>

<p>The graph is given as a 2D array of <code>edges</code> where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cnt<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the original graph, and <code>cnt<sub>i</sub></code> is the total number of new nodes that you will <strong>subdivide</strong> the edge into. Note that <code>cnt<sub>i</sub> == 0</code> means you will not subdivide the edge.</p>

<p>To <strong>subdivide</strong> the edge <code>[u<sub>i</sub>, v<sub>i</sub>]</code>, replace it with <code>(cnt<sub>i</sub> + 1)</code> new edges and <code>cnt<sub>i</sub></code> new nodes. The new nodes are <code>x<sub>1</sub></code>, <code>x<sub>2</sub></code>, ..., <code>x<sub>cnt<sub>i</sub></sub></code>, and the new edges are <code>[u<sub>i</sub>, x<sub>1</sub>]</code>, <code>[x<sub>1</sub>, x<sub>2</sub>]</code>, <code>[x<sub>2</sub>, x<sub>3</sub>]</code>, ..., <code>[x<sub>cnt<sub>i</sub>-1</sub>, x<sub>cnt<sub>i</sub></sub>]</code>, <code>[x<sub>cnt<sub>i</sub></sub>, v<sub>i</sub>]</code>.</p>

<p>In this <strong>new graph</strong>, you want to know how many nodes are <strong>reachable</strong> from the node <code>0</code>, where a node is <strong>reachable</strong> if the distance is <code>maxMoves</code> or less.</p>

<p>Given the original graph and <code>maxMoves</code>, return <em>the number of nodes that are <strong>reachable</strong> from node </em><code>0</code><em> in the new graph</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0882.Reachable%20Nodes%20In%20Subdivided%20Graph/images/origfinal.png" style="width: 600px; height: 247px;" />
<pre>
<strong>Input:</strong> edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
<strong>Output:</strong> 13
<strong>Explanation:</strong> The edge subdivisions are shown in the image above.
The nodes that are reachable are highlighted in yellow.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
<strong>Output:</strong> 23
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= edges.length &lt;= min(n * (n - 1) / 2, 10<sup>4</sup>)</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub> &lt; v<sub>i</sub> &lt; n</code></li>
	<li>There are <strong>no multiple edges</strong> in the graph.</li>
	<li><code>0 &lt;= cnt<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= maxMoves &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n &lt;= 3000</code></li>
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
