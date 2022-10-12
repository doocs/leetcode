# [572. Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree)

[中文文档](/solution/0500-0599/0572.Subtree%20of%20Another%20Tree/README.md)

## Description

<p>Given the roots of two binary trees <code>root</code> and <code>subRoot</code>, return <code>true</code> if there is a subtree of <code>root</code> with the same structure and node values of<code> subRoot</code> and <code>false</code> otherwise.</p>

<p>A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code> and all of this node&#39;s descendants. The tree <code>tree</code> could also be considered as a subtree of itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0572.Subtree%20of%20Another%20Tree/images/subtree1-tree.jpg" style="width: 532px; height: 400px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,2], subRoot = [4,1,2]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0572.Subtree%20of%20Another%20Tree/images/subtree2-tree.jpg" style="width: 502px; height: 458px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the <code>root</code> tree is in the range <code>[1, 2000]</code>.</li>
	<li>The number of nodes in the <code>subRoot</code> tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= root.val &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= subRoot.val &lt;= 10<sup>4</sup></code></li>
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
    def isSubtree(self, root: TreeNode, subRoot: TreeNode) -> bool:
        def dfs(root1, root2):
            if root1 is None and root2 is None:
                return True
            if root1 is None or root2 is None:
                return False
            return (
                root1.val == root2.val
                and dfs(root1.left, root2.left)
                and dfs(root1.right, root2.right)
            )

        if root is None:
            return False
        return (
            dfs(root, subRoot)
            or self.isSubtree(root.left, subRoot)
            or self.isSubtree(root.right, subRoot)
        )
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return dfs(root, subRoot) || isSubtree(root.left, subRoot)
            || isSubtree(root.right, subRoot);
    }

    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && dfs(root1.left, root2.left)
            && dfs(root1.right, root2.right);
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
    bool isSubtree(TreeNode* root, TreeNode* subRoot) {
        if (!root) return 0;
        return dfs(root, subRoot) || isSubtree(root->left, subRoot) || isSubtree(root->right, subRoot);
    }

    bool dfs(TreeNode* root1, TreeNode* root2) {
        if (!root1 && !root2) return 1;
        if (!root1 || !root2) return 0;
        return root1->val == root2->val && dfs(root1->left, root2->left) && dfs(root1->right, root2->right);
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
func isSubtree(root *TreeNode, subRoot *TreeNode) bool {
	if root == nil {
		return false
	}
	var dfs func(root1, root2 *TreeNode) bool
	dfs = func(root1, root2 *TreeNode) bool {
		if root1 == nil && root2 == nil {
			return true
		}
		if root1 == nil || root2 == nil {
			return false
		}
		return root1.Val == root2.Val && dfs(root1.Left, root2.Left) && dfs(root1.Right, root2.Right)
	}
	return dfs(root, subRoot) || isSubtree(root.Left, subRoot) || isSubtree(root.Right, subRoot)
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
 * @param {TreeNode} subRoot
 * @return {boolean}
 */
var isSubtree = function (root, subRoot) {
    if (!root) return false;
    let dfs = function (root1, root2) {
        if (!root1 && !root2) {
            return true;
        }
        if (!root1 || !root2) {
            return false;
        }
        return (
            root1.val == root2.val &&
            dfs(root1.left, root2.left) &&
            dfs(root1.right, root2.right)
        );
    };
    return (
        dfs(root, subRoot) ||
        isSubtree(root.left, subRoot) ||
        isSubtree(root.right, subRoot)
    );
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

const dfs = (root: TreeNode | null, subRoot: TreeNode | null) => {
    if (root == null && subRoot == null) {
        return true;
    }
    if (root == null || subRoot == null || root.val !== subRoot.val) {
        return false;
    }
    return dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right);
};

function isSubtree(root: TreeNode | null, subRoot: TreeNode | null): boolean {
    if (root == null) {
        return false;
    }
    return (
        dfs(root, subRoot) ||
        isSubtree(root.left, subRoot) ||
        isSubtree(root.right, subRoot)
    );
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, sub_root: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if root.is_none() && sub_root.is_none() {
            return true;
        }
        if root.is_none() || sub_root.is_none() {
            return false;
        }
        let root = root.as_ref().unwrap().borrow();
        let sub_root = sub_root.as_ref().unwrap().borrow();
        root.val == sub_root.val
            && Self::dfs(&root.left, &sub_root.left)
            && Self::dfs(&root.right, &sub_root.right)
    }

    fn help(
        root: &Option<Rc<RefCell<TreeNode>>>,
        sub_root: &Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        if root.is_none() {
            return false;
        }
        Self::dfs(root, sub_root)
            || Self::help(&root.as_ref().unwrap().borrow().left, sub_root)
            || Self::help(&root.as_ref().unwrap().borrow().right, sub_root)
    }

    pub fn is_subtree(
        root: Option<Rc<RefCell<TreeNode>>>,
        sub_root: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        Self::help(&root, &sub_root)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
