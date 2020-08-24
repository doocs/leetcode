# [1059. All Paths from Source Lead to Destination](https://leetcode.com/problems/all-paths-from-source-lead-to-destination)

[中文文档](/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/README.md)

## Description
<p>Given the <code>edges</code> of a directed graph, and two nodes <code>source</code> and <code>destination</code> of this graph, determine whether or not all paths starting from <code>source</code> eventually end at <code>destination</code>, that is:</p>

<ul>
	<li>At least one path exists from the <code>source</code> node to the <code>destination</code> node</li>
	<li>If a path exists from the <code>source</code> node to a node with no outgoing edges, then that node is equal to <code>destination</code>.</li>
	<li>The number of possible paths from <code>source</code> to <code>destination</code> is a finite number.</li>
</ul>

<p>Return <code>true</code> if and only if all roads from <code>source</code> lead to <code>destination</code>.</p>

<p> </p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/16/485_example_1.png" style="width: 200px; height: 208px;" /></p>

<pre>
<strong>Input: </strong>n = 3, edges = <span id="example-input-1-2">[[0,1],[0,2]]</span>, source = <span id="example-input-1-3">0</span>, destination = 2
<strong>Output: </strong><span id="example-output-1">false</span>
<strong>Explanation: </strong>It is possible to reach and get stuck on both node 1 and node 2.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/16/485_example_2.png" style="width: 200px; height: 230px;" /></p>

<pre>
<strong>Input: </strong>n = <span id="example-input-2-1">4</span>, edges = <span id="example-input-2-2">[[0,1],[0,3],[1,2],[2,1]]</span>, source = <span id="example-input-2-3">0</span>, destination = <span id="example-input-2-4">3</span>
<strong>Output: </strong><span id="example-output-2">false</span>
<strong>Explanation: </strong>We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
</pre>

<p><strong>Example 3:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/16/485_example_3.png" style="width: 200px; height: 183px;" /></p>

<pre>
<strong>Input: </strong>n = <span id="example-input-3-1">4</span>, edges = <span id="example-input-3-2">[[0,1],[0,2],[1,3],[2,3]]</span>, source = <span id="example-input-3-3">0</span>, destination = <span id="example-input-3-4">3</span>
<strong>Output: </strong><span id="example-output-3">true</span>
</pre>

<p><strong>Example 4:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/16/485_example_4.png" style="width: 200px; height: 183px;" /></p>

<pre>
<strong>Input: </strong>n = <span id="example-input-4-1">3</span>, edges = <span id="example-input-4-2">[[0,1],[1,1],[1,2]]</span>, source = <span id="example-input-4-3">0</span>, destination = <span id="example-input-4-4">2</span>
<strong>Output: </strong><span id="example-output-4">false</span>
<strong>Explanation: </strong>All paths from the source node end at the destination node, but there are an infinite number of paths, such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.
</pre>

<p><strong>Example 5:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/16/485_example_5.png" style="width: 150px; height: 131px;" /></p>

<pre>
<strong>Input: </strong>n = <span id="example-input-5-1">2</span>, edges = <span id="example-input-5-2">[[0,1],[1,1]]</span>, source = <span id="example-input-5-3">0</span>, destination = <span id="example-input-5-4">1</span>
<strong>Output: </strong><span id="example-output-5">false</span>
<strong>Explanation: </strong>There is infinite self-loop at destination node.
</pre>

<p> </p>

<p><strong>Note:</strong></p>

<ol>
	<li><italic>The given graph may have self loops and parallel edges.</italic></li>
	<li>The number of nodes <code>n</code> in the graph is between <code>1</code> and <code>10000</code></li>
	<li>The number of edges in the graph is between <code>0</code> and <code>10000</code></li>
	<li><code>0 <= edges.length <= 10000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 <= source <= n - 1</code></li>
	<li><code>0 <= destination <= n - 1</code></li>
</ol>



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