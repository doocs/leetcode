---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/README.md
rating: 1405
source: 第 190 场周赛 Q3
tags:
    - 位运算
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1457. 二叉树中的伪回文路径](https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree)

[English Version](/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「<strong>伪回文</strong>」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。</p>

<p>请你返回从根到叶子节点的所有路径中&nbsp;<strong>伪回文&nbsp;</strong>路径的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/images/palindromic_paths_1.png" style="height: 201px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [2,3,1,3,1,null,1]
<strong>输出：</strong>2 
<strong>解释：</strong>上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
     在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/images/palindromic_paths_2.png" style="height: 314px; width: 300px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [2,1,1,1,3,null,null,null,null,null,1]
<strong>输出：</strong>1 
<strong>解释：</strong>上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径 [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。
     这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [9]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定二叉树的节点数目在范围&nbsp;<code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 位运算

一条路径是伪回文路径，当且仅当该路径经过的节点值的出现次数为奇数的数字为 $0$ 个或 $1$ 个。

由于二叉树节点值的范围为 $1$ 到 $9$，因此对于每一条从根到叶子的路径，我们可以用一个长度为 $10$ 的二进制数 $mask$ 表示当前路径经过的节点值的出现状态，其中 $mask$ 的第 $i$ 位为 $1$，表示当前路径上节点值 $i$ 的出现次数为奇数，否则表示其出现次数为偶数。那么，如果一条路径是伪回文路径，需要满足 $mask \&(mask - 1) = 0$，其中 $\&$ 表示按位与运算。

基于以上分析，我们可以使用深度优先搜索的方法计算路径数。我们定义一个函数 $dfs(root, mask)$，表示从当前 $root$ 节点开始，且当前节点的状态为 $mask$ 的所有伪回文路径的个数。那么答案就是 $dfs(root, 0)$。

函数 $dfs(root, mask)$ 的执行逻辑如下：

如果 $root$ 为空，则返回 $0$；

否则，令 $mask = mask \oplus 2^{root.val}$，其中 $\oplus$ 表示按位异或运算。

如果 $root$ 是叶子节点，那么如果 $mask \&(mask - 1) = 0$，返回 $1$，否则返回 $0$；

如果 $root$ 不是叶子节点，返回 $dfs(root.left, mask) + dfs(root.right, mask)$。

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
    def pseudoPalindromicPaths(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode], mask: int):
            if root is None:
                return 0
            mask ^= 1 << root.val
            if root.left is None and root.right is None:
                return int((mask & (mask - 1)) == 0)
            return dfs(root.left, mask) + dfs(root.right, mask)

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
    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int mask) {
        if (root == null) {
            return 0;
        }
        mask ^= 1 << root.val;
        if (root.left == null && root.right == null) {
            return (mask & (mask - 1)) == 0 ? 1 : 0;
        }
        return dfs(root.left, mask) + dfs(root.right, mask);
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
    int pseudoPalindromicPaths(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int mask) {
            if (!root) {
                return 0;
            }
            mask ^= 1 << root->val;
            if (!root->left && !root->right) {
                return (mask & (mask - 1)) == 0 ? 1 : 0;
            }
            return dfs(root->left, mask) + dfs(root->right, mask);
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
func pseudoPalindromicPaths(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, mask int) int {
		if root == nil {
			return 0
		}
		mask ^= 1 << root.Val
		if root.Left == nil && root.Right == nil {
			if mask&(mask-1) == 0 {
				return 1
			}
			return 0
		}
		return dfs(root.Left, mask) + dfs(root.Right, mask)
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

function pseudoPalindromicPaths(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, mask: number): number => {
        if (!root) {
            return 0;
        }
        mask ^= 1 << root.val;
        if (!root.left && !root.right) {
            return (mask & (mask - 1)) === 0 ? 1 : 0;
        }
        return dfs(root.left, mask) + dfs(root.right, mask);
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
    pub fn pseudo_palindromic_paths(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, mask: i32) -> i32 {
            if let Some(node) = root {
                let mut mask = mask;
                let val = node.borrow().val;
                mask ^= 1 << val;

                if node.borrow().left.is_none() && node.borrow().right.is_none() {
                    return if (mask & (mask - 1)) == 0 { 1 } else { 0 };
                }

                return (
                    dfs(node.borrow().left.clone(), mask) + dfs(node.borrow().right.clone(), mask)
                );
            }
            0
        }

        dfs(root, 0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
