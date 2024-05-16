---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.10.Check%20SubTree/README_EN.md
---

<!-- problem:start -->

# [04.10. Check SubTree](https://leetcode.cn/problems/check-subtree-lcci)

[中文文档](/lcci/04.10.Check%20SubTree/README.md)

## Description

<!-- description:start -->

<p>T1&nbsp;and T2 are two very large binary trees, with T1&nbsp;much bigger than T2. Create an algorithm to determine if T2 is a subtree of T1.</p>

<p>A tree T2 is a subtree of T1&nbsp;if there exists a node n in T1&nbsp;such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, the two trees would be identical.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: t1 = [1, 2, 3], t2 = [2]

<strong> Output</strong>: true

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: t1 = [1, null, 2, 4], t2 = [3, 2]

<strong> Output</strong>: false

</pre>

<p><strong>Note: </strong></p>

<ol>
	<li>The node numbers of both tree are in [0, 20000].</li>
</ol>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

First, we check if $t_2$ is null. If it is, then $t_2$ is definitely a subtree of $t_1$, so we return `true`.

Otherwise, we check if $t_1$ is null. If it is, then $t_2$ is definitely not a subtree of $t_1$, so we return `false`.

Next, we check if $t_1$ and $t_2$ are equal. If they are, then $t_2$ is a subtree of $t_1$, so we return `true`. Otherwise, we recursively check if $t_1$'s left subtree is equal to $t_2$, and if $t_1$'s right subtree is equal to $t_2$. If either is `true`, then $t_2$ is a subtree of $t_1$, so we return `true`.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes in $t_1$.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def checkSubTree(self, t1: TreeNode, t2: TreeNode) -> bool:
        def dfs(t1, t2):
            if t2 is None:
                return t1 is None
            if t1 is None or t1.val != t2.val:
                return False
            return dfs(t1.left, t2.left) and dfs(t1.right, t2.right)

        if t2 is None:
            return True
        if t1 is None:
            return False
        if dfs(t1, t2):
            return True
        return self.checkSubTree(t1.left, t2) or self.checkSubTree(t1.right, t2)
```

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
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (dfs(t1, t2)) {
            return true;
        }
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return t1 == null;
        }
        if (t1 == null || t1.val != t2.val) {
            return false;
        }
        return dfs(t1.left, t2.left) && dfs(t1.right, t2.right);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool checkSubTree(TreeNode* t1, TreeNode* t2) {
        if (!t2) {
            return true;
        }
        if (!t1) {
            return false;
        }
        if (dfs(t1, t2)) {
            return true;
        }
        return checkSubTree(t1->left, t2) || checkSubTree(t1->right, t2);
    }

    bool dfs(TreeNode* t1, TreeNode* t2) {
        if (!t2) {
            return !t1;
        }
        if (!t1 || t1->val != t2->val) {
            return false;
        }
        return dfs(t1->left, t2->left) && dfs(t1->right, t2->right);
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
func checkSubTree(t1 *TreeNode, t2 *TreeNode) bool {
	var dfs func(t1, t2 *TreeNode) bool
	dfs = func(t1, t2 *TreeNode) bool {
		if t2 == nil {
			return t1 == nil
		}
		if t1 == nil || t1.Val != t2.Val {
			return false
		}
		return dfs(t1.Left, t2.Left) && dfs(t1.Right, t2.Right)
	}
	if t2 == nil {
		return true
	}
	if t1 == nil {
		return false
	}
	if dfs(t1, t2) {
		return true
	}
	return checkSubTree(t1.Left, t2) || checkSubTree(t1.Right, t2)
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

function checkSubTree(t1: TreeNode | null, t2: TreeNode | null): boolean {
    const dfs = (t1: TreeNode | null, t2: TreeNode | null): boolean => {
        if (!t2) {
            return !t1;
        }
        if (!t1 || t1.val !== t2.val) {
            return false;
        }
        return dfs(t1.left, t2.left) && dfs(t1.right, t2.right);
    };
    if (!t2) {
        return true;
    }
    if (!t1) {
        return false;
    }
    if (dfs(t1, t2)) {
        return true;
    }
    return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
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
    fn dfs(t1: &Option<Rc<RefCell<TreeNode>>>, t2: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        match (t1, t2) {
            (Some(node1), Some(node2)) => {
                let n1 = node1.borrow();
                let n2 = node2.borrow();
                n1.val == n2.val &&
                    Solution::dfs(&n1.left, &n2.left) &&
                    Solution::dfs(&n1.right, &n2.right)
            }
            (None, Some(_)) => false,
            (Some(_), None) => false,
            _ => true, // Both are None
        }
    }

    pub fn check_sub_tree(
        t1: Option<Rc<RefCell<TreeNode>>>,
        t2: Option<Rc<RefCell<TreeNode>>>
    ) -> bool {
        match (t1, t2) {
            (Some(node1), Some(node2)) => {
                let n1 = node1.borrow();
                let n2 = node2.borrow();
                Solution::dfs(&Some(Rc::clone(&node1)), &Some(Rc::clone(&node2))) ||
                    Solution::check_sub_tree(n1.left.clone(), Some(Rc::clone(&node2))) ||
                    Solution::check_sub_tree(n1.right.clone(), Some(Rc::clone(&node2)))
            }
            (Some(_), None) => true,
            (None, Some(_)) => false,
            _ => true, // Both are None or t1 is None
        }
    }
}
```

```swift
/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*
*    init(_ val: Int, _ left: TreeNode? = nil, _ right: TreeNode? = nil) {
*        self.val = val
*        self.left = left
*        self.right = right
*    }
* }
*/

class Solution {
    func checkSubTree(_ t1: TreeNode?, _ t2: TreeNode?) -> Bool {
        if t2 == nil {
            return true
        }
        if t1 == nil {
            return false
        }
        if isSameTree(t1, t2) {
            return true
        }
        return checkSubTree(t1!.left, t2) || checkSubTree(t1!.right, t2)
    }

    private func isSameTree(_ t1: TreeNode?, _ t2: TreeNode?) -> Bool {
        if t1 == nil && t2 == nil {
            return true
        }
        if t1 == nil || t2 == nil {
            return false
        }
        if t1!.val != t2!.val {
            return false
        }
        return isSameTree(t1!.left, t2!.left) && isSameTree(t1!.right, t2!.right)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
