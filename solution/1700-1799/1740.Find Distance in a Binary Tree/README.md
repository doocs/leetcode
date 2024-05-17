---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1740.Find%20Distance%20in%20a%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [1740. 找到二叉树中的距离 🔒](https://leetcode.cn/problems/find-distance-in-a-binary-tree)

[English Version](/solution/1700-1799/1740.Find%20Distance%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树的根节点 <code>root</code> 以及两个整数 <code>p</code> 和 <code>q</code> ，返回该二叉树中值为 <code>p</code> 的结点与值为 <code>q</code> 的结点间的 <strong>距离 </strong>。</p>

<p>两个结点间的<strong> 距离 </strong>就是从一个结点到另一个结点的路径上边的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1740.Find%20Distance%20in%20a%20Binary%20Tree/images/binarytree.png" />
<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
<strong>输出：</strong>3
<strong>解释：</strong>在 5 和 0 之间有 3 条边：5-3-1-0</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1740.Find%20Distance%20in%20a%20Binary%20Tree/images/binarytree.png" />
<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
<strong>输出：</strong>2
<strong>解释：</strong>在 5 和 7 之间有 2 条边：5-2-7</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1740.Find%20Distance%20in%20a%20Binary%20Tree/images/binarytree.png" />
<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
<strong>输出：</strong>0
<strong>解释：</strong>一个结点与它本身之间的距离为 0</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中结点个数的范围在 <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>0 <= Node.val <= 10<sup>9</sup></code></li>
	<li>树中所有结点的值都是唯一的.</li>
	<li><code>p</code> 和<code>q</code> 是树中结点的值.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
    def findDistance(self, root: Optional[TreeNode], p: int, q: int) -> int:
        def lca(root, p, q):
            if root is None or root.val in [p, q]:
                return root
            left = lca(root.left, p, q)
            right = lca(root.right, p, q)
            if left is None:
                return right
            if right is None:
                return left
            return root

        def dfs(root, v):
            if root is None:
                return -1
            if root.val == v:
                return 0
            left, right = dfs(root.left, v), dfs(root.right, v)
            if left == right == -1:
                return -1
            return 1 + max(left, right)

        g = lca(root, p, q)
        return dfs(g, p) + dfs(g, q)
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
    public int findDistance(TreeNode root, int p, int q) {
        TreeNode g = lca(root, p, q);
        return dfs(g, p) + dfs(g, q);
    }

    private int dfs(TreeNode root, int v) {
        if (root == null) {
            return -1;
        }
        if (root.val == v) {
            return 0;
        }
        int left = dfs(root.left, v);
        int right = dfs(root.right, v);
        if (left == -1 && right == -1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    private TreeNode lca(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
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
    int findDistance(TreeNode* root, int p, int q) {
        TreeNode* g = lca(root, p, q);
        return dfs(g, p) + dfs(g, q);
    }

    TreeNode* lca(TreeNode* root, int p, int q) {
        if (!root || root->val == p || root->val == q) return root;
        TreeNode* left = lca(root->left, p, q);
        TreeNode* right = lca(root->right, p, q);
        if (!left) return right;
        if (!right) return left;
        return root;
    }

    int dfs(TreeNode* root, int v) {
        if (!root) return -1;
        if (root->val == v) return 0;
        int left = dfs(root->left, v);
        int right = dfs(root->right, v);
        if (left == -1 && right == -1) return -1;
        return 1 + max(left, right);
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
func findDistance(root *TreeNode, p int, q int) int {
	var lca func(root *TreeNode, p int, q int) *TreeNode
	lca = func(root *TreeNode, p int, q int) *TreeNode {
		if root == nil || root.Val == p || root.Val == q {
			return root
		}
		left, right := lca(root.Left, p, q), lca(root.Right, p, q)
		if left == nil {
			return right
		}
		if right == nil {
			return left
		}
		return root
	}
	var dfs func(root *TreeNode, v int) int
	dfs = func(root *TreeNode, v int) int {
		if root == nil {
			return -1
		}
		if root.Val == v {
			return 0
		}
		left, right := dfs(root.Left, v), dfs(root.Right, v)
		if left == -1 && right == -1 {
			return -1
		}
		return 1 + max(left, right)
	}
	g := lca(root, p, q)
	return dfs(g, p) + dfs(g, q)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
