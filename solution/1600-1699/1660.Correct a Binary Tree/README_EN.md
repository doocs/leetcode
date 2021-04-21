# [1660. Correct a Binary Tree](https://leetcode.com/problems/correct-a-binary-tree)

[中文文档](/solution/1600-1699/1660.Correct%20a%20Binary%20Tree/README.md)

## Description

<p>You have a binary tree with a small defect. There is <strong>exactly one</strong> invalid node where its right child incorrectly points to another node at the <strong>same depth</strong> but to the <b>invalid node&#39;s right</b>.</p>

<p>Given the root of the binary tree with this defect, <code>root</code>, return <em>the root of the binary tree after <strong>removing</strong> this invalid node <strong>and every node underneath it</strong> (minus the node it incorrectly points to).</em></p>

<p><strong>Custom testing:</strong></p>

<p>The test input is read as 3 lines:</p>

<ul>
	<li><code>TreeNode root</code></li>
	<li><code>int fromNode</code> (<strong>not available to </strong><code>correctBinaryTree</code>)</li>
	<li><code>int toNode</code> (<strong>not available to </strong><code>correctBinaryTree</code>)</li>
</ul>

<p>After the binary tree rooted at <code>root</code> is parsed, the <code>TreeNode</code> with value of <code>fromNode</code> will have its right child pointer pointing to the <code>TreeNode</code> with a value of <code>toNode</code>. Then, <code>root</code> is passed to <code>correctBinaryTree</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/10/22/ex1v2.png" style="width: 250px; height: 177px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3], fromNode = 2, toNode = 3
<strong>Output:</strong> [1,null,3]
<strong>Explanation:</strong> The node with value 2 is invalid, so remove it.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/10/22/ex2v3.png" style="width: 350px; height: 255px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [8,3,1,7,null,9,4,2,null,null,null,5,6], fromNode = 7, toNode = 4
<strong>Output:</strong> [8,3,1,null,null,9,4,null,null,5,6]
<strong>Explanation:</strong> The node with value 7 is invalid, so remove it and the node underneath it, node 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[3, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>fromNode != toNode</code></li>
	<li><code>fromNode</code> and <code>toNode</code> will exist in the tree and will be on the same depth.</li>
	<li><code>toNode</code> is to the <strong>right</strong> of <code>fromNode</code>.</li>
	<li><code>fromNode.right</code> is <code>null</code> in the initial tree from the test data.</li>
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
