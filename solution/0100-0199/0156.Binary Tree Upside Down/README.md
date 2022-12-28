# [156. 上下翻转二叉树](https://leetcode.cn/problems/binary-tree-upside-down)

[English Version](/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉树的根节点 <code>root</code> ，请你将此二叉树上下翻转，并返回新的根节点。</p>

<p>你可以按下面的步骤翻转一棵二叉树：</p>

<ol>
	<li>原来的左子节点变成新的根节点</li>
	<li>原来的根节点变成新的右子节点</li>
	<li>原来的右子节点变成新的左子节点</li>
</ol>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/images/main.jpg" style="width: 600px; height: 95px;" />
<p>上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/images/updown.jpg" style="width: 800px; height: 161px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,5]
<strong>输出：</strong>[4,5,2,null,null,3,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[0, 10]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10</code></li>
	<li>树中的每个右节点都有一个同级节点（即共享同一父节点的左节点）</li>
	<li>树中的每个右节点都没有子节点</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

若根节点为空，或者根节点左子树为空，直接返回根节点。

递归处理左子树，返回的根节点 newRoot，也就是二叉树上下翻转后的根节点。

然后处理根节点 root，根节点变成左子节点的右子节点，而根节点的右子节点变成左子节点的左子节点。

接着将根节点 root 的左右子节点置为空，最后返回 newRoot 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树节点个数。

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
