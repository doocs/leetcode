# [2973. Find Number of Coins to Place in Tree Nodes](https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes)

[中文文档](/solution/2900-2999/2973.Find%20Number%20of%20Coins%20to%20Place%20in%20Tree%20Nodes/README.md)

## Description

<p>You are given an <strong>undirected</strong> tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, and rooted at node <code>0</code>. You are given a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>cost</code> of length <code>n</code>, where <code>cost[i]</code> is the <strong>cost</strong> assigned to the <code>i<sup>th</sup></code> node.</p>

<p>You need to place some coins on every node of the tree. The number of coins to be placed at node <code>i</code> can be calculated as:</p>

<ul>
	<li>If size of the subtree of node <code>i</code> is less than <code>3</code>, place <code>1</code> coin.</li>
	<li>Otherwise, place an amount of coins equal to the <strong>maximum</strong> product of cost values assigned to <code>3</code> distinct nodes in the subtree of node <code>i</code>. If this product is <strong>negative</strong>, place <code>0</code> coins.</li>
</ul>

<p>Return <em>an array </em><code>coin</code><em> of size </em><code>n</code><em> such that </em><code>coin[i]</code><em> is the number of coins placed at node </em><code>i</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2973.Find%20Number%20of%20Coins%20to%20Place%20in%20Tree%20Nodes/images/screenshot-2023-11-10-012641.png" style="width: 600px; height: 233px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[0,3],[0,4],[0,5]], cost = [1,2,3,4,5,6]
<strong>Output:</strong> [120,1,1,1,1,1]
<strong>Explanation:</strong> For node 0 place 6 * 5 * 4 = 120 coins. All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2973.Find%20Number%20of%20Coins%20to%20Place%20in%20Tree%20Nodes/images/screenshot-2023-11-10-012614.png" style="width: 800px; height: 374px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]], cost = [1,4,2,3,5,7,8,-4,2]
<strong>Output:</strong> [280,140,32,1,1,1,1,1,1]
<strong>Explanation:</strong> The coins placed on each node are:
- Place 8 * 7 * 5 = 280 coins on node 0.
- Place 7 * 5 * 4 = 140 coins on node 1.
- Place 8 * 2 * 2 = 32 coins on node 2.
- All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2973.Find%20Number%20of%20Coins%20to%20Place%20in%20Tree%20Nodes/images/screenshot-2023-11-10-012513.png" style="width: 300px; height: 277px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]], cost = [1,2,-2]
<strong>Output:</strong> [0,1,1]
<strong>Explanation:</strong> Node 1 and 2 are leaves with subtree of size 1, place 1 coin on each of them. For node 0 the only possible product of cost is 2 * 1 * -2 = -4. Hence place 0 coins on node 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= |cost[i]| &lt;= 10<sup>4</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
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
