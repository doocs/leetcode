# [971. Flip Binary Tree To Match Preorder Traversal](https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal)

[中文文档](/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/README.md)

## Description

<p>You are given the <code>root</code> of a binary tree with <code>n</code> nodes, where each node is uniquely assigned a value from <code>1</code> to <code>n</code>. You are also given a sequence of <code>n</code> values <code>voyage</code>, which is the <strong>desired</strong> <a href="https://en.wikipedia.org/wiki/Tree_traversal#Pre-order" target="_blank"><strong>pre-order traversal</strong></a> of the binary tree.</p>

<p>Any node in the binary tree can be <strong>flipped</strong> by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/fliptree.jpg" style="width: 400px; height: 187px;" />
<p>Flip the <strong>smallest</strong> number of nodes so that the <strong>pre-order traversal</strong> of the tree <strong>matches</strong> <code>voyage</code>.</p>

<p>Return <em>a list of the values of all <strong>flipped</strong> nodes. You may return the answer in <strong>any order</strong>. If it is <strong>impossible</strong> to flip the nodes in the tree to make the pre-order traversal match </em><code>voyage</code><em>, return the list </em><code>[-1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/1219-01.png" style="width: 150px; height: 205px;" />
<pre>
<strong>Input:</strong> root = [1,2], voyage = [2,1]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> It is impossible to flip the nodes such that the pre-order traversal matches voyage.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/1219-02.png" style="width: 150px; height: 142px;" />
<pre>
<strong>Input:</strong> root = [1,2,3], voyage = [1,3,2]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/1219-02.png" style="width: 150px; height: 142px;" />
<pre>
<strong>Input:</strong> root = [1,2,3], voyage = [1,2,3]
<strong>Output:</strong> []
<strong>Explanation:</strong> The tree&#39;s pre-order traversal already matches voyage, so no nodes need to be flipped.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>n == voyage.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= Node.val, voyage[i] &lt;= n</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li>All the values in <code>voyage</code> are <strong>unique</strong>.</li>
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
