# [776. Split BST](https://leetcode.com/problems/split-bst)

[中文文档](/solution/0700-0799/0776.Split%20BST/README.md)

## Description

<p>Given a Binary Search Tree (BST) with root node <code>root</code>, and a target value <code>V</code>, split the tree into two subtrees&nbsp;where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.&nbsp; It&#39;s not necessarily the case that the tree contains a node with value <code>V</code>.</p>

<p>Additionally, most of the structure of the original tree should remain.&nbsp; Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.</p>

<p>You should output the root TreeNode of&nbsp;both subtrees after splitting, in any order.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> root = [4,2,6,1,3,5,7], V = 2

<strong>Output:</strong> [[2,1],[4,3,6,null,null,5,7]]

<strong>Explanation:</strong>

Note that root, output[0], and output[1] are TreeNode objects, not arrays.



The given tree [4,2,6,1,3,5,7] is represented by the following diagram:



          4

        /   \

      2      6

     / \    / \

    1   3  5   7



while the diagrams for the outputs are:



          4

        /   \

      3      6      and    2

            / \           /

           5   7         1

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>The size of the BST will not exceed <code>50</code>.</li>
	<li>The BST is always valid and each node&#39;s value is different.</li>
</ol>

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
