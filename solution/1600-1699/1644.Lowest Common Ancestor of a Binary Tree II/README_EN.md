# [1644. Lowest Common Ancestor of a Binary Tree II](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii)

[中文文档](/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the lowest common ancestor (LCA) of two given nodes, </em><code>p</code><em> and </em><code>q</code>. If either node <code>p</code> or <code>q</code> <strong>does not exist</strong> in the tree, return <code>null</code>. All values of the nodes in the tree are <strong>unique</strong>.</p>

<p>According to the <strong><a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a></strong>: &quot;The lowest common ancestor of two nodes <code>p</code> and <code>q</code> in a binary tree <code>T</code> is the lowest node that has both <code>p</code> and <code>q</code> as <strong>descendants</strong> (where we allow <b>a node to be a descendant of itself</b>)&quot;. A <strong>descendant</strong> of a node <code>x</code> is a node <code>y</code> that is on the path from node <code>x</code> to some leaf node.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> The LCA of nodes 5 and 1 is 3.</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" /></p>

<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<strong>Output:</strong> 5
<strong>Explanation:</strong> The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.</pre>

<p><strong>Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" /></p>

<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
<strong>Output:</strong> null
<strong>Explanation:</strong> Node 10 does not exist in the tree, so return null.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>p != q</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong>&nbsp;Can you find the LCA traversing the tree, without checking nodes existence?

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
