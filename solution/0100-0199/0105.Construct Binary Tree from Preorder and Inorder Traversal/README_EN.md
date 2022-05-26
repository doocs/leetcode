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
    indexes = {}
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def build(preorder, inorder, p1, p2, i1, i2) -> TreeNode:
            if p1 > p2 or i1 > i2:
                return None
            root_val = preorder[p1]
            pos = self.indexes[root_val]
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1)
            root.right = None if pos == i2 else build(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2)
            return root
        n = len(inorder)
        for i in range(n):
            self.indexes[inorder[i]] = i
        return build(preorder, inorder, 0, n - 1, 0, n - 1)
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
    private Map<Integer, Integer> indexes = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            indexes.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2) {
        if (p1 > p2 || i1 > i2) return null;
        int rootVal = preorder[p1];
        int pos = indexes.get(rootVal);
        TreeNode node = new TreeNode(rootVal);
        node.left = pos == i1 ? null : build(preorder, inorder, p1 + 1, pos - i1 + p1, i1, pos - 1);
        node.right = pos == i2 ? null : build(preorder, inorder, pos - i1 + p1 + 1, p2, pos + 1, i2);
        return node;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
