---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/README.md
tags:
    - 树
    - 二叉搜索树
    - 二叉树
---

<!-- problem:start -->

# [700. 二叉搜索树中的搜索](https://leetcode.cn/problems/search-in-a-binary-search-tree)

[English Version](/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定二叉搜索树（BST）的根节点<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;和一个整数值<meta charset="UTF-8" />&nbsp;<code>val</code>。</p>

<p>你需要在 BST 中找到节点值等于&nbsp;<code>val</code>&nbsp;的节点。 返回以该节点为根的子树。 如果节点不存在，则返回<meta charset="UTF-8" />&nbsp;<code>null</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/images/tree1.jpg" style="height: 179px; width: 250px;" /><meta charset="UTF-8" /></p>

<pre>
<b>输入：</b>root = [4,2,7,1,3], val = 2
<b>输出：</b>[2,1,3]
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/images/tree2.jpg" style="height: 179px; width: 250px;" />
<pre>
<b>输入：</b>root = [4,2,7,1,3], val = 5
<b>输出：</b>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数在&nbsp;<code>[1, 5000]</code>&nbsp;范围内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>7</sup></code></li>
	<li><code>root</code>&nbsp;是二叉搜索树</li>
	<li><code>1 &lt;= val &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们判断当前节点是否为空或者当前节点的值是否等于目标值，如果是则返回当前节点。

否则，如果当前节点的值大于目标值，则递归搜索左子树，否则递归搜索右子树。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    def searchBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        if root is None or root.val == val:
            return root
        return (
            self.searchBST(root.left, val)
            if root.val > val
            else self.searchBST(root.right, val)
        )
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
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
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
    TreeNode* searchBST(TreeNode* root, int val) {
        if (!root || root->val == val) {
            return root;
        }
        return root->val > val ? searchBST(root->left, val) : searchBST(root->right, val);
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
 func searchBST(root *TreeNode, val int) *TreeNode {
    if root == nil || root.Val == val {
        return root
    }
    if root.Val > val {
        return searchBST(root.Left, val)
    }
    return searchBST(root.Right, val)
}
```

#### TypeScript

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function searchBST(root: TreeNode | null, val: number): TreeNode | null {
    if (root === null || root.val === val) {
        return root;
    }
    return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
