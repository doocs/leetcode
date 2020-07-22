# [面试题55 - II. 平衡二叉树](https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/)

## 题目描述
输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过 1，那么它就是一棵平衡二叉树。

**示例 1:**

给定二叉树 `[3,9,20,null,null,15,7]`

```
    3
   / \
  9  20
    /  \
   15   7
```

返回 `true`。

**示例 2:**

给定二叉树 `[1,2,2,3,3,null,null,4,4]`

```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```

返回 `false`。

**限制：**

- `1 <= 树的结点个数 <= 10000`

## 解法
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
    def isBalanced(self, root: TreeNode) -> bool:
        if root is None:
            return True
        return abs(self._height(root.left) - self._height(root.right)) <= 1 and self.isBalanced(root.left) and self.isBalanced(root.right)

    def _height(self, tree):
        if tree is None:
            return 0
        return 1 + max(self._height(tree.left), self._height(tree.right))
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
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return 1 + Math.max(height(tree.left), height(tree.right));
    }
}
```

### **JavaScript**
```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isBalanced = function(root) {
    if(!root) return true
    if(!isBalanced(root.left) || !isBalanced(root.right)) return false
    if(Math.abs(getDepth(root.left)-getDepth(root.right)) > 1) return false
    return true
};

function getDepth(node) {
    if(!node) return 0
    return Math.max(getDepth(node.left),getDepth(node.right)) + 1
}
```

### **...**
```

```

<!-- tabs:end -->