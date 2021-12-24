# [106. 从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal)

[English Version](/solution/0100-0199/0106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>根据一棵树的中序遍历与后序遍历构造二叉树。</p>

<p><strong>注意:</strong><br>
你可以假设树中没有重复的元素。</p>

<p>例如，给出</p>

<pre>中序遍历 inorder =&nbsp;[9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]</pre>

<p>返回如下的二叉树：</p>

<pre>    3
   / \
  9  20
    /  \
   15   7
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

思路同 [105](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README.md)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    indexes = {}
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
        def build(inorder, postorder, i1, i2, p1, p2):
            if i1 > i2 or p1 > p2:
                return None
            root_val = postorder[p2]
            pos = self.indexes[root_val]
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(inorder, postorder, i1, pos - 1, p1, p1 - i1 + pos - 1)
            root.right = None if pos == i2 else build(inorder, postorder, pos + 1, i2, p1 - i1 + pos, p2 - 1)
            return root
        n = len(inorder)
        for i in range(n):
            self.indexes[inorder[i]] = i
        return build(inorder, postorder, 0, n - 1, 0, n - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            indexes.put(inorder[i], i);
        }
        return build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int i1, int i2, int p1, int p2) {
        if (i1 > i2 || p1 > p2) return null;
        int rootVal = postorder[p2];
        int pos = indexes.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = pos == i1 ? null : build(inorder, postorder, i1, pos - 1, p1, p1 - i1 + pos - 1);
        root.right = pos == i2 ? null : build(inorder, postorder, pos + 1, i2, p1 - i1 + pos, p2 - 1);
        return root;
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
