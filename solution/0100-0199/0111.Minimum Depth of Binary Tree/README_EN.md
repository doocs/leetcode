# [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree)

[中文文档](/solution/0100-0199/0111.Minimum%20Depth%20of%20Binary%20Tree/README.md)

## Description

<p>Given a binary tree, find its minimum depth.</p>

<p>The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.</p>

<p><strong>Note:</strong>&nbsp;A leaf is a node with no children.</p>

<p><strong>Example:</strong></p>

<p>Given binary tree <code>[3,9,20,null,null,15,7]</code>,</p>

<pre>

    3

   / \

  9  20

    /  \

   15   7</pre>

<p>return its minimum&nbsp;depth = 2.</p>

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
    def minDepth(self, root: TreeNode) -> int:
        if root is None:
            return 0
        if root.left is None and root.right is None:
            return 1
        l = self.minDepth(root.left)
        r = self.minDepth(root.right)
        # 如果左子树和右子树其中一个为空，那么需要返回比较大的那个子树的深度
        if root.left is None or root.right is None:
            return l + r + 1
        # 左右子树都不为空，返回最小深度+1即可
        return min(l, r) + 1
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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        if (root.left == null || root.right == null) return l + r + 1;
        return Math.min(l, r) + 1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
