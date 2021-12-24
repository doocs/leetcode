# [105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)

[English Version](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>根据一棵树的前序遍历与中序遍历构造二叉树。</p>

<p><strong>注意:</strong><br>
你可以假设树中没有重复的元素。</p>

<p>例如，给出</p>

<pre>前序遍历 preorder =&nbsp;[3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]</pre>

<p>返回如下的二叉树：</p>

<pre>    3
   / \
  9  20
    /  \
   15   7</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先遍历前序节点，对于前序的根节点，在中序节点 `[i1, i2]` 中找到根节点的位置 pos，就可以将中序节点分成：左子树 `[i1, pos - 1]`、右子树 `[pos + 1, i2]`。

通过左右子树的区间，可以计算出左、右子树节点的个数，假设为 m、n。然后在前序节点中，从根节点往后的 m 个节点为左子树，再往后的 n 个节点为右子树。

递归求解即可。

> 前序遍历：先遍历根节点，再遍历左右子树；中序遍历：先遍历左子树，再遍历根节点，最后遍历右子树。

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
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def build(preorder, inorder, p1, p2, i1, i2) -> TreeNode:
            if p1 > p2 or i1 > i2:
                return None
            root_val = preorder[p1]
            pos = self.indexes[root_val]
            root = TreeNode(root_val)
            # pos==i1，说明只有右子树，左子树为空
            root.left = None if pos == i1 else build(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1)
            # pos==i2，说明只有左子树，右子树为空
            root.right = None if pos == i2 else build(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2)
            return root
        n = len(inorder)
        for i in range(n):
            self.indexes[inorder[i]] = i
        return build(preorder, inorder, 0, n - 1, 0, n - 1)
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
        // pos==i1，说明只有右子树，左子树为空
        node.left = pos == i1 ? null : build(preorder, inorder, p1 + 1, pos - i1 + p1, i1, pos - 1);
        // pos==i2，说明只有左子树，右子树为空
        node.right = pos == i2 ? null : build(preorder, inorder, pos - i1 + p1 + 1, p2, pos + 1, i2);
        return node;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
