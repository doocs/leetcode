# [156. 上下翻转二叉树](https://leetcode-cn.com/problems/binary-tree-upside-down)

[English Version](/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。</p>

<p><strong>例子:</strong></p>

<pre><strong>输入: </strong>[1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

<strong>输出:</strong> 返回二叉树的根 [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1  
</pre>

<p><strong>说明:</strong></p>

<p>对 <code>[4,5,2,#,#,3,1]</code> 感到困惑? 下面详细介绍请查看&nbsp;<a href="https://support.leetcode-cn.com/hc/kb/article/1194353/" target="_blank">二叉树是如何被序列化的</a>。</p>

<p>二叉树的序列化遵循层次遍历规则，当没有节点存在时，&#39;#&#39; 表示路径终止符。</p>

<p>这里有一个例子:</p>

<pre>   1
  / \
 2   3
    /
   4
    \
     5
</pre>

<p>上面的二叉树则被序列化为 <code>[1,2,3,#,#,4,#,#,5]</code>.</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

若根节点为空，或者根节点左子树为空，直接返回根节点。

递归处理左子树，返回的根节点 newRoot，也就是二叉树上下翻转后的根节点。

然后处理根节点 root，根节点变成左子节点的右子节点，而根节点的右子节点变成左子节点的左子节点。

接着将根节点 root 的左右子节点置为空，最后返回 newRoot 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def upsideDownBinaryTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if root is None or root.left is None:
            return root
        new_root = self.upsideDownBinaryTree(root.left)
        root.left.right = root
        root.left.left = root.right
        root.left = None
        root.right = None
        return new_root
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
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.right = root;
        root.left.left = root.right;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* upsideDownBinaryTree(TreeNode* root) {
        if (!root || !root->left) return root;
        TreeNode* newRoot = upsideDownBinaryTree(root->left);
        root->left->right = root;
        root->left->left = root->right;
        root->left = nullptr;
        root->right = nullptr;
        return newRoot;
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func upsideDownBinaryTree(root *TreeNode) *TreeNode {
	if root == nil || root.Left == nil {
		return root
	}
	newRoot := upsideDownBinaryTree(root.Left)
	root.Left.Right = root
	root.Left.Left = root.Right
	root.Left = nil
	root.Right = nil
	return newRoot
}
```

### **...**

```

```

<!-- tabs:end -->
