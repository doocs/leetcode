---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2331.Evaluate%20Boolean%20Binary%20Tree/README.md
rating: 1303
source: 第 82 场双周赛 Q1
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [2331. 计算布尔二叉树的值](https://leetcode.cn/problems/evaluate-boolean-binary-tree)

[English Version](/solution/2300-2399/2331.Evaluate%20Boolean%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <strong>完整二叉树</strong>&nbsp;的根，这棵树有以下特征：</p>

<ul>
	<li><strong>叶子节点</strong>&nbsp;要么值为&nbsp;<code>0</code>&nbsp;要么值为&nbsp;<code>1</code>&nbsp;，其中&nbsp;<code>0</code> 表示&nbsp;<code>False</code>&nbsp;，<code>1</code> 表示&nbsp;<code>True</code>&nbsp;。</li>
	<li><strong>非叶子节点 </strong>要么值为 <code>2</code>&nbsp;要么值为 <code>3</code>&nbsp;，其中&nbsp;<code>2</code>&nbsp;表示逻辑或&nbsp;<code>OR</code> ，<code>3</code>&nbsp;表示逻辑与&nbsp;<code>AND</code>&nbsp;。</li>
</ul>

<p><strong>计算</strong>&nbsp;一个节点的值方式如下：</p>

<ul>
	<li>如果节点是个叶子节点，那么节点的 <strong>值</strong>&nbsp;为它本身，即&nbsp;<code>True</code>&nbsp;或者&nbsp;<code>False</code>&nbsp;。</li>
	<li>否则，<strong>计算</strong>&nbsp;两个孩子的节点值，然后将该节点的运算符对两个孩子值进行 <strong>运算</strong>&nbsp;。</li>
</ul>

<p>返回根节点<em>&nbsp;</em><code>root</code>&nbsp;的布尔运算值。</p>

<p><strong>完整二叉树</strong>&nbsp;是每个节点有 <code>0</code>&nbsp;个或者 <code>2</code>&nbsp;个孩子的二叉树。</p>

<p><strong>叶子节点</strong>&nbsp;是没有孩子的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2331.Evaluate%20Boolean%20Binary%20Tree/images/example1drawio1.png" style="width: 700px; height: 252px;"></p>

<pre><b>输入：</b>root = [2,1,3,null,null,0,1]
<b>输出：</b>true
<b>解释：</b>上图展示了计算过程。
AND 与运算节点的值为 False AND True = False 。
OR 运算节点的值为 True OR False = True 。
根节点的值为 True ，所以我们返回 true 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>root = [0]
<b>输出：</b>false
<b>解释：</b>根节点是叶子节点，且值为 false，所以我们返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在&nbsp;<code>[1, 1000]</code>&nbsp;之间。</li>
	<li><code>0 &lt;= Node.val &lt;= 3</code></li>
	<li>每个节点的孩子数为&nbsp;<code>0</code> 或&nbsp;<code>2</code>&nbsp;。</li>
	<li>叶子节点的值为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>&nbsp;。</li>
	<li>非叶子节点的值为&nbsp;<code>2</code>&nbsp;或&nbsp;<code>3</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们可以使用递归的方式来求解本题。

对于当前节点 $\textit{root}$：

-   如果其左孩子为空，说明当前节点是叶子节点。如果当前节点的值为 $1$，则返回 $\textit{true}$，否则返回 $\textit{false}$；
-   如果当前节点的值为 $2$，则返回其左孩子和右孩子的递归结果的逻辑或，否则返回其左孩子和右孩子的递归结果的逻辑与。

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
    def evaluateTree(self, root: Optional[TreeNode]) -> bool:
        if root.left is None:
            return bool(root.val)
        op = or_ if root.val == 2 else and_
        return op(self.evaluateTree(root.left), self.evaluateTree(root.right))
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
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null) {
            return root.val == 1;
        }
        if (root.val == 2) {
            return evaluateTree(root.left) || evaluateTree(root.right);
        }
        return evaluateTree(root.left) && evaluateTree(root.right);
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
    bool evaluateTree(TreeNode* root) {
        if (!root->left) {
            return root->val;
        }
        if (root->val == 2) {
            return evaluateTree(root->left) || evaluateTree(root->right);
        }
        return evaluateTree(root->left) && evaluateTree(root->right);
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
func evaluateTree(root *TreeNode) bool {
	if root.Left == nil {
		return root.Val == 1
	}
	if root.Val == 2 {
		return evaluateTree(root.Left) || evaluateTree(root.Right)
	} else {
		return evaluateTree(root.Left) && evaluateTree(root.Right)
	}
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

function evaluateTree(root: TreeNode | null): boolean {
    const { val, left, right } = root;
    if (left === null) {
        return val === 1;
    }
    if (val === 2) {
        return evaluateTree(left) || evaluateTree(right);
    }
    return evaluateTree(left) && evaluateTree(right);
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
    pub fn evaluate_tree(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        match root {
            Some(node) => {
                let node = node.borrow();
                if node.left.is_none() {
                    return node.val == 1;
                }
                if node.val == 2 {
                    return Self::evaluate_tree(node.left.clone())
                        || Self::evaluate_tree(node.right.clone());
                }
                Self::evaluate_tree(node.left.clone()) && Self::evaluate_tree(node.right.clone())
            }
            None => false,
        }
    }
}
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
bool evaluateTree(struct TreeNode* root) {
    if (!root->left) {
        return root->val == 1;
    }
    if (root->val == 2) {
        return evaluateTree(root->left) || evaluateTree(root->right);
    }
    return evaluateTree(root->left) && evaluateTree(root->right);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
