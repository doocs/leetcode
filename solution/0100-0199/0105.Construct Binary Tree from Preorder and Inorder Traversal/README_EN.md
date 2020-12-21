# [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)

[中文文档](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README.md)

## Description

<p>Given preorder and inorder traversal of a tree, construct the binary tree.</p>

<p><strong>Note:</strong><br />

You may assume that duplicates do not exist in the tree.</p>

<p>For example, given</p>

<pre>

preorder =&nbsp;[3,9,20,15,7]

inorder = [9,3,15,20,7]</pre>

<p>Return the following binary tree:</p>

<pre>

    3

   / \

  9  20

    /  \

   15   7</pre>

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
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def build(preorder, inorder, p1, p2, i1, i2) -> TreeNode:
            if p1 > p2 or i1 > i2:
                return None
            root_val = preorder[p1]
            pos = -1
            for i in range(i1, i2 + 1):
                if inorder[i] == root_val:
                    pos = i
                    break
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1)
            root.right = None if pos == i2 else build(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2)
            return root
        return build(preorder, inorder, 0, len(preorder) - 1, 0, len(inorder) - 1)
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2) {
        if (p1 > p2 || i1 > i2) return null;
        int rootVal = preorder[p1];
        int pos = find(inorder, rootVal, i1, i2);
        TreeNode root = new TreeNode(rootVal);
        root.left = pos == i1 ? null : buildTree(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1);
        root.right = pos == i2 ? null : buildTree(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2);
        return root;
    }

    private int find(int[] order, int val, int p, int q) {
        for (int i = p; i <= q; ++i) {
            if (order[i] == val) return i;
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
