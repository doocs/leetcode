---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [156. Binary Tree Upside Down 🔒](https://leetcode.com/problems/binary-tree-upside-down)

[中文文档](/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>root</code> của một cây nhị phân, hãy lật ngược cây và trả về <em>root mới</em>.</p>

<p>Bạn có thể lật ngược một cây nhị phân theo các bước sau:</p>

<ol>
	<li>Con trái ban đầu trở thành root mới.</li>
	<li>Root ban đầu trở thành con phải mới.</li>
	<li>Con phải ban đầu trở thành con trái mới.</li>
</ol>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/images/main.jpg" style="width: 600px; height: 95px;" />
<p>Các bước trên được thực hiện theo từng level. <strong>Đảm bảo</strong> rằng mọi node phải đều có sibling (một node trái cùng parent) và không có node con.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/images/updown.jpg" style="width: 800px; height: 161px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3,4,5]
<strong>Đầu ra:</strong> [4,5,2,null,null,3,1]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> []
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = [1]
<strong>Đầu ra:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng node trong cây nằm trong khoảng <code>[0, 10]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10</code></li>
	<li>Mọi node phải trong cây đều có sibling (một node trái có cùng parent).</li>
	<li>Mọi node phải trong cây đều không có node con.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Mọi con phải đều là một lá có một sibling bên trái, nên cây gồm một nhánh trái và các lá phải. $n\le 10$. Sau khi lật, lá ngoài cùng bên trái là root mới, root cũ trở thành con phải của nó, còn con phải cũ trở thành con trái. Đệ quy đi xuống nhánh trái, nối lại các liên kết khi quay lui, rồi xóa các con của root cũ.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
