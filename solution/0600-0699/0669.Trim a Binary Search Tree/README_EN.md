# [669. Trim a Binary Search Tree](https://leetcode.com/problems/trim-a-binary-search-tree)

[中文文档](/solution/0600-0699/0669.Trim%20a%20Binary%20Search%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary search tree and the lowest and highest boundaries as <code>low</code> and <code>high</code>, trim the tree so that all its elements lies in <code>[low, high]</code>. Trimming the tree should <strong>not</strong> change the relative structure of the elements that will remain in the tree (i.e., any node&#39;s descendant should remain a descendant). It can be proven that there is a <strong>unique answer</strong>.</p>

<p>Return <em>the root of the trimmed binary search tree</em>. Note that the root may change depending on the given bounds.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0669.Trim%20a%20Binary%20Search%20Tree/images/trim1.jpg" style="width: 450px; height: 126px;" />
<pre>
<strong>Input:</strong> root = [1,0,2], low = 1, high = 2
<strong>Output:</strong> [1,null,2]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0669.Trim%20a%20Binary%20Search%20Tree/images/trim2.jpg" style="width: 450px; height: 277px;" />
<pre>
<strong>Input:</strong> root = [3,0,4,null,2,null,null,1], low = 1, high = 3
<strong>Output:</strong> [3,2,null,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>The value of each node in the tree is <strong>unique</strong>.</li>
	<li><code>root</code> is guaranteed to be a valid binary search tree.</li>
	<li><code>0 &lt;= low &lt;= high &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def trimBST(
        self, root: Optional[TreeNode], low: int, high: int
    ) -> Optional[TreeNode]:
        def dfs(root):
            if root is None:
                return root
            if root.val > high:
                return dfs(root.left)
            if root.val < low:
                return dfs(root.right)
            root.left = dfs(root.left)
            root.right = dfs(root.right)
            return root

        return dfs(root)
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def trimBST(
        self, root: Optional[TreeNode], low: int, high: int
    ) -> Optional[TreeNode]:
        while root and (root.val < low or root.val > high):
            root = root.left if root.val > high else root.right
        if root is None:
            return None
        node = root
        while node.left:
            if node.left.val < low:
                node.left = node.left.right
            else:
                node = node.left
        node = root
        while node.right:
            if node.right.val > high:
                node.right = node.right.left
            else:
                node = node.right
        return root
```

### **Java**

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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return root;
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            root = root.val < low ? root.right : root.left;
        }
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        while (node.left != null) {
            if (node.left.val < low) {
                node.left = node.left.right;
            } else {
                node = node.left;
            }
        }
        node = root;
        while (node.right != null) {
            if (node.right.val > high) {
                node.right = node.right.left;
            } else {
                node = node.right;
            }
        }
        return root;
    }
}
```

### **C++**

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
    TreeNode* trimBST(TreeNode* root, int low, int high) {
        if (!root) return root;
        if (root->val > high) return trimBST(root->left, low, high);
        if (root->val < low) return trimBST(root->right, low, high);
        root->left = trimBST(root->left, low, high);
        root->right = trimBST(root->right, low, high);
        return root;
    }
};
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
    TreeNode* trimBST(TreeNode* root, int low, int high) {
        while (root && (root->val < low || root->val > high)) {
            root = root->val < low ? root->right : root->left;
        }
        if (!root) {
            return root;
        }
        TreeNode* node = root;
        while (node->left) {
            if (node->left->val < low) {
                node->left = node->left->right;
            } else {
                node = node->left;
            }
        }
        node = root;
        while (node->right) {
            if (node->right->val > high) {
                node->right = node->right->left;
            } else {
                node = node->right;
            }
        }
        return root;
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func trimBST(root *TreeNode, low int, high int) *TreeNode {
	if root == nil {
		return root
	}
	if root.Val > high {
		return trimBST(root.Left, low, high)
	}
	if root.Val < low {
		return trimBST(root.Right, low, high)
	}
	root.Left = trimBST(root.Left, low, high)
	root.Right = trimBST(root.Right, low, high)
	return root
}
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
func trimBST(root *TreeNode, low int, high int) *TreeNode {
	for root != nil && (root.Val < low || root.Val > high) {
		if root.Val < low {
			root = root.Right
		} else {
			root = root.Left
		}
	}
	if root == nil {
		return nil
	}
	node := root
	for node.Left != nil {
		if node.Left.Val < low {
			node.Left = node.Left.Right
		} else {
			node = node.Left
		}
	}
	node = root
	for node.Right != nil {
		if node.Right.Val > high {
			node.Right = node.Right.Left
		} else {
			node = node.Right
		}
	}
	return root
}
```

### **JavaScript**

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
 * @param {number} low
 * @param {number} high
 * @return {TreeNode}
 */
var trimBST = function (root, low, high) {
    function dfs(root) {
        if (!root) {
            return root;
        }
        if (root.val < low) {
            return dfs(root.right);
        }
        if (root.val > high) {
            return dfs(root.left);
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        return root;
    }
    return dfs(root);
};
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
 * @param {number} low
 * @param {number} high
 * @return {TreeNode}
 */
var trimBST = function (root, low, high) {
    while (root && (root.val < low || root.val > high)) {
        root = root.val < low ? root.right : root.left;
    }
    if (!root) {
        return root;
    }
    let node = root;
    while (node.left) {
        if (node.left.val < low) {
            node.left = node.left.right;
        } else {
            node = node.left;
        }
    }
    node = root;
    while (node.right) {
        if (node.right.val > high) {
            node.right = node.right.left;
        } else {
            node = node.right;
        }
    }
    return root;
};
```

### **C**

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */


struct TreeNode *trimBST(struct TreeNode *root, int low, int high) {
    if (!root) {
        return root;
    }
    if (root->val < low) {
        return trimBST(root->right, low, high);
    }
    if (root->val > high) {
        return trimBST(root->left, low, high);
    }
    root->left = trimBST(root->left, low, high);
    root->right = trimBST(root->right, low, high);
    return root;
}
```

### **TypeScript**

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

function trimBST(
    root: TreeNode | null,
    low: number,
    high: number,
): TreeNode | null {
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return root;
        }
        const { val, left, right } = root;
        if (val < low || val > high) {
            return dfs(left) || dfs(right);
        }
        root.left = dfs(left);
        root.right = dfs(right);
        return root;
    };
    return dfs(root);
}
```

### **Rust**

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
    pub fn trim_bst(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        low: i32,
        high: i32,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() {
            return root;
        }
        {
            let mut node = root.as_mut().unwrap().borrow_mut();
            if node.val < low {
                return Self::trim_bst(node.right.take(), low, high);
            }
            if node.val > high {
                return Self::trim_bst(node.left.take(), low, high);
            }
            node.left = Self::trim_bst(node.left.take(), low, high);
            node.right = Self::trim_bst(node.right.take(), low, high);
        }
        root
    }
}
```

### **...**

```

```

<!-- tabs:end -->
