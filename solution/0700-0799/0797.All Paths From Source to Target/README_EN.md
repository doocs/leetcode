# [797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target)

[中文文档](/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/README.md)

## Description

<p>Given a directed&nbsp;acyclic graph (<strong>DAG</strong>) of <code>n</code> nodes labeled from 0 to n - 1,&nbsp;find all possible paths from node <code>0</code> to node <code>n - 1</code>, and return them in any order.</p>

<p>The graph is given as follows:&nbsp;<code>graph[i]</code> is a list of all nodes you can visit from node <code>i</code>&nbsp;(i.e., there is a directed edge from node <code>i</code> to node <code>graph[i][j]</code>).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="/solution/0700-0799/0797.All Paths From Source to Target/images/all_1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> graph = [[1,2],[3],[3],[]]
<strong>Output:</strong> [[0,1,3],[0,2,3]]
<strong>Explanation:</strong> There are two paths: 0 -&gt; 1 -&gt; 3 and 0 -&gt; 2 -&gt; 3.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="/solution/0700-0799/0797.All Paths From Source to Target/images/all_2.jpg" style="width: 423px; height: 301px;" />
<pre>
<strong>Input:</strong> graph = [[4,3,1],[3,2,4],[3],[4],[]]
<strong>Output:</strong> [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> graph = [[1],[]]
<strong>Output:</strong> [[0,1]]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> graph = [[1,2,3],[2],[3],[]]
<strong>Output:</strong> [[0,1,2,3],[0,2,3],[0,3]]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> graph = [[1,3],[2],[3],[]]
<strong>Output:</strong> [[0,1,2,3],[0,3]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; n</code></li>
	<li><code>graph[i][j] != i</code> (i.e., there will be no self-loops).</li>
	<li>The input graph is <strong>guaranteed</strong> to be a <strong>DAG</strong>.</li>
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
