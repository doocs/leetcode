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

**方法一：递归**

我们设计一个递归函数 $dfs(root)$，函数返回值为 $root$ 节点的深度，如果 $root$ 节点不平衡，返回值为 $-1$。

函数 $dfs(root)$ 的递归过程如下：

-   如果 $root$ 为空，返回 $0$。
-   递归计算左右子树的深度，记为 $l$ 和 $r$。
-   如果 $l$ 或 $r$ 为 $-1$，或者 $l$ 和 $r$ 的差的绝对值大于 $1$，返回 $-1$。
-   否则，返回 $max(l, r) + 1$。

如果 $dfs(root)$ 返回值不为 $-1$，则说明 $root$ 节点平衡，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

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
        def dfs(root):
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            if l == -1 or r == -1 or abs(l - r) > 1:
                return -1
            return 1 + max(l, r)

        return dfs(root) != -1
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        def dfs(root):
            if root is None:
                return (True, 0)
            l, ld = dfs(root.left)
            r, rd = dfs(root.right)
            d = max(ld, rd) + 1
            if l and r and abs(ld - rd) <= 1:
                return (True, d)
            return (False, d)

        return dfs(root)[0]
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
        return dfs(root) != -1;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isBalanced(TreeNode* root) {
        function<int(TreeNode*)> dfs = [&](TreeNode* root) -> int {
            if (!root) {
                return 0;
            }
            int l = dfs(root->left);
            int r = dfs(root->right);
            if (l == -1 || r == -1 || abs(l - r) > 1) {
                return -1;
            }
            return 1 + max(l, r);
        };
        return dfs(root) != -1;
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
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l == -1 || r == -1 || abs(l-r) > 1 {
			return -1
		}
		return 1 + max(l, r)
	}
	return dfs(root) != -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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
    const dfs = root => {
        if (!root) {
            return 0;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (l === -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
    };
    return dfs(root) !== -1;
};
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
        return dfs(root) != -1;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (l == -1 || r == -1 || Math.Abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.Max(l, r);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
