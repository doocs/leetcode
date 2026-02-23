---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/README_EN.md
rating: 1462
source: Weekly Contest 131 Q2
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [1022. Sum of Root To Leaf Binary Numbers](https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers)

[中文文档](/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a binary tree where each node has a value <code>0</code> or <code>1</code>. Each root-to-leaf path represents a binary number starting with the most significant bit.</p>

<ul>
	<li>For example, if the path is <code>0 -&gt; 1 -&gt; 1 -&gt; 0 -&gt; 1</code>, then this could represent <code>01101</code> in binary, which is <code>13</code>.</li>
</ul>

<p>For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return <em>the sum of these numbers</em>.</p>

<p>The test cases are generated so that the answer fits in a <strong>32-bits</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/images/sum-of-root-to-leaf-binary-numbers.png" style="width: 400px; height: 263px;" />
<pre>
<strong>Input:</strong> root = [1,0,1,0,1,0,1]
<strong>Output:</strong> 22
<strong>Explanation: </strong>(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [0]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>Node.val</code> is <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

We design a recursive function $\text{dfs}(root, t)$, which takes two parameters: the current node $root$ and the binary number $t$ corresponding to the parent node of the current node. The return value of the function is the sum of binary numbers represented by paths from the current node to leaf nodes. The answer is $\textrm{dfs}(root, 0)$.

The logic of the recursive function is as follows:

- If the current node $root$ is null, return $0$; otherwise, calculate the binary number $t$ corresponding to the current node, i.e., $t = t \ll 1 | root.val$.
- If the current node is a leaf node, return $t$; otherwise, return the sum of $\textrm{dfs}(root.left, t)$ and $\textrm{dfs}(root.right, t)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary tree. Each node is visited once; the recursion stack requires $O(n)$ space.

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
