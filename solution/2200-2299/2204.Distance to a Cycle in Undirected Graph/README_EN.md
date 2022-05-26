# [2204. Distance to a Cycle in Undirected Graph](https://leetcode.com/problems/distance-to-a-cycle-in-undirected-graph)

[中文文档](/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/README.md)

## Description

<p>You are given a positive integer <code>n</code> representing the number of nodes in a <strong>connected undirected graph</strong> containing <strong>exactly one</strong> cycle. The nodes are numbered from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>).</p>

<p>You are also given a 2D integer array <code>edges</code>, where <code>edges[i] = [node1<sub>i</sub>, node2<sub>i</sub>]</code> denotes that there is a <strong>bidirectional</strong> edge connecting <code>node1<sub>i</sub></code> and <code>node2<sub>i</sub></code> in the graph.</p>

<p>The distance between two nodes <code>a</code> and <code>b</code> is defined to be the <strong>minimum</strong> number of edges that are needed to go from <code>a</code> to <code>b</code>.</p>

<p>Return <em>an integer array <code>answer</code></em><em> of size </em><code>n</code><em>, where </em><code>answer[i]</code><em> is the <strong>minimum</strong> distance between the </em><code>i<sup>th</sup></code><em> node and <strong>any</strong> node in the cycle.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/images/image-20220315154238-1.png" style="width: 350px; height: 237px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[1,2],[2,3],[3,4],[4,1],[0,1],[5,2],[6,5]]
<strong>Output:</strong> [1,0,0,0,0,1,2]
<strong>Explanation:</strong>
The nodes 1, 2, 3, and 4 form the cycle.
The distance from 0 to 1 is 1.
The distance from 1 to 1 is 0.
The distance from 2 to 2 is 0.
The distance from 3 to 3 is 0.
The distance from 4 to 4 is 0.
The distance from 5 to 2 is 1.
The distance from 6 to 2 is 2.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/images/image-20220315154634-1.png" style="width: 400px; height: 297px;" />
<pre>
<strong>Input:</strong> n = 9, edges = [[0,1],[1,2],[0,2],[2,6],[6,7],[6,8],[1,3],[3,4],[3,5]]
<strong>Output:</strong> [0,0,0,1,2,2,1,2,2]
<strong>Explanation:</strong>
The nodes 0, 1, and 2 form the cycle.
The distance from 0 to 0 is 0.
The distance from 1 to 1 is 0.
The distance from 2 to 2 is 0.
The distance from 3 to 1 is 1.
The distance from 4 to 1 is 2.
The distance from 5 to 1 is 2.
The distance from 6 to 2 is 1.
The distance from 7 to 2 is 2.
The distance from 8 to 2 is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= node1<sub>i</sub>, node2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>node1<sub>i</sub> != node2<sub>i</sub></code></li>
	<li>The graph is connected.</li>
	<li>The graph has exactly one cycle.</li>
	<li>There is at most one edge between any pair of vertices.</li>
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
