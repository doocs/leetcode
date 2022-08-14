# [1766. Tree of Coprimes](https://leetcode.com/problems/tree-of-coprimes)

[中文文档](/solution/1700-1799/1766.Tree%20of%20Coprimes/README.md)

## Description

<p>There is a tree (i.e.,&nbsp;a connected, undirected graph that has no cycles) consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> and exactly <code>n - 1</code> edges. Each node has a value associated with it, and the <strong>root</strong> of the tree is node <code>0</code>.</p>

<p>To represent this tree, you are given an integer array <code>nums</code> and a 2D array <code>edges</code>. Each <code>nums[i]</code> represents the <code>i<sup>th</sup></code> node&#39;s value, and each <code>edges[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> represents an edge between nodes <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> in the tree.</p>

<p>Two values <code>x</code> and <code>y</code> are <strong>coprime</strong> if <code>gcd(x, y) == 1</code> where <code>gcd(x, y)</code> is the <strong>greatest common divisor</strong> of <code>x</code> and <code>y</code>.</p>

<p>An ancestor of a node <code>i</code> is any other node on the shortest path from node <code>i</code> to the <strong>root</strong>. A node is <strong>not </strong>considered an ancestor of itself.</p>

<p>Return <em>an array </em><code>ans</code><em> of size </em><code>n</code>, <em>where </em><code>ans[i]</code><em> is the closest ancestor to node </em><code>i</code><em> such that </em><code>nums[i]</code> <em>and </em><code>nums[ans[i]]</code> are <strong>coprime</strong>, or <code>-1</code><em> if there is no such ancestor</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1766.Tree%20of%20Coprimes/images/untitled-diagram.png" style="width: 191px; height: 281px;" /></strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
<strong>Output:</strong> [-1,0,0,1]
<strong>Explanation:</strong> In the above figure, each node&#39;s value is in parentheses.
- Node 0 has no coprime ancestors.
- Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
- Node 2 has two ancestors, nodes 1 and 0. Node 1&#39;s value is not coprime (gcd(3,3) == 3), but node 0&#39;s
  value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
- Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
  closest valid ancestor.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1766.Tree%20of%20Coprimes/images/untitled-diagram1.png" style="width: 441px; height: 291px;" /></p>

<pre>
<strong>Input:</strong> nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
<strong>Output:</strong> [-1,0,-1,0,0,0,-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[j].length == 2</code></li>
	<li><code>0 &lt;= u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
	<li><code>u<sub>j</sub> != v<sub>j</sub></code></li>
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
