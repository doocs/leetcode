---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1038.Binary%20Search%20Tree%20to%20Greater%20Sum%20Tree/README_EN.md
rating: 1374
source: Weekly Contest 135 Q2
tags:
    - Tree
    - Depth-First Search
    - Binary Search Tree
    - Binary Tree
---

<!-- problem:start -->

# [1038. Binary Search Tree to Greater Sum Tree](https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree)

[中文文档](/solution/1000-1099/1038.Binary%20Search%20Tree%20to%20Greater%20Sum%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.</p>

<p>As a reminder, a <em>binary search tree</em> is a tree that satisfies these constraints:</p>

<ul>
	<li>The left subtree of a node contains only nodes with keys <strong>less than</strong> the node&#39;s key.</li>
	<li>The right subtree of a node contains only nodes with keys <strong>greater than</strong> the node&#39;s key.</li>
	<li>Both the left and right subtrees must also be binary search trees.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1038.Binary%20Search%20Tree%20to%20Greater%20Sum%20Tree/images/tree.png" style="width: 400px; height: 273px;" />
<pre>
<strong>Input:</strong> root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
<strong>Output:</strong> [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [0,null,1]
<strong>Output:</strong> [1,null,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 100]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 100</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as 538: <a href="https://leetcode.com/problems/convert-bst-to-greater-tree/" target="_blank">https://leetcode.com/problems/convert-bst-to-greater-tree/</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

Traverse the binary search tree in the order of "right-root-left". Accumulate all the node values encountered into $s$, and assign the accumulated value to the corresponding `node`.

Time complexity is $O(n)$, and space complexity is $O(n)$, where $n$ is the number of nodes in the binary search tree.

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
    def bstToGst(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs(root: Optional[TreeNode]):
            if root is None:
                return
            dfs(root.right)
            nonlocal s
            s += root.val
            root.val = s
            dfs(root.left)

        s = 0
        dfs(root)
        return root
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
    private int s;

    public TreeNode bstToGst(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
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
    TreeNode* bstToGst(TreeNode* root) {
        int s = 0;
        auto dfs = [&](this auto&& dfs, TreeNode* root) {
            if (!root) {
                return;
            }
            dfs(root->right);
            s += root->val;
            root->val = s;
            dfs(root->left);
        };
        dfs(root);
        return root;
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
func bstToGst(root *TreeNode) *TreeNode {
	s := 0
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Right)
		s += root.Val
		root.Val = s
		dfs(root.Left)
	}
	dfs(root)
	return root
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

function bstToGst(root: TreeNode | null): TreeNode | null {
    let s = 0;
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    };
    dfs(root);
    return root;
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
    pub fn bst_to_gst(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut s = 0;
        Self::dfs(&root, &mut s);
        root
    }

    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, s: &mut i32) {
        if let Some(node) = root {
            let mut node = node.borrow_mut();
            Self::dfs(&node.right, s);
            *s += node.val;
            node.val = *s;
            Self::dfs(&node.left, s);
        }
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
 * @return {TreeNode}
 */
var bstToGst = function (root) {
    let s = 0;
    function dfs(root) {
        if (!root) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    }
    dfs(root);
    return root;
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

int dfs(struct TreeNode* root, int sum) {
    if (root) {
        sum = dfs(root->right, sum) + root->val;
        root->val = sum;
        sum = dfs(root->left, sum);
    }
    return sum;
}

struct TreeNode* bstToGst(struct TreeNode* root) {
    dfs(root, 0);
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Morris Traversal

Morris traversal does not require a stack, with a time complexity of $O(n)$ and a space complexity of $O(1)$. The core idea is as follows:

Define $s$ as the cumulative sum of the node values in the binary search tree. Traverse the binary tree nodes:

1. If the right subtree of the current node `root` is null, **add the current node value to $s$**, update the current node value to $s$, and move the current node to `root.left`.
2. If the right subtree of the current node `root` is not null, find the leftmost node `next` in the right subtree (i.e., the successor node of `root` in an in-order traversal):
    - If the left subtree of the successor node `next` is null, set the left subtree of `next` to point to the current node `root`, and move the current node to `root.right`.
    - If the left subtree of the successor node `next` is not null, **add the current node value to $s$**, update the current node value to $s$, then set the left subtree of `next` to null (i.e., remove the link between `next` and `root`), and move the current node to `root.left`.
3. Repeat the above steps until the binary tree nodes are null, at which point the traversal is complete.
4. Finally, return the root node of the binary search tree.

> Morris reverse in-order traversal follows the same idea as Morris in-order traversal, except that the traversal order changes from "left-root-right" to "right-root-left".

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
    def bstToGst(self, root: TreeNode) -> TreeNode:
        s = 0
        node = root
        while root:
            if root.right is None:
                s += root.val
                root.val = s
                root = root.left
            else:
                next = root.right
                while next.left and next.left != root:
                    next = next.left
                if next.left is None:
                    next.left = root
                    root = root.right
                else:
                    s += root.val
                    root.val = s
                    next.left = None
                    root = root.left
        return node
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
    public TreeNode bstToGst(TreeNode root) {
        int s = 0;
        TreeNode node = root;
        while (root != null) {
            if (root.right == null) {
                s += root.val;
                root.val = s;
                root = root.left;
            } else {
                TreeNode next = root.right;
                while (next.left != null && next.left != root) {
                    next = next.left;
                }
                if (next.left == null) {
                    next.left = root;
                    root = root.right;
                } else {
                    s += root.val;
                    root.val = s;
                    next.left = null;
                    root = root.left;
                }
            }
        }
        return node;
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
    TreeNode* bstToGst(TreeNode* root) {
        int s = 0;
        TreeNode* node = root;
        while (root) {
            if (root->right == nullptr) {
                s += root->val;
                root->val = s;
                root = root->left;
            } else {
                TreeNode* next = root->right;
                while (next->left && next->left != root) {
                    next = next->left;
                }
                if (next->left == nullptr) {
                    next->left = root;
                    root = root->right;
                } else {
                    s += root->val;
                    root->val = s;
                    next->left = nullptr;
                    root = root->left;
                }
            }
        }
        return node;
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
func bstToGst(root *TreeNode) *TreeNode {
	s := 0
	node := root
	for root != nil {
		if root.Right == nil {
			s += root.Val
			root.Val = s
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				next.Left = root
				root = root.Right
			} else {
				s += root.Val
				root.Val = s
				next.Left = nil
				root = root.Left
			}
		}
	}
	return node
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

function bstToGst(root: TreeNode | null): TreeNode | null {
    let cur = root;
    let sum = 0;
    while (cur != null) {
        const { val, left, right } = cur;
        if (right == null) {
            sum += val;
            cur.val = sum;
            cur = left;
        } else {
            let next = right;
            while (next.left != null && next.left != cur) {
                next = next.left;
            }
            if (next.left == null) {
                next.left = cur;
                cur = right;
            } else {
                next.left = null;
                sum += val;
                cur.val = sum;
                cur = left;
            }
        }
    }
    return root;
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

struct TreeNode* bstToGst(struct TreeNode* root) {
    struct TreeNode* cur = root;
    int sum = 0;
    while (cur) {
        if (!cur->right) {
            sum += cur->val;
            cur->val = sum;
            cur = cur->left;
        } else {
            struct TreeNode* next = cur->right;
            while (next->left && next->left != cur) {
                next = next->left;
            }
            if (!next->left) {
                next->left = cur;
                cur = cur->right;
            } else {
                next->left = NULL;
                sum += cur->val;
                cur->val = sum;
                cur = cur->left;
            }
        }
    }
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
