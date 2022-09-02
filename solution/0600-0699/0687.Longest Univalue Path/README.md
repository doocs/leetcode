# [687. 最长同值路径](https://leetcode.cn/problems/longest-univalue-path)

[English Version](/solution/0600-0699/0687.Longest%20Univalue%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树的<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;，返回&nbsp;<em>最长的路径的长度</em> ，这个路径中的&nbsp;<em>每个节点具有相同值</em>&nbsp;。 这条路径可以经过也可以不经过根节点。</p>

<p><strong>两个节点之间的路径长度</strong>&nbsp;由它们之间的边数表示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0687.Longest%20Univalue%20Path/images/ex1.jpg" /></p>

<pre>
<strong>输入：</strong>root = [5,4,5,1,1,5]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0687.Longest%20Univalue%20Path/images/ex2.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1,4,5,4,4,5]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树的节点数的范围是<meta charset="UTF-8" />&nbsp;<code>[0, 10<sup>4</sup>]</code>&nbsp;</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li>树的深度将不超过 <code>1000</code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

相似题目：[543. 二叉树的直径](/solution/0500-0599/0543.Diameter%20of%20Binary%20Tree/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestUnivaluePath(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            left = left + 1 if root.left and root.left.val == root.val else 0
            right = right + 1 if root.right and root.right.val == root.val else 0
            nonlocal ans
            ans = max(ans, left + right)
            return max(left, right)

        ans = 0
        dfs(root)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    private int ans;

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        left = root.left != null && root.left.val == root.val ? left + 1 : 0;
        right = root.right != null && root.right.val == root.val ? right + 1 : 0;
        ans = Math.max(ans, left + right);
        return Math.max(left, right);
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
    int ans;

    int longestUnivaluePath(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        int left = dfs(root->left), right = dfs(root->right);
        left = root->left && root->left->val == root->val ? left + 1 : 0;
        right = root->right && root->right->val == root->val ? right + 1 : 0;
        ans = max(ans, left + right);
        return max(left, right);
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
func longestUnivaluePath(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		left, right := dfs(root.Left), dfs(root.Right)
		if root.Left != nil && root.Left.Val == root.Val {
			left++
		} else {
			left = 0
		}
		if root.Right != nil && root.Right.Val == root.Val {
			right++
		} else {
			right = 0
		}
		ans = max(ans, left+right)
		return max(left, right)
	}
	dfs(root)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
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

#define max(a,b) (((a) > (b)) ? (a) : (b))

int dfs(struct TreeNode *root, int target, int *res) {
    if (!root) {
        return 0;
    }
    int left = dfs(root->left, root->val, res);
    int right = dfs(root->right, root->val, res);
    *res = max(*res, left + right);
    if (root->val == target) {
        return max(left, right) + 1;
    }
    return 0;
}

int longestUnivaluePath(struct TreeNode *root) {
    if (!root) {
        return 0;
    }
    int res = 0;
    dfs(root, root->val, &res);
    return res;
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
 * @return {number}
 */
var longestUnivaluePath = function (root) {
    let ans = 0;
    let dfs = function (root) {
        if (!root) {
            return 0;
        }
        let left = dfs(root.left),
            right = dfs(root.right);
        left = root.left?.val == root.val ? left + 1 : 0;
        right = root.right?.val == root.val ? right + 1 : 0;
        ans = Math.max(ans, left + right);
        return Math.max(left, right);
    };
    dfs(root);
    return ans;
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

function longestUnivaluePath(root: TreeNode | null): number {
    if (root == null) {
        return 0;
    }

    let res = 0;
    const dfs = (root: TreeNode | null, target: number) => {
        if (root == null) {
            return 0;
        }

        const { val, left, right } = root;

        let l = dfs(left, val);
        let r = dfs(right, val);
        res = Math.max(res, l + r);
        if (val === target) {
            return Math.max(l, r) + 1;
        }
        return 0;
    };
    dfs(root, root.val);
    return res;
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, target: i32, res: &mut i32) -> i32 {
        if root.is_none() {
            return 0;
        }

        let root = root.as_ref().unwrap().as_ref().borrow();
        let left = Self::dfs(&root.left, root.val, res);
        let right = Self::dfs(&root.right, root.val, res);
        *res = (*res).max(left + right);
        if root.val == target {
            return left.max(right) + 1;
        }
        0
    }

    pub fn longest_univalue_path(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if root.is_none() {
            return 0;
        }

        let mut res = 0;
        Self::dfs(
            &root,
            root.as_ref().unwrap().as_ref().borrow().val,
            &mut res,
        );
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
