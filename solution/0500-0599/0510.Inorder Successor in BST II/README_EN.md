# [510. Inorder Successor in BST II](https://leetcode.com/problems/inorder-successor-in-bst-ii)

[中文文档](/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/README.md)

## Description

<p>Given a <code>node</code> in a binary search tree, return <em>the in-order successor of that node in the BST</em>. If that node has no in-order successor, return <code>null</code>.</p>

<p>The successor of a <code>node</code> is the node with the smallest key greater than <code>node.val</code>.</p>

<p>You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for <code>Node</code>:</p>

<pre>
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
</pre>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_1.png" style="width: 122px; height: 117px;" />
<pre>
<strong>Input:</strong> tree = [2,1,3], node = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1&#39;s in-order successor node is 2. Note that both the node and the return value is of Node type.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_2.png" style="width: 246px; height: 229px;" />
<pre>
<strong>Input:</strong> tree = [5,3,6,2,4,null,null,1], node = 6
<strong>Output:</strong> null
<strong>Explanation:</strong> There is no in-order successor of the current node, so the answer is null.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_34.png" style="width: 438px; height: 335px;" />
<pre>
<strong>Input:</strong> tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 15
<strong>Output:</strong> 17
</pre>

<p><strong>Example 4:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_34.png" style="width: 438px; height: 335px;" />
<pre>
<strong>Input:</strong> tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 13
<strong>Output:</strong> 15
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> tree = [0], node = 0
<strong>Output:</strong> null
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>All Nodes will have unique values.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it without looking up any of the node&#39;s values?</p>


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
