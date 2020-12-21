# [106. Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal)

[中文文档](/solution/0100-0199/0106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal/README.md)

## Description

<p>Given inorder and postorder traversal of a tree, construct the binary tree.</p>

<p><strong>Note:</strong><br />

You may assume that duplicates do not exist in the tree.</p>

<p>For example, given</p>

<pre>

inorder =&nbsp;[9,3,15,20,7]

postorder = [9,15,7,20,3]</pre>

<p>Return the following binary tree:</p>

<pre>

    3

   / \

  9  20

    /  \

   15   7

</pre>

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
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
        def build(inorder, postorder, i1, i2, p1, p2):
            if i1 > i2 or p1 > p2:
                return None
            root_val = postorder[p2]
            pos = -1
            for i in range(i1, i2 + 1):
                if inorder[i] == root_val:
                    pos = i
                    break
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(inorder, postorder, i1, pos - 1, p1, p1 - i1 + pos - 1)
            root.right = None if pos == i2 else build(inorder, postorder, pos + 1, i2, p1 - i1 + pos, p2 - 1)
            return root
        return build(inorder, postorder, 0, len(inorder) - 1, 0, len(postorder) - 1)
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int i1, int i2, int p1, int p2) {
        if (i1 > i2 || p1 > p2) return null;
        int rootVal = postorder[p2];
        int pos = find(inorder, rootVal, i1, i2);
        TreeNode root = new TreeNode(rootVal);
        root.left = pos == i1 ? null : build(inorder, postorder, i1, pos - 1, p1, p1 - i1 + pos - 1);
        root.right = pos == i2 ? null : build(inorder, postorder, pos + 1, i2, p1 - i1 + pos, p2 - 1);
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

### **C++**

```cpp
class Solution {
public:
    TreeNode *buildTree(vector<int> &inorder, vector<int> &postorder) {
        return buildTree(inorder, 0, inorder.size() - 1, postorder, 0, postorder.size() - 1);
    }
    TreeNode *buildTree(vector<int> &inorder, int iLeft, int iRight, vector<int> &postorder, int pLeft, int pRight) {
        if (iLeft > iRight || pLeft > pRight) return NULL;
        TreeNode *cur = new TreeNode(postorder[pRight]);
        int i = 0;
        for (i = iLeft; i < inorder.size(); ++i) {
            if (inorder[i] == cur->val)
                break;
        }
        cur->left = buildTree(inorder, iLeft, i - 1, postorder, pLeft, pLeft + i - iLeft - 1);
        cur->right = buildTree(inorder, i + 1, iRight, postorder, pLeft + i - iLeft, pRight - 1);
        return cur;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
