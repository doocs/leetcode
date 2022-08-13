# [面试题 55 - II. 平衡二叉树](https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/)

## 题目描述

<p>输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<p>给定二叉树 <code>[3,9,20,null,null,15,7]</code></p>

<pre>
    3
   / \
  9  20
    /  \
   15   7</pre>

<p>返回 <code>true</code> 。<br />
<br />
<strong>示例 2:</strong></p>

<p>给定二叉树 <code>[1,2,2,3,3,null,null,4,4]</code></p>

<pre>
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
</pre>

<p>返回 <code>false</code> 。</p>

<p> </p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 <= 树的结点个数 <= 10000</code></li>
</ul>

<p>注意：本题与主站 110 题相同：<a href="https://leetcode.cn/problems/balanced-binary-tree/">https://leetcode.cn/problems/balanced-binary-tree/</a></p>

<p> </p>

## 解法

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
    def isBalanced(self, root: TreeNode) -> bool:
        def height(root):
            if root is None:
                return 0
            return 1 + max(height(root.left), height(root.right))

        if root is None:
            return True
        return (
            abs(height(root.left) - height(root.right)) <= 1
            and self.isBalanced(root.left)
            and self.isBalanced(root.right)
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return 1 + Math.max(depth(tree.left), depth(tree.right));
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isBalanced = function (root) {
    const depth = root => {
        if (!root) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    };

    if (!root) {
        return true;
    }
    return (
        Math.abs(depth(root.left) - depth(root.right)) <= 1 &&
        isBalanced(root.left) &&
        isBalanced(root.right)
    );
};
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
    bool isBalanced(TreeNode* root) {
        if (!root) {
            return true;
        }
        return abs(depth(root->left) - depth(root->right)) <= 1 && isBalanced(root->left) && isBalanced(root->right);
    }

private:
    int depth(TreeNode* root) {
        if (!root) {
            return 0;
        }
        return 1 + max(depth(root->left), depth(root->right));
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
func isBalanced(root *TreeNode) bool {
    if (root == nil) {
        return true
    }
    return math.Abs(float64(depth(root.Left)-depth(root.Right))) <= 1 && isBalanced(root.Left) && isBalanced(root.Right)
}

func depth(root *TreeNode) int {
    if (root == nil) {
        return 0
    }
    left, right := depth(root.Left), depth(root.Right)
    if (left > right) {
        return 1 + left
    }
    return 1 + right
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
        match root {
            None => 0,
            Some(node) => {
                let node = node.borrow();
                1 + Self::dfs(&node.left).max(Self::dfs(&node.right))
            }
        }
    }
    pub fn is_balanced(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        match root {
            None => true,
            Some(node) => {
                let mut node = node.borrow_mut();
                let a = 10;
                (Self::dfs(&node.left) - Self::dfs(&node.right)).abs() <= 1
                    && Self::is_balanced(node.left.take())
                    && Self::is_balanced(node.right.take())
            }
        }
    }
}
```

### **C#**

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public bool IsBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.Abs(Height(root.left) - Height(root.right)) <= 1 && IsBalanced(root.left) && IsBalanced(root.right);
    }

    int Height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.Max(Height(root.left), Height(root.right));
    }
}
```

### **...**

```

```

<!-- tabs:end -->
