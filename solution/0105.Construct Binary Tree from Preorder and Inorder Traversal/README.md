## 从前序与中序遍历序列构造二叉树

### 问题描述
根据一棵树的前序遍历与中序遍历构造二叉树。

**注意:**

你可以假设树中没有重复的元素。

例如，给出
```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
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

利用树的前序遍历和中序遍历特性 + 递归实现。

对树进行前序遍历，每一个元素，在树的中序遍历中找到该元素；在中序遍历中，该元素的左边是它的左子树的全部元素，右边是它的右子树的全部元素，以此为递归条件，确定左右子树的范围。

```java
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int n = preorder.length;
        return n > 0 ? buildTree(preorder, 0, n - 1, inorder, 0, n - 1) : null;
    }
    
    private TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder,  int s2, int e2) {
        TreeNode node = new TreeNode(preorder[s1]);                           
        if (s1 == e1 && s2 == e2) {
            return node;
        }

        int p = s2;
        while (inorder[p] != preorder[s1]) {
            ++p;
            if (p > e2) {
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        
        node.left = p > s2 ? buildTree(preorder, s1 + 1, s1 - s2 + p, inorder, s2, p - 1) : null;
        node.right = p < e2 ? buildTree(preorder, s1 - s2 + p + 1, e1, inorder, p + 1, e2) : null;
        return node;
    }
}
```