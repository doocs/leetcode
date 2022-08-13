# [面试题 04.05. 合法二叉搜索树](https://leetcode.cn/problems/legal-binary-search-tree-lcci)

[English Version](/lcci/04.05.Legal%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>实现一个函数，检查一棵二叉树是否为二叉搜索树。</p><strong>示例 1:</strong><pre><strong>输入:</strong><br>    2<br>   / &#92<br>  1   3<br><strong>输出:</strong> true<br></pre><strong>示例 2:</strong><pre><strong>输入:</strong><br>    5<br>   / &#92<br>  1   4<br>     / &#92<br>    3   6<br><strong>输出:</strong> false<br><strong>解释:</strong> 输入为: [5,1,4,null,null,3,6]。<br>     根节点的值为 5 ，但是其右子节点值为 4 。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

一棵合法的二叉搜索树，其中序遍历的结果应该升序排列。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

-   非递归中序遍历

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

-   利用上界下界判定

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
