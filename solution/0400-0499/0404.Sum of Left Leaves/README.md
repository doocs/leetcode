# [404. 左叶子之和](https://leetcode.cn/problems/sum-of-left-leaves)

[English Version](/solution/0400-0499/0404.Sum%20of%20Left%20Leaves/README_EN.md)

<!-- tags:树,深度优先搜索,广度优先搜索,二叉树 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定二叉树的根节点&nbsp;<code>root</code>&nbsp;，返回所有左叶子之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0404.Sum%20of%20Left%20Leaves/images/leftsum-tree.jpg" /></p>

<pre>
<strong>输入:</strong> root = [3,9,20,null,null,15,7] 
<strong>输出:</strong> 24 
<strong>解释:</strong> 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> root = [1]
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数在&nbsp;<code>[1, 1000]</code>&nbsp;范围内</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

## 解法

### 方法一：递归

我们首先判断 `root` 是否为空，如果为空则返回 $0$。

否则，我们递归调用 `sumOfLeftLeaves` 函数计算 `root` 的右子树中所有左叶子之和，并将结果赋给答案变量 $ans$。然后我们判断 `root` 的左子节点是否存在，如果存在，我们判断其是否为叶子节点，如果是叶子节点，则将其值加到答案变量 $ans$ 中，否则我们递归调用 `sumOfLeftLeaves` 函数计算 `root` 的左子树中所有左叶子之和，并将结果加到答案变量 $ans$ 中。

最后返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumOfLeftLeaves(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        ans = self.sumOfLeftLeaves(root.right)
        if root.left:
            if root.left.left == root.left.right:
                ans += root.left.val
            else:
                ans += self.sumOfLeftLeaves(root.left)
        return ans
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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = sumOfLeftLeaves(root.right);
        if (root.left != null) {
            if (root.left.left == root.left.right) {
                ans += root.left.val;
            } else {
                ans += sumOfLeftLeaves(root.left);
            }
        }
        return ans;
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
    int sumOfLeftLeaves(TreeNode* root) {
        if (!root) {
            return 0;
        }
        int ans = sumOfLeftLeaves(root->right);
        if (root->left) {
            if (!root->left->left && !root->left->right) {
                ans += root->left->val;
            } else {
                ans += sumOfLeftLeaves(root->left);
            }
        }
        return ans;
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
func sumOfLeftLeaves(root *TreeNode) int {
	if root == nil {
		return 0
	}
	ans := sumOfLeftLeaves(root.Right)
	if root.Left != nil {
		if root.Left.Left == root.Left.Right {
			ans += root.Left.Val
		} else {
			ans += sumOfLeftLeaves(root.Left)
		}
	}
	return ans
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

function sumOfLeftLeaves(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    let ans = sumOfLeftLeaves(root.right);
    if (root.left) {
        if (root.left.left === root.left.right) {
            ans += root.left.val;
        } else {
            ans += sumOfLeftLeaves(root.left);
        }
    }
    return ans;
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, is_left: bool) -> i32 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        let left = &node.left;
        let right = &node.right;
        if left.is_none() && right.is_none() {
            if is_left {
                return node.val;
            }
            return 0;
        }
        Self::dfs(left, true) + Self::dfs(right, false)
    }

    pub fn sum_of_left_leaves(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::dfs(&root, false)
    }
}
```

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int sumOfLeftLeaves(struct TreeNode* root) {
    if (!root) {
        return 0;
    }
    int ans = sumOfLeftLeaves(root->right);
    if (root->left) {
        if (!root->left->left && !root->left->right) {
            ans += root->left->val;
        } else {
            ans += sumOfLeftLeaves(root->left);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：栈

我们也可以将方法一的递归改为迭代，使用栈来模拟递归的过程。

与方法一类似，我们首先判断 `root` 是否为空，如果为空则返回 $0$。

否则，我们初始化答案变量 $ans$ 为 $0$，然后初始化栈 $stk$，将 `root` 加入栈中。

当栈不为空时，我们弹出栈顶元素 `root`，如果 `root` 的左子节点存在，我们判断其是否为叶子节点，如果是叶子节点，则将其值加到答案变量 $ans$ 中，否则我们将其左子节点加入栈中。然后我们判断 `root` 的右子节点是否存在，如果存在，我们将其加入栈中。

最后返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumOfLeftLeaves(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        ans = 0
        stk = [root]
        while stk:
            root = stk.pop()
            if root.left:
                if root.left.left == root.left.right:
                    ans += root.left.val
                else:
                    stk.append(root.left)
            if root.right:
                stk.append(root.right)
        return ans
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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stk = new ArrayDeque<>();
        stk.push(root);
        int ans = 0;
        while (!stk.isEmpty()) {
            root = stk.pop();
            if (root.left != null) {
                if (root.left.left == root.left.right) {
                    ans += root.left.val;
                } else {
                    stk.push(root.left);
                }
            }
            if (root.right != null) {
                stk.push(root.right);
            }
        }
        return ans;
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
    int sumOfLeftLeaves(TreeNode* root) {
        if (!root) {
            return 0;
        }
        int ans = 0;
        stack<TreeNode*> stk{{root}};
        while (!stk.empty()) {
            root = stk.top(), stk.pop();
            if (root->left) {
                if (!root->left->left && !root->left->right) {
                    ans += root->left->val;
                } else {
                    stk.push(root->left);
                }
            }
            if (root->right) {
                stk.push(root->right);
            }
        }
        return ans;
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
func sumOfLeftLeaves(root *TreeNode) (ans int) {
	if root == nil {
		return 0
	}
	stk := []*TreeNode{root}
	for len(stk) > 0 {
		root = stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if root.Left != nil {
			if root.Left.Left == root.Left.Right {
				ans += root.Left.Val
			} else {
				stk = append(stk, root.Left)
			}
		}
		if root.Right != nil {
			stk = append(stk, root.Right)
		}
	}
	return
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

function sumOfLeftLeaves(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    let ans = 0;
    const stk: TreeNode[] = [root];
    while (stk.length) {
        const { left, right } = stk.pop()!;
        if (left) {
            if (left.left === left.right) {
                ans += left.val;
            } else {
                stk.push(left);
            }
        }
        if (right) {
            stk.push(right);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
