# [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree)

[中文文档](/solution/0100-0199/0101.Symmetric%20Tree/README.md)

## Description

<p>Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).</p>

<p>For example, this binary tree <code>[1,2,2,3,4,4,3]</code> is symmetric:</p>

<pre>

    1

   / \

  2   2

 / \ / \

3  4 4  3

</pre>

<p>&nbsp;</p>

<p>But the following <code>[1,2,2,null,3,null,3]</code> is not:</p>

<pre>

    1

   / \

  2   2

   \   \

   3    3

</pre>

<p>&nbsp;</p>

<p><b>Note:</b><br />

Bonus points if you could solve it both recursively and iteratively.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        if root is None:
            return True
        return self.is_symmetric(root.left, root.right)

    def is_symmetric(self, left: TreeNode, right: TreeNode) -> bool:
        if left is None and right is None:
            return True
        if left is None or right is None or left.val != right.val:
            return False
        return self.is_symmetric(left.left, right.right) and self.is_symmetric(left.right, right.left)
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isSymmetric(TreeNode* root) {
        if (!root) return true;
        return isSymmetric(root->left, root->right);
    }

private:
    bool isSymmetric(TreeNode* left, TreeNode* right) {
        if (!left && !right) return true;
        if (!left && right || left && !right || left->val != right->val) return false;
        return isSymmetric(left->left, right->right) && isSymmetric(left->right, right->left);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
