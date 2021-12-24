# [1786. Number of Restricted Paths From First to Last Node](https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node)

[中文文档](/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/README.md)

## Description

<p>There is an undirected weighted connected graph. You are given a positive integer <code>n</code> which denotes that the graph has <code>n</code> nodes labeled from <code>1</code> to <code>n</code>, and an array <code>edges</code> where each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, weight<sub>i</sub>]</code> denotes that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with weight equal to <code>weight<sub>i</sub></code>.</p>

<p>A path from node <code>start</code> to node <code>end</code> is a sequence of nodes <code>[z<sub>0</sub>, z<sub>1</sub>,<sub> </sub>z<sub>2</sub>, ..., z<sub>k</sub>]</code> such that <code>z<sub>0 </sub>= start</code> and <code>z<sub>k</sub> = end</code> and there is an edge between <code>z<sub>i</sub></code> and <code>z<sub>i+1</sub></code> where <code>0 &lt;= i &lt;= k-1</code>.</p>

<p>The distance of a path is the sum of the weights on the edges of the path. Let <code>distanceToLastNode(x)</code> denote the shortest distance of a path between node <code>n</code> and node <code>x</code>. A <strong>restricted path</strong> is a path that also satisfies that <code>distanceToLastNode(z<sub>i</sub>) &gt; distanceToLastNode(z<sub>i+1</sub>)</code> where <code>0 &lt;= i &lt;= k-1</code>.</p>

<p>Return <em>the number of restricted paths from node</em> <code>1</code> <em>to node</em> <code>n</code>. Since that number may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex1.png" style="width: 351px; height: 341px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Each circle contains the node number in black and its <code>distanceToLastNode value in blue. </code>The three restricted paths are:
1) 1 --&gt; 2 --&gt; 5
2) 1 --&gt; 2 --&gt; 3 --&gt; 5
3) 1 --&gt; 3 --&gt; 5
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex22.png" style="width: 356px; height: 401px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Each circle contains the node number in black and its <code>distanceToLastNode value in blue. </code>The only restricted path is 1 --&gt; 3 --&gt; 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 &lt;= weight<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>There is at most one edge between any two nodes.</li>
	<li>There is at least one path between any two nodes.</li>
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
