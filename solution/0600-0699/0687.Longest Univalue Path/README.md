---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0687.Longest%20Univalue%20Path/README.md
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [687. 最长同值路径](https://leetcode.cn/problems/longest-univalue-path)

[English Version](/solution/0600-0699/0687.Longest%20Univalue%20Path/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二叉树的<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;，返回&nbsp;<em>最长的路径的长度</em> ，这个路径中的&nbsp;<em>每个节点具有相同值</em>&nbsp;。 这条路径可以经过也可以不经过根节点。</p>

<p><strong>两个节点之间的路径长度</strong>&nbsp;由它们之间的边数表示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0687.Longest%20Univalue%20Path/images/ex1.jpg" /></p>

<pre>
<strong>输入：</strong>root = [5,4,5,1,1,5]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0687.Longest%20Univalue%20Path/images/ex2.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1,4,5,4,4,5]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树的节点数的范围是<meta charset="UTF-8" />&nbsp;<code>[0, 10<sup>4</sup>]</code>&nbsp;</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li>树的深度将不超过 <code>1000</code>&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $\textit{dfs}(root)$，表示以 $\textit{root}$ 节点作为路径的其中一个端点，向下延伸的最长同值路径长度。

在 $\textit{dfs}(root)$ 中，我们首先递归调用 $\textit{dfs}(root.\textit{left})$ 和 $\textit{dfs}(root.\textit{right})$，得到两个返回值 $\textit{l}$ 和 $\textit{r}$。这两个返回值分别代表了以 $\textit{root}$ 节点的左孩子和右孩子为路径的其中一个端点，向下延伸的最长同值路径长度。

如果 $\textit{root}$ 存在左孩子且 $\textit{root}.\textit{val} = \textit{root}.\textit{left}.\textit{val}$，那么在 $\textit{root}$ 的左孩子为路径的其中一个端点，向下延伸的最长同值路径长度应为 $\textit{l} + 1$；否则，这个长度为 $0$。如果 $\textit{root}$ 存在右孩子且 $\textit{root}.\textit{val} = \textit{root}.\textit{right}.\textit{val}$，那么在 $\textit{root}$ 的右孩子为路径的其中一个端点，向下延伸的最长同值路径长度应为 $\textit{r} + 1$；否则，这个长度为 $0$。

在递归调用完左右孩子之后，我们更新答案为 $\max(\textit{ans}, \textit{l} + \textit{r})$，即以 $\textit{root}$ 为端点的路径经过 $\textit{root}$ 的最长同值路径长度。

最后，$\textit{dfs}(root)$ 函数返回以 $\textit{root}$ 为端点的向下延伸的最长同值路径长度，即 $\max(\textit{l}, \textit{r})$。

在主函数中，我们调用 $\textit{dfs}(root)$，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

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
    def longestUnivaluePath(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            l = l + 1 if root.left and root.left.val == root.val else 0
            r = r + 1 if root.right and root.right.val == root.val else 0
            nonlocal ans
            ans = max(ans, l + r)
            return max(l, r)

        ans = 0
        dfs(root)
        return ans
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
    private int ans;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        l = root.left != null && root.left.val == root.val ? l + 1 : 0;
        r = root.right != null && root.right.val == root.val ? r + 1 : 0;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
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
    int longestUnivaluePath(TreeNode* root) {
        int ans = 0;
        auto dfs = [&](auto&& dfs, TreeNode* root) -> int {
            if (!root) {
                return 0;
            }
            int l = dfs(dfs, root->left);
            int r = dfs(dfs, root->right);
            l = root->left && root->left->val == root->val ? l + 1 : 0;
            r = root->right && root->right->val == root->val ? r + 1 : 0;
            ans = max(ans, l + r);
            return max(l, r);
        };
        dfs(dfs, root);
        return ans;
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
func longestUnivaluePath(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if root.Left != nil && root.Left.Val == root.Val {
			l++
		} else {
			l = 0
		}
		if root.Right != nil && root.Right.Val == root.Val {
			r++
		} else {
			r = 0
		}
		ans = max(ans, l+r)
		return max(l, r)
	}
	dfs(root)
	return
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

function longestUnivaluePath(root: TreeNode | null): number {
    let ans: number = 0;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        let [l, r] = [dfs(root.left), dfs(root.right)];
        l = root.left && root.left.val === root.val ? l + 1 : 0;
        r = root.right && root.right.val === root.val ? r + 1 : 0;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    };
    dfs(root);
    return ans;
}
```

#### Rust

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::cell::RefCell;
use std::rc::Rc;

impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, target: i32, ans: &mut i32) -> i32 {
        if root.is_none() {
            return 0;
        }

        let root = root.as_ref().unwrap().borrow();
        let left = Self::dfs(&root.left, root.val, ans);
        let right = Self::dfs(&root.right, root.val, ans);

        *ans = (*ans).max(left + right);

        if root.val == target {
            return left.max(right) + 1;
        }

        0
    }

    pub fn longest_univalue_path(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if root.is_none() {
            return 0;
        }

        let mut ans = 0;
        Self::dfs(&root, root.as_ref().unwrap().borrow().val, &mut ans);
        ans
    }
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var longestUnivaluePath = function (root) {
    let ans = 0;
    const dfs = root => {
        if (!root) {
            return 0;
        }
        let [l, r] = [dfs(root.left), dfs(root.right)];
        l = root.left && root.left.val === root.val ? l + 1 : 0;
        r = root.right && root.right.val === root.val ? r + 1 : 0;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    };
    dfs(root);
    return ans;
};
```

#### C

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
#define max(a, b) (((a) > (b)) ? (a) : (b))

int dfs(struct TreeNode* root, int* ans) {
    if (!root) {
        return 0;
    }
    int l = dfs(root->left, ans);
    int r = dfs(root->right, ans);
    l = root->left && root->left->val == root->val ? l + 1 : 0;
    r = root->right && root->right->val == root->val ? r + 1 : 0;
    *ans = max(*ans, l + r);
    return max(l, r);
}

int longestUnivaluePath(struct TreeNode* root) {
    int ans = 0;
    dfs(root, &ans);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
