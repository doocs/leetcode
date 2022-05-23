# [1519. Number of Nodes in the Sub-Tree With the Same Label](https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label)

[中文文档](/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/README.md)

## Description

<p>You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> and exactly <code>n - 1</code> <code>edges</code>. The <strong>root</strong> of the tree is the node <code>0</code>, and each node of the tree has <strong>a label</strong> which is a lower-case character given in the string <code>labels</code> (i.e. The node with the number <code>i</code> has the label <code>labels[i]</code>).</p>

<p>The <code>edges</code> array is given on the form <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>, which means there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Return <em>an array of size <code>n</code></em> where <code>ans[i]</code> is the number of nodes in the subtree of the <code>i<sup>th</sup></code> node which have the same label as node <code>i</code>.</p>

<p>A subtree of a tree <code>T</code> is the tree consisting of a node in <code>T</code> and all of its descendant nodes.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e1.jpg" style="width: 400px; height: 291px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = &quot;abaedcd&quot;
<strong>Output:</strong> [2,1,1,1,1,1,1]
<strong>Explanation:</strong> Node 0 has label &#39;a&#39; and its sub-tree has node 2 with label &#39;a&#39; as well, thus the answer is 2. Notice that any node is part of its sub-tree.
Node 1 has a label &#39;b&#39;. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e2.jpg" style="width: 300px; height: 253px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[1,2],[0,3]], labels = &quot;bbbb&quot;
<strong>Output:</strong> [4,2,1,1]
<strong>Explanation:</strong> The sub-tree of node 2 contains only node 2, so the answer is 1.
The sub-tree of node 3 contains only node 3, so the answer is 1.
The sub-tree of node 1 contains nodes 1 and 2, both have label &#39;b&#39;, thus the answer is 2.
The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label &#39;b&#39;, thus the answer is 4.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e3.jpg" style="width: 300px; height: 253px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = &quot;aabab&quot;
<strong>Output:</strong> [3,2,1,1,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>labels.length == n</code></li>
	<li><code>labels</code> is consisting of only of lowercase English letters.</li>
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
