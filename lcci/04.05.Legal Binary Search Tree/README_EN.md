# [04.05. Legal Binary Search Tree](https://leetcode.cn/problems/legal-binary-search-tree-lcci)

[中文文档](/lcci/04.05.Legal%20Binary%20Search%20Tree/README.md)

## Description

<p>Implement a function to check if a binary tree is a binary search tree.</p>

<p><strong>Example&nbsp;1:</strong></p>

<pre>

<strong>Input:</strong>

    2

   / \

  1   3

<strong>Output:</strong> true

</pre>

<p><strong>Example&nbsp;2:</strong></p>

<pre>

<strong>Input:</strong>

    5

   / \

  1   4

&nbsp;    / \

&nbsp;   3   6

<strong>Output:</strong> false

<strong>Explanation:</strong> Input: [5,1,4,null,null,3,6].

&nbsp;    the value of root node is 5, but its right child has value 4.</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    res, t = True, None

    def isValidBST(self, root: TreeNode) -> bool:
        self.isValid(root)
        return self.res

    def isValid(self, root):
        if not root:
            return
        self.isValid(root.left)
        if self.t is None or self.t < root.val:
            self.t = root.val
        else:
            self.res = False
            return
        self.isValid(root.right)
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private boolean res = true;
    private Integer t = null;
    public boolean isValidBST(TreeNode root) {
        isValid(root);
        return res;
    }

    private void isValid(TreeNode root) {
        if (root == null) {
            return;
        }
        isValid(root.left);
        if (t == null || t < root.val) {
            t = root.val;
        } else {
            res = false;
            return;
        }
        isValid(root.right);
    }
}
```

### **Go**

-   Non-recursive in-order traversal

```go
func isValidBST(root *TreeNode) bool {
	stack := make([]*TreeNode, 0)
	var prev *TreeNode = nil
	node := root
	for len(stack) > 0 || node != nil {
		for node != nil {
			stack = append(stack, node)
			node = node.Left
		}
		node = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if prev == nil || node.Val > prev.Val {
			prev = node
		} else {
			return false
		}
		node = node.Right
	}
	return true
}
```

-   Use upper bound and lower bound to check

```go
func isValidBST(root *TreeNode) bool {
	return check(root, math.MinInt64, math.MaxInt64)
}

func check(node *TreeNode, lower, upper int) bool {
	if node == nil {
		return true
	}
	if node.Val <= lower || node.Val >= upper {
		return false
	}
	return check(node.Left, lower, node.Val) && check(node.Right, node.Val, upper)
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isValidBST(TreeNode* root) {
        TreeNode* pre = nullptr;
        TreeNode* cur = root;
        stack<TreeNode*> stk;
        while (cur || !stk.empty()) {
            if (cur) {
                stk.push(cur);
                cur = cur->left;
            } else {
                cur = stk.top();
                stk.pop();
                if (pre && pre->val >= cur->val) {
                    return false;
                }
                pre = cur;
                cur = cur->right;
            }
        }
        return true;
    }
};
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

function isValidBST(root: TreeNode | null): boolean {
    let pre = -Infinity;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return true;
        }
        const { val, left, right } = root;
        if (!dfs(left) || val <= pre) {
            return false;
        }
        pre = val;
        return dfs(right);
    };
    return dfs(root);
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

function isValidBST(root: TreeNode | null): boolean {
    if (root == null) {
        return true;
    }
    const { val, left, right } = root;
    const dfs = (root: TreeNode | null, min: number, max: number) => {
        if (root == null) {
            return true;
        }
        const { val, left, right } = root;
        if (val <= min || val >= max) {
            return false;
        }
        return (
            dfs(left, min, Math.min(val, max)) &&
            dfs(right, Math.max(val, min), max)
        );
    };
    return dfs(left, -Infinity, val) && dfs(right, val, Infinity);
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, pre: &mut Option<i32>) -> bool {
        if root.is_none() {
            return true;
        }
        let root = root.as_ref().unwrap().borrow();
        if !Self::dfs(&root.left, pre) {
            return false;
        }
        if pre.is_some() && pre.unwrap() >= root.val {
            return false;
        }
        *pre = Some(root.val);
        Self::dfs(&root.right, pre)
    }

    pub fn is_valid_bst(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::dfs(&root, &mut None)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
