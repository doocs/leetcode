# [2508. Add Edges to Make Degrees of All Nodes Even](https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even)

[中文文档](/solution/2500-2599/2508.Add%20Edges%20to%20Make%20Degrees%20of%20All%20Nodes%20Even/README.md)

## Description

<p>There is an <strong>undirected</strong> graph consisting of <code>n</code> nodes numbered from <code>1</code> to <code>n</code>. You are given the integer <code>n</code> and a <strong>2D</strong> array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. The graph can be disconnected.</p>

<p>You can add <strong>at most</strong> two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.</p>

<p>Return <code>true</code><em> if it is possible to make the degree of each node in the graph even, otherwise return </em><code>false</code><em>.</em></p>

<p>The degree of a node is the number of edges connected to it.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2508.Add%20Edges%20to%20Make%20Degrees%20of%20All%20Nodes%20Even/images/agraphdrawio.png" style="width: 500px; height: 190px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The above diagram shows a valid way of adding an edge.
Every node in the resulting graph is connected to an even number of edges.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2508.Add%20Edges%20to%20Make%20Degrees%20of%20All%20Nodes%20Even/images/aagraphdrawio.png" style="width: 400px; height: 120px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[1,2],[3,4]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The above diagram shows a valid way of adding two edges.</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2508.Add%20Edges%20to%20Make%20Degrees%20of%20All%20Nodes%20Even/images/aaagraphdrawio.png" style="width: 150px; height: 158px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[1,2],[1,3],[1,4]]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is not possible to obtain a valid graph with adding at most 2 edges.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
