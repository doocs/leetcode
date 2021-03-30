# [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree)

[中文文档](/solution/0000-0099/0098.Validate%20Binary%20Search%20Tree/README.md)

## Description

<p>Given a binary tree, determine if it is a valid binary search tree (BST).</p>

<p>Assume a BST is defined as follows:</p>

<ul>
    <li>The left subtree of a node contains only nodes with keys <strong>less than</strong> the node&#39;s key.</li>
    <li>The right subtree of a node contains only nodes with keys <strong>greater than</strong> the node&#39;s key.</li>
    <li>Both the left and right subtrees must also be binary search trees.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

    2

   / \

  1   3



<strong>Input:</strong>&nbsp;[2,1,3]

<strong>Output:</strong> true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

    5

   / \

  1   4

&nbsp;    / \

&nbsp;   3   6



<strong>Input:</strong> [5,1,4,null,null,3,6]

<strong>Output:</strong> false

<strong>Explanation:</strong> The root node&#39;s value is 5 but its right child&#39;s value is 4.

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    pre = None
    def isValidBST(self, root: TreeNode) -> bool:
        if not root:
            return True
        if not self.isValidBST(root.left):
            return False
        if self.pre is not None and self.pre >= root.val:
            return False
        self.pre = root.val
        if not self.isValidBST(root.right):
            return False
        return True
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Integer pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre != null && pre >= root.val) return false;
        pre = root.val;
        if (!isValidBST(root.right)) return false;
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
