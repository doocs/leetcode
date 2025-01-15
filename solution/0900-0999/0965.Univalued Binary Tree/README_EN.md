---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0965.Univalued%20Binary%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [965. Univalued Binary Tree](https://leetcode.com/problems/univalued-binary-tree)

[中文文档](/solution/0900-0999/0965.Univalued%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>A binary tree is <strong>uni-valued</strong> if every node in the tree has the same value.</p>

<p>Given the <code>root</code> of a binary tree, return <code>true</code><em> if the given tree is <strong>uni-valued</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0965.Univalued%20Binary%20Tree/images/unival_bst_1.png" style="width: 265px; height: 172px;" />
<pre>
<strong>Input:</strong> root = [1,1,1,1,1,null,1]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0965.Univalued%20Binary%20Tree/images/unival_bst_2.png" style="width: 198px; height: 169px;" />
<pre>
<strong>Input:</strong> root = [2,2,2,5,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 100]</code>.</li>
	<li><code>0 &lt;= Node.val &lt; 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We denote the value of the root node as $x$, and then design a function $\text{dfs}(\text{root})$, which indicates whether the current node's value is equal to $x$ and its left and right subtrees are also univalued binary trees.

In the function $\text{dfs}(\text{root})$, if the current node is null, return $\text{true}$; otherwise, if the current node's value is equal to $x$ and its left and right subtrees are also univalued binary trees, return $\text{true}$; otherwise, return $\text{false}$.

In the main function, we call $\text{dfs}(\text{root})$ and return the result.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

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
