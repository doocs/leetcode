---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0114.Flatten%20Binary%20Tree%20to%20Linked%20List/README_EN.md
tags:
    - Stack
    - Tree
    - Depth-First Search
    - Linked List
    - Binary Tree
---

<!-- problem:start -->

# [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list)

[中文文档](/solution/0100-0199/0114.Flatten%20Binary%20Tree%20to%20Linked%20List/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, flatten the tree into a &quot;linked list&quot;:</p>

<ul>
	<li>The &quot;linked list&quot; should use the same <code>TreeNode</code> class where the <code>right</code> child pointer points to the next node in the list and the <code>left</code> child pointer is always <code>null</code>.</li>
	<li>The &quot;linked list&quot; should be in the same order as a <a href="https://en.wikipedia.org/wiki/Tree_traversal#Pre-order,_NLR" target="_blank"><strong>pre-order</strong><strong> traversal</strong></a> of the binary tree.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0114.Flatten%20Binary%20Tree%20to%20Linked%20List/images/flaten.jpg" style="width: 500px; height: 226px;" />
<pre>
<strong>Input:</strong> root = [1,2,5,3,4,null,6]
<strong>Output:</strong> [1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [0]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 2000]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you flatten the tree in-place (with <code>O(1)</code> extra space)?

## Solutions

<!-- solution:start -->

### Solution 1: Find Predecessor Node

The visit order of preorder traversal is "root, left subtree, right subtree". After the last node of the left subtree is visited, the right subtree node of the root node will be visited next.

Therefore, for the current node, if its left child node is not null, we find the rightmost node of the left subtree as the predecessor node, and then assign the right child node of the current node to the right child node of the predecessor node. Then assign the left child node of the current node to the right child node of the current node, and set the left child node of the current node to null. Then take the right child node of the current node as the next node and continue processing until all nodes are processed.

The time complexity is $O(n)$, where $n$ is the number of nodes in the tree. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        while root:
            if root.left:
                pre = root.left
                while pre.right:
                    pre = pre.right
                pre.right = root.right
                root.right = root.left
                root.left = None
            root = root.right
```

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
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 找到当前节点左子树的最右节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // 将左子树的最右节点指向原来的右子树
                pre.right = root.right;

                // 将当前节点指向左子树
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
```

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
    void flatten(TreeNode* root) {
        while (root) {
            if (root->left) {
                TreeNode* pre = root->left;
                while (pre->right) {
                    pre = pre->right;
                }
                pre->right = root->right;
                root->right = root->left;
                root->left = nullptr;
            }
            root = root->right;
        }
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func flatten(root *TreeNode) {
	for root != nil {
		if root.Left != nil {
			pre := root.Left
			for pre.Right != nil {
				pre = pre.Right
			}
			pre.Right = root.Right
			root.Right = root.Left
			root.Left = nil
		}
		root = root.Right
	}
}
```

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

/**
 Do not return anything, modify root in-place instead.
 */
function flatten(root: TreeNode | null): void {
    while (root !== null) {
        if (root.left !== null) {
            let pre = root.left;
            while (pre.right !== null) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        root = root.right;
    }
}
```

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
    #[allow(dead_code)]
    pub fn flatten(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        if root.is_none() {
            return;
        }
        let mut v: Vec<Option<Rc<RefCell<TreeNode>>>> = Vec::new();
        // Initialize the vector
        Self::pre_order_traverse(&mut v, root);
        // Traverse the vector
        let n = v.len();
        for i in 0..n - 1 {
            v[i].as_ref().unwrap().borrow_mut().left = None;
            v[i].as_ref().unwrap().borrow_mut().right = v[i + 1].clone();
        }
    }

    #[allow(dead_code)]
    fn pre_order_traverse(
        v: &mut Vec<Option<Rc<RefCell<TreeNode>>>>,
        root: &Option<Rc<RefCell<TreeNode>>>
    ) {
        if root.is_none() {
            return;
        }
        v.push(root.clone());
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        Self::pre_order_traverse(v, &left);
        Self::pre_order_traverse(v, &right);
    }
}
```

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
 * @return {void} Do not return anything, modify root in-place instead.
 */
var flatten = function (root) {
    while (root) {
        if (root.left) {
            let pre = root.left;
            while (pre.right) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        root = root.right;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func flatten(root *TreeNode) {
	for root != nil {
		left, right := root.Left, root.Right
		root.Left = nil
		if left != nil {
			root.Right = left
			for left.Right != nil {
				left = left.Right
			}
			left.Right = right
		}
		root = root.Right
	}
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
