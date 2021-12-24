# [742. Closest Leaf in a Binary Tree](https://leetcode.com/problems/closest-leaf-in-a-binary-tree)

[中文文档](/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/README.md)

## Description

<p>Given a binary tree <b>where every node has a unique value</b>, and a target key <code>k</code>, find the value of the nearest leaf node to target <code>k</code> in the tree.

</p><p>

Here, <i>nearest</i> to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a <i>leaf</i> if it has no children.

</p><p>

In the following examples, the input tree is represented in flattened form row by row.

The actual <code>root</code> tree given will be a TreeNode object.

</p><p>

<b>Example 1:</b>

<pre>

<b>Input:</b>

root = [1, 3, 2], k = 1

Diagram of binary tree:

          1

         / \

        3   2



<b>Output:</b> 2 (or 3)



<b>Explanation:</b> Either 2 or 3 is the nearest leaf node to the target of 1.

</pre>

</p><p>

<b>Example 2:</b>

<pre>

<b>Input:</b>

root = [1], k = 1

<b>Output:</b> 1



<b>Explanation:</b> The nearest leaf node is the root node itself.

</pre>

</p>

<p>

<b>Example 3:</b>

<pre>

<b>Input:</b>

root = [1,2,3,4,null,null,null,5,null,6], k = 2

Diagram of binary tree:

             1

            / \

           2   3

          /

         4

        /

       5

      /

     6



<b>Output:</b> 3

<b>Explanation:</b> The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li><code>root</code> represents a binary tree with at least <code>1</code> node and at most <code>1000</code> nodes.</li>

<li>Every node has a unique <code>node.val</code> in range <code>[1, 1000]</code>.</li>

<li>There exists some node in the given binary tree for which <code>node.val == k</code>.</li>

</ol>

</p>

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
