# [2203. Minimum Weighted Subgraph With the Required Paths](https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths)

[中文文档](/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/README.md)

## Description

<p>You are given an integer <code>n</code> denoting the number of nodes of a <strong>weighted directed</strong> graph. The nodes are numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>You are also given a 2D integer array <code>edges</code> where <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code> denotes that there exists a <strong>directed</strong> edge from <code>from<sub>i</sub></code> to <code>to<sub>i</sub></code> with weight <code>weight<sub>i</sub></code>.</p>

<p>Lastly, you are given three <strong>distinct</strong> integers <code>src1</code>, <code>src2</code>, and <code>dest</code> denoting three distinct nodes of the graph.</p>

<p>Return <em>the <strong>minimum weight</strong> of a subgraph of the graph such that it is <strong>possible</strong> to reach</em> <code>dest</code> <em>from both</em> <code>src1</code> <em>and</em> <code>src2</code> <em>via a set of edges of this subgraph</em>. In case such a subgraph does not exist, return <code>-1</code>.</p>

<p>A <strong>subgraph</strong> is a graph whose vertices and edges are subsets of the original graph. The <strong>weight</strong> of a subgraph is the sum of weights of its constituent edges.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/images/example1drawio.png" style="width: 263px; height: 250px;" />
<pre>
<strong>Input:</strong> n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The above figure represents the input graph.
The blue edges represent one of the subgraphs that yield the optimal answer.
Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It is not possible to get a subgraph with less weight satisfying all the constraints.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/images/example2-1drawio.png" style="width: 350px; height: 51px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong>
The above figure represents the input graph.
It can be seen that there does not exist any path from node 1 to node 2, hence there are no subgraphs satisfying all the constraints.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub>, src1, src2, dest &lt;= n - 1</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>src1</code>, <code>src2</code>, and <code>dest</code> are pairwise distinct.</li>
	<li><code>1 &lt;= weight[i] &lt;= 10<sup>5</sup></code></li>
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
