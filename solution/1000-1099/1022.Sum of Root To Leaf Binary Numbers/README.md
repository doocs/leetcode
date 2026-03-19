---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/README.md
rating: 1462
source: 第 131 场周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1022. 从根到叶的二进制数之和](https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers)

[English Version](/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出一棵二叉树，其上每个结点的值都是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>&nbsp;。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。</p>

<ul>
	<li>例如，如果路径为&nbsp;<code>0 -&gt; 1 -&gt; 1 -&gt; 0 -&gt; 1</code>，那么它表示二进制数&nbsp;<code>01101</code>，也就是&nbsp;<code>13</code>&nbsp;。</li>
</ul>

<p>对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。</p>

<p>返回这些数字之和。题目数据保证答案是一个 <strong>32 位 </strong>整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/images/sum-of-root-to-leaf-binary-numbers.png" />
<pre>
<strong>输入：</strong>root = [1,0,1,0,1,0,1]
<strong>输出：</strong>22
<strong>解释：</strong>(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [0]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在&nbsp;<code>[1, 1000]</code>&nbsp;范围内</li>
	<li><code>Node.val</code>&nbsp;仅为 <code>0</code> 或 <code>1</code>&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们设计一个递归函数 $\text{dfs}(root, t)$，它接收两个参数：当前节点 $root$ 和当前节点的父节点对应的二进制数 $t$。函数的返回值是从当前节点到叶子节点的路径所表示的二进制数之和。答案即为 $\textrm{dfs}(root, 0)$。

递归函数的逻辑如下：

- 如果当前节点 $root$ 为空，则返回 $0$，否则计算当前节点对应的二进制数 $t$，即 $t = t \ll 1 | root.val$。
- 如果当前节点是叶子节点，则返回 $t$，否则返回 $\textrm{dfs}(root.left, t)$ 和 $\textrm{dfs}(root.right, t)$ 的和。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。对每个节点访问一次；递归栈需要 $O(n)$ 的空间。

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
    def sumRootToLeaf(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode], x: int) -> int:
            if root is None:
                return 0
            x = x << 1 | root.val
            if root.left == root.right:
                return x
            return dfs(root.left, x) + dfs(root.right, x)

        return dfs(root, 0)
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
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
        x = x << 1 | root.val;
        if (root.left == root.right) {
            return x;
        }
        return dfs(root.left, x) + dfs(root.right, x);
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
    int sumRootToLeaf(TreeNode* root) {
        auto dfs = [&](this auto&& dfs, TreeNode* root, int x) -> int {
            if (!root) {
                return 0;
            }
            x = x << 1 | root->val;
            if (root->left == root->right) {
                return x;
            }
            return dfs(root->left, x) + dfs(root->right, x);
        };
        return dfs(root, 0);
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
func sumRootToLeaf(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, x int) int {
		if root == nil {
			return 0
		}
		x = x<<1 | root.Val
		if root.Left == root.Right {
			return x
		}
		return dfs(root.Left, x) + dfs(root.Right, x)
	}
	return dfs(root, 0)
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

function sumRootToLeaf(root: TreeNode | null): number {
    const dfs = (node: TreeNode | null, x: number): number => {
        if (node === null) {
            return 0;
        }

        x = (x << 1) | node.val;

        if (node.left === null && node.right === null) {
            return x;
        }

        return dfs(node.left, x) + dfs(node.right, x);
    };

    return dfs(root, 0);
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
use std::rc::Rc;
use std::cell::RefCell;

impl Solution {
    pub fn sum_root_to_leaf(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        fn dfs(node: Option<Rc<RefCell<TreeNode>>>, x: i32) -> i32 {
            if let Some(n) = node {
                let n_ref = n.borrow();
                let x = (x << 1) | n_ref.val;

                if n_ref.left.is_none() && n_ref.right.is_none() {
                    return x;
                }

                dfs(n_ref.left.clone(), x) + dfs(n_ref.right.clone(), x)
            } else {
                0
            }
        }

        dfs(root, 0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
