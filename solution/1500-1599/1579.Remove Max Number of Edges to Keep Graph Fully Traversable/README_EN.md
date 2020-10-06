# [1579. Remove Max Number of Edges to Keep Graph Fully Traversable](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable)

[中文文档](/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/README.md)

## Description

<p>Alice and Bob have an undirected graph of&nbsp;<code>n</code>&nbsp;nodes&nbsp;and 3 types of edges:</p>

<ul>
	<li>Type 1: Can be traversed by Alice only.</li>
	<li>Type 2: Can be traversed by Bob only.</li>
	<li>Type 3: Can by traversed by both Alice and Bob.</li>
</ul>

<p>Given an array&nbsp;<code>edges</code>&nbsp;where&nbsp;<code>edges[i] = [type<sub>i</sub>, u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;represents a bidirectional edge of type&nbsp;<code>type<sub>i</sub></code>&nbsp;between nodes&nbsp;<code>u<sub>i</sub></code>&nbsp;and&nbsp;<code>v<sub>i</sub></code>, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.</p>

<p>Return <em>the maximum number of edges you can remove, or return</em> <code>-1</code> <em>if it&#39;s impossible for the graph to be fully traversed by Alice and Bob.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/08/19/ex1.png" style="width: 179px; height: 191px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/08/19/ex2.png" style="width: 178px; height: 190px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
<strong>Output:</strong> 0
<strong>Explanation: </strong>Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/08/19/ex3.png" style="width: 178px; height: 190px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
<strong>Output:</strong> -1
<b>Explanation: </b>In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it&#39;s impossible to make the graph fully traversable.</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= edges.length &lt;= min(10^5, 3 * n * (n-1) / 2)</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 &lt;= edges[i][0] &lt;= 3</code></li>
	<li><code>1 &lt;= edges[i][1] &lt; edges[i][2] &lt;= n</code></li>
	<li>All tuples&nbsp;<code>(type<sub>i</sub>, u<sub>i</sub>, v<sub>i</sub>)</code>&nbsp;are distinct.</li>
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