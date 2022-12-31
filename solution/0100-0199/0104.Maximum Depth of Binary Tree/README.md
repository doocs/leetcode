# [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree)

[English Version](/solution/0100-0199/0104.Maximum%20Depth%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，找出其最大深度。</p>

<p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>

<p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>

<p><strong>示例：</strong><br>
给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</p>

<pre>    3
   / \
  9  20
    /  \
   15   7</pre>

<p>返回它的最大深度&nbsp;3 。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

递归遍历左右子树，求左右子树的最大深度，然后取最大值加 $1$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是二叉树的节点数。每个节点在递归中只被遍历一次。

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
    def maxDepth(self, root: TreeNode) -> int:
        if root is None:
            return 0
        l, r = self.maxDepth(root.left), self.maxDepth(root.right)
        return 1 + max(l, r)
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
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return 1 + Math.max(l, r);
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
    int maxDepth(TreeNode* root) {
        if (!root) return 0;
        int l = maxDepth(root->left), r = maxDepth(root->right);
        return 1 + max(l, r);
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
func maxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	l, r := maxDepth(root.Left), maxDepth(root.Right)
	return 1 + max(l, r)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
var maxDepth = function (root) {
    if (!root) return 0;
    const l = maxDepth(root.left);
    const r = maxDepth(root.right);
    return 1 + Math.max(l, r);
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

function maxDepth(root: TreeNode | null): number {
    if (root == null) {
        return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if root.is_none() {
            return 0;
        }
        let node = root.as_ref().unwrap().borrow();
        1 + Self::dfs(&node.left).max(Self::dfs(&node.right))
    }

    pub fn max_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::dfs(&root)
    }
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

#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxDepth(struct TreeNode *root) {
    if (!root) {
        return 0;
    }
    int left = maxDepth(root->left);
    int right = maxDepth(root->right);
    return 1 + max(left, right);
}
```

### **...**

```

```

<!-- tabs:end -->
