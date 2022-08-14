# [2277. Closest Node to Path in Tree](https://leetcode.com/problems/closest-node-to-path-in-tree)

[中文文档](/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/README.md)

## Description

<p>You are given a positive integer <code>n</code> representing the number of nodes in a tree, numbered from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>). You are also given a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [node1<sub>i</sub>, node2<sub>i</sub>]</code> denotes that there is a <strong>bidirectional</strong> edge connecting <code>node1<sub>i</sub></code> and <code>node2<sub>i</sub></code> in the tree.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>query</code> of length <code>m</code> where <code>query[i] = [start<sub>i</sub>, end<sub>i</sub>, node<sub>i</sub>]</code> means that for the <code>i<sup>th</sup></code> query, you are tasked with finding the node on the path from <code>start<sub>i</sub></code> to <code>end<sub>i</sub></code> that is <strong>closest</strong> to <code>node<sub>i</sub></code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> of length </em><code>m</code><em>, where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/images/image-20220514132158-1.png" style="width: 300px; height: 211px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]], query = [[5,3,4],[5,3,6]]
<strong>Output:</strong> [0,2]
<strong>Explanation:</strong>
The path from node 5 to node 3 consists of the nodes 5, 2, 0, and 3.
The distance between node 4 and node 0 is 2.
Node 0 is the node on the path closest to node 4, so the answer to the first query is 0.
The distance between node 6 and node 2 is 1.
Node 2 is the node on the path closest to node 6, so the answer to the second query is 2.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/images/image-20220514132318-2.png" style="width: 300px; height: 89px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[1,2]], query = [[0,1,2]]
<strong>Output:</strong> [1]
<strong>Explanation:</strong>
The path from node 0 to node 1 consists of the nodes 0, 1.
The distance between node 2 and node 1 is 1.
Node 1 is the node on the path closest to node 2, so the answer to the first query is 1.
</pre>

<p><strong>Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/images/image-20220514132333-3.png" style="width: 300px; height: 89px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[1,2]], query = [[0,0,0]]
<strong>Output:</strong> [0]
<strong>Explanation:</strong>
The path from node 0 to node 0 consists of the node 0.
Since 0 is the only node on the path, the answer to the first query is 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= node1<sub>i</sub>, node2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>node1<sub>i</sub> != node2<sub>i</sub></code></li>
	<li><code>1 &lt;= query.length &lt;= 1000</code></li>
	<li><code>query[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub>, end<sub>i</sub>, node<sub>i</sub> &lt;= n - 1</code></li>
	<li>The graph is a tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
