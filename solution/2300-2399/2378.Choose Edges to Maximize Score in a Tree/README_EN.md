# [2378. Choose Edges to Maximize Score in a Tree](https://leetcode.com/problems/choose-edges-to-maximize-score-in-a-tree)

[中文文档](/solution/2300-2399/2378.Choose%20Edges%20to%20Maximize%20Score%20in%20a%20Tree/README.md)

## Description

<p>You are given a <strong>weighted</strong> tree consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>The tree is <strong>rooted</strong> at node <code>0</code> and represented with a <strong>2D</strong> array <code>edges</code> of size <code>n</code> where <code>edges[i] = [par<sub>i</sub>, weight<sub>i</sub>]</code> indicates that node <code>par<sub>i</sub></code> is the <strong>parent</strong> of node <code>i</code>, and the edge between them has a weight equal to <code>weight<sub>i</sub></code>. Since the root does <strong>not</strong> have a parent, you have <code>edges[0] = [-1, -1]</code>.</p>

<p>Choose some edges from the tree such that no two chosen edges are <strong>adjacent</strong> and the <strong>sum</strong> of the weights of the chosen edges is maximized.</p>

<p>Return <em>the <strong>maximum</strong> sum of the chosen edges</em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>You are allowed to <strong>not</strong> choose any edges in the tree, the sum of weights in this case will be <code>0</code>.</li>
	<li>Two edges <code>Edge<sub>1</sub></code> and <code>Edge<sub>2</sub></code> in the tree are <strong>adjacent</strong> if they have a <strong>common</strong> node.
	<ul>
		<li>In other words, they are adjacent if <code>Edge<sub>1</sub></code> connects nodes <code>a</code> and <code>b</code> and <code>Edge<sub>2</sub></code> connects nodes <code>b</code> and <code>c</code>.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2378.Choose%20Edges%20to%20Maximize%20Score%20in%20a%20Tree/images/treedrawio.png" style="width: 271px; height: 221px;" />
<pre>
<strong>Input:</strong> edges = [[-1,-1],[0,5],[0,10],[2,6],[2,4]]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The above diagram shows the edges that we have to choose colored in red.
The total score is 5 + 6 = 11.
It can be shown that no better score can be obtained.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2378.Choose%20Edges%20to%20Maximize%20Score%20in%20a%20Tree/images/treee1293712983719827.png" style="width: 221px; height: 181px;" />
<pre>
<strong>Input:</strong> edges = [[-1,-1],[0,5],[0,-6],[0,7]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We choose the edge with weight 7.
Note that we cannot choose more than one edge because all edges are adjacent to each other.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>par<sub>0</sub> == weight<sub>0</sub> == -1</code></li>
	<li><code>0 &lt;= par<sub>i</sub> &lt;= n - 1</code> for all <code>i &gt;= 1</code>.</li>
	<li><code>par<sub>i</sub> != i</code></li>
	<li><code>-10<sup>6</sup> &lt;= weight<sub>i</sub> &lt;= 10<sup>6</sup></code> for all <code>i &gt;= 1</code>.</li>
	<li><code>edges</code> represents a valid tree.</li>
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
