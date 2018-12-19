## 从中序与后序遍历序列构造二叉树

### 问题描述
根据一棵树的中序遍历与后序遍历构造二叉树。

**注意:**

你可以假设树中没有重复的元素。

例如，给出
```
中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
```

返回如下的二叉树：
```
    3
   / \
  9  20
    /  \
   15   7
```

### 解法

利用树的后序遍历和中序遍历特性 + 递归实现。

树的后序遍历序列，从后往前，对于每一个元素，在树的中序遍历中找到该元素；在中序遍历中，该元素的左边是它的左子树的全部元素，右边是它的右子树的全部元素，以此为递归条件，确定左右子树的范围。

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        int n = inorder.length;
        return n > 0 ? buildTree(inorder, 0, n - 1, postorder, 0, n - 1) : null;
    }
    
    private TreeNode buildTree(int[] inorder, int s1, int e1, int[] postorder, int s2, int e2) {
        TreeNode node = new TreeNode(postorder[e2]);
        if (s2 == e2 && s1 == e1) {
            return node;
        }
        
        int p = s1;
        while (inorder[p] != postorder[e2]) {
            ++p;
            if (p > e1) {
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        
        node.left = p > s1 ? buildTree(inorder, s1, p - 1, postorder, s2, p - 1 + s2 - s1) : null;
        node.right = p < e1 ? buildTree(inorder, p + 1, e1, postorder, p + s2 - s1, e2 - 1) : null;
        return node;
        
    }
}
```