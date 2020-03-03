# [面试题07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

## 题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
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


**限制：**

- `0 <= 节点个数 <= 5000`

## 解法
### Python3
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if preorder is None or inorder is None or len(preorder) != len(inorder):
            return None
        return self._build_tree(preorder, 0, len(preorder) - 1, inorder, 0, len(inorder) - 1)
        
    def _build_tree(self, preorder, s1, e1, inorder, s2, e2):
        if s1 > e1 or s2 > e2:
            return None
        index = self._find_index(inorder, s2, e2, preorder[s1])
        tree = TreeNode(preorder[s1])
        tree.left = self._build_tree(preorder, s1 + 1, index + s1 - s2, inorder, s2, index - 1)
        tree.right = self._build_tree(preorder, index + s1 - s2 + 1, e1, inorder, index + 1, e2)
        return tree

    def _find_index(self, order, s, e, val):
        for i in range(s, e + 1):
            if order[i] == val:
                return i
        return -1
```

### Java
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder == null || preorder.length == 0 || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if (s1 > e1 || s2 > e2) {
            return null;
        }
        int index = findIndex(inorder, s2, e2, preorder[s1]);
        TreeNode tree = new TreeNode(preorder[s1]);
        tree.left = buildTree(preorder, s1 + 1, index + s1 - s2, inorder, s2, index - 1);
        tree.right = buildTree(preorder, index + s1 - s2 + 1, e1, inorder, index + 1, e2);
        return tree;
    }

    public int findIndex(int[] order, int s, int e, int val) {
        for (int i = s; i <= e; ++i) {
            if (order[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
```

### ...
```

```
