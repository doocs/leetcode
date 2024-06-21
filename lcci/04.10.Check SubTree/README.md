---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.10.Check%20SubTree/README.md
---

<!-- problem:start -->

# [面试题 04.10. 检查子树](https://leetcode.cn/problems/check-subtree-lcci)

[English Version](/lcci/04.10.Check%20SubTree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。</p>

<p>如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：t1 = [1, 2, 3], t2 = [2]
<strong> 输出</strong>：true
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：t1 = [1, null, 2, 4], t2 = [3, 2]
<strong> 输出</strong>：false
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li>树的节点数目范围为[0, 20000]。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们首先判断 $t_2$ 是否为空，如果为空，那么 $t_2$ 一定是 $t_1$ 的子树，返回 `true`。

否则，判断 $t_1$ 是否为空，如果为空，那么 $t_2$ 一定不是 $t_1$ 的子树，返回 `false`。

接着，我们判断 $t_1$ 和 $t_2$ 是否相等，如果相等，那么 $t_2$ 是 $t_1$ 的子树，返回 `true`。否则，我们递归判断 $t_1$ 的左子树和 $t_2$ 是否相等，以及 $t_1$ 的右子树和 $t_2$ 是否相等，只要有一个为 `true`，那么 $t_2$ 就是 $t_1$ 的子树，返回 `true`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为 $t_1$ 的节点数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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
    fn dfs(t1: &Option<Rc<RefCell<TreeNode>>>, t2: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        match (t1, t2) {
            (Some(node1), Some(node2)) => {
                let n1 = node1.borrow();
                let n2 = node2.borrow();
                n1.val == n2.val
                    && Solution::dfs(&n1.left, &n2.left)
                    && Solution::dfs(&n1.right, &n2.right)
            }
            (None, Some(_)) => false,
            (Some(_), None) => false,
            _ => true, // Both are None
        }
    }

    pub fn check_sub_tree(
        t1: Option<Rc<RefCell<TreeNode>>>,
        t2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        match (t1, t2) {
            (Some(node1), Some(node2)) => {
                let n1 = node1.borrow();
                let n2 = node2.borrow();
                Solution::dfs(&Some(Rc::clone(&node1)), &Some(Rc::clone(&node2)))
                    || Solution::check_sub_tree(n1.left.clone(), Some(Rc::clone(&node2)))
                    || Solution::check_sub_tree(n1.right.clone(), Some(Rc::clone(&node2)))
            }
            (Some(_), None) => true,
            (None, Some(_)) => false,
            _ => true, // Both are None or t1 is None
        }
    }
}
```

#### Swift

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
