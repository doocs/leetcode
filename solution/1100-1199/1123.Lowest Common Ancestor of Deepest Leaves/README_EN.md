# [1123. Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves)

[中文文档](/solution/1100-1199/1123.Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves/README.md)

## Description

<p>Given the <code>root</code> of a&nbsp;binary tree, return <em>the lowest common ancestor of its deepest leaves</em>.</p>

<p>Recall that:</p>

<ul>
	<li>The node of a binary tree is a leaf if and only if it has no children</li>
	<li>The depth of the root of the tree is <code>0</code>. if the depth of a node is <code>d</code>, the depth of each of its children&nbsp;is&nbsp;<code>d + 1</code>.</li>
	<li>The lowest common ancestor of a set <code>S</code> of nodes, is the node <code>A</code> with the largest depth such that every node in <code>S</code> is in the subtree with root <code>A</code>.</li>
</ul>

<p><strong>Note:</strong> This question is the same as 865: <a href="https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/" target="_blank">https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/</a></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1123.Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves/images/sketch1.png" style="width: 600px; height: 510px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4]
<strong>Output:</strong> [2,7,4]
<strong>Explanation:</strong> We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest leaf-nodes of the tree.
Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The root is the deepest node in the tree, and it&#39;s the lca of itself.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [0,1,3,null,2]
<strong>Output:</strong> [2]
<strong>Explanation:</strong> The deepest leaf node in the tree is 2, the lca of one node is itself.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree will be in the range <code>[1, 1000]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
	<li>The values of the nodes in the tree&nbsp;are <strong>unique</strong>.</li>
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
