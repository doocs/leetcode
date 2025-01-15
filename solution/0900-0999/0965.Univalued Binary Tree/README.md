---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0965.Univalued%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [965. 单值二叉树](https://leetcode.cn/problems/univalued-binary-tree)

[English Version](/solution/0900-0999/0965.Univalued%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果二叉树每个节点都具有相同的值，那么该二叉树就是<em>单值</em>二叉树。</p>

<p>只有给定的树是单值二叉树时，才返回&nbsp;<code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0965.Univalued%20Binary%20Tree/images/screen-shot-2018-12-25-at-50104-pm.png" style="height: 159px; width: 200px;"></p>

<pre><strong>输入：</strong>[1,1,1,1,1,null,1]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0965.Univalued%20Binary%20Tree/images/screen-shot-2018-12-25-at-50050-pm.png" style="height: 158px; width: 200px;"></p>

<pre><strong>输入：</strong>[2,2,2,5,2]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>给定树的节点数范围是&nbsp;<code>[1, 100]</code>。</li>
	<li>每个节点的值都是整数，范围为&nbsp;<code>[0, 99]</code>&nbsp;。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们记根节点的值为 $x$，然后设计一个函数 $\text{dfs}(\text{root})$，它表示当前节点的值是否等于 $x$，并且它的左右子树也是单值二叉树。

在函数 $\text{dfs}(\text{root})$ 中，如果当前节点为空，那么返回 $\text{true}$，否则，如果当前节点的值等于 $x$，并且它的左右子树也是单值二叉树，那么返回 $\text{true}$，否则返回 $\text{false}$。

在主函数中，我们调用 $\text{dfs}(\text{root})$，并返回结果。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树中的节点数目。

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
    def isUnivalTree(self, root: Optional[TreeNode]) -> bool:
        def dfs(root: Optional[TreeNode]) -> bool:
            if root is None:
                return True
            return root.val == x and dfs(root.left) and dfs(root.right)

        x = root.val
        return dfs(root)
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
    private int x;

    public boolean isUnivalTree(TreeNode root) {
        x = root.val;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        return root.val == x && dfs(root.left) && dfs(root.right);
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
    bool isUnivalTree(TreeNode* root) {
        int x = root->val;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> bool {
            if (!root) {
                return true;
            }
            return root->val == x && dfs(root->left) && dfs(root->right);
        };
        return dfs(root);
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
func isUnivalTree(root *TreeNode) bool {
	x := root.Val
	var dfs func(*TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		return root.Val == x && dfs(root.Left) && dfs(root.Right)
	}
	return dfs(root)
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

function isUnivalTree(root: TreeNode | null): boolean {
    const x = root!.val;
    const dfs = (root: TreeNode | null): boolean => {
        if (!root) {
            return true;
        }
        return root.val === x && dfs(root.left) && dfs(root.right);
    };
    return dfs(root);
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
    pub fn is_unival_tree(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        let x = root.as_ref().unwrap().borrow().val;

        fn dfs(node: Option<Rc<RefCell<TreeNode>>>, x: i32) -> bool {
            if let Some(n) = node {
                let n = n.borrow();
                n.val == x && dfs(n.left.clone(), x) && dfs(n.right.clone(), x)
            } else {
                true
            }
        }

        dfs(root, x)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
