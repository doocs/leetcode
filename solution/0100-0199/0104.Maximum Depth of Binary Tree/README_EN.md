# [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree)

[中文文档](/solution/0100-0199/0104.Maximum%20Depth%20of%20Binary%20Tree/README.md)

## Description

<p>Given a binary tree, find its maximum depth.</p>

<p>The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.</p>

<p><strong>Note:</strong>&nbsp;A leaf is a node with no children.</p>

<p><strong>Example:</strong></p>

<p>Given binary tree <code>[3,9,20,null,null,15,7]</code>,</p>

<pre>

    3

   / \

  9  20

    /  \

   15   7</pre>

<p>return its depth = 3.</p>

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
    def maxDepth(self, root: TreeNode) -> int:
        if root is None:
            return 0
        l = self.maxDepth(root.left)
        r = self.maxDepth(root.right)
        return 1 + max(l, r)
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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return 1 + Math.max(l, r);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDepth(TreeNode* root) {
        if (!root) return 0;
        int l = maxDepth(root->left);
        int r = maxDepth(root->right);
        return max(l, r) + 1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
