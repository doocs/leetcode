# [538. Convert BST to Greater Tree](https://leetcode.com/problems/convert-bst-to-greater-tree)

[中文文档](/solution/0500-0599/0538.Convert%20BST%20to%20Greater%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.</p>



<p>As a reminder, a <em>binary search tree</em> is a tree that satisfies these constraints:</p>



<ul>
	<li>The left subtree of a node contains only nodes with keys&nbsp;<strong>less than</strong>&nbsp;the node&#39;s key.</li>
	<li>The right subtree of a node contains only nodes with keys&nbsp;<strong>greater than</strong>&nbsp;the node&#39;s key.</li>
	<li>Both the left and right subtrees must also be binary search trees.</li>
</ul>



<p><strong>Note:</strong> This question is the same as&nbsp;1038:&nbsp;<a href="https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/">https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/</a></p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0538.Convert%20BST%20to%20Greater%20Tree/images/tree.png" style="width: 550px; height: 375px;" />

<pre>

<strong>Input:</strong> root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]

<strong>Output:</strong> [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input:</strong> root = [0,null,1]

<strong>Output:</strong> [1,null,1]

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> root = [1,0,2]

<strong>Output:</strong> [3,3,2]

</pre>



<p><strong>Example 4:</strong></p>



<pre>

<strong>Input:</strong> root = [3,2,4,1]

<strong>Output:</strong> [7,9,4,10]

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li><code>root</code> is guaranteed to be a valid binary search tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    int add = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            root.val += add;
            add = root.val;
            convertBST(root.left);
        }
        return root;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
