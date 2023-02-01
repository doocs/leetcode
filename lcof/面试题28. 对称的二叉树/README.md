# [面试题 28. 对称的二叉树](https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/)

## 题目描述

<p>请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。</p>

<p>例如，二叉树&nbsp;[1,2,2,3,4,4,3] 是对称的。</p>

<p><code>&nbsp; &nbsp; 1<br>
&nbsp; &nbsp;/ \<br>
&nbsp; 2 &nbsp; 2<br>
&nbsp;/ \ / \<br>
3 &nbsp;4 4 &nbsp;3</code><br>
但是下面这个&nbsp;[1,2,2,null,3,null,3] 则不是镜像对称的:</p>

<p><code>&nbsp; &nbsp; 1<br>
&nbsp; &nbsp;/ \<br>
&nbsp; 2 &nbsp; 2<br>
&nbsp; &nbsp;\ &nbsp; \<br>
&nbsp; &nbsp;3 &nbsp; &nbsp;3</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>root = [1,2,2,3,4,4,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root = [1,2,2,null,3,null,3]
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 1000</code></p>

<p>注意：本题与主站 101 题相同：<a href="https://leetcode.cn/problems/symmetric-tree/">https://leetcode.cn/problems/symmetric-tree/</a></p>

## 解法

**方法一：递归**

我们设计一个递归函数 `dfs`，它接收两个参数 `a` 和 `b`，分别代表两棵树的根节点。我们可以对 `a` 和 `b` 进行如下判断：

-   如果 `a` 和 `b` 都为空，说明两棵树都遍历完了，返回 `true`；
-   如果 `a` 和 `b` 中有且只有一个为空，说明两棵树的结构不同，返回 `false`；
-   如果 `a` 和 `b` 的值不相等，说明两棵树的结构不同，返回 `false`；
-   如果 `a` 和 `b` 的值相等，那么我们分别递归地判断 `a` 的左子树和 `b` 的右子树，以及 `a` 的右子树和 `b` 的左子树是否对称。

最后，我们返回 `dfs(root, root)`，即判断 `root` 的左子树和右子树是否对称。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    def isSymmetric(self, root: TreeNode) -> bool:
        def dfs(a, b):
            if a is None and b is None:
                return True
            if a is None or b is None or a.val != b.val:
                return False
            return dfs(a.left, b.right) and dfs(a.right, b.left)

        return dfs(root, root)
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
    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
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
    bool isSymmetric(TreeNode* root) {
        function<bool(TreeNode*, TreeNode*)> dfs = [&](TreeNode* a, TreeNode* b) -> bool {
            if (!a && !b) {
                return true;
            }
            if (!a || !b || a->val != b->val) {
                return false;
            }
            return dfs(a->left, b->right) && dfs(a->right, b->left);
        };
        return dfs(root, root);
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
func isSymmetric(root *TreeNode) bool {
	var dfs func(a, b *TreeNode) bool
	dfs = func(a, b *TreeNode) bool {
		if a == nil && b == nil {
			return true
		}
		if a == nil || b == nil || a.Val != b.Val {
			return false
		}
		return dfs(a.Left, b.Right) && dfs(a.Right, b.Left)
	}
	return dfs(root, root)
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
var isSymmetric = function (root) {
    const dfs = (a, b) => {
        if (!a && !b) {
            return true;
        }
        if (!a || !b || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    };
    return dfs(root, root);
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

function isSymmetric(root: TreeNode | null): boolean {
    const dfs = (a: TreeNode | null, b: TreeNode | null): boolean => {
        if (!a && !b) {
            return true;
        }
        if (!a || !b || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    };
    return dfs(root, root);
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
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    fn dfs(a: &Option<Rc<RefCell<TreeNode>>>, b: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        if a.is_none() && b.is_none() {
            return true;
        }
        if a.is_none() || b.is_none() {
            return false;
        }
        let l = a.as_ref().unwrap().borrow();
        let r = b.as_ref().unwrap().borrow();
        l.val == r.val && Self::dfs(&l.left, &r.right) && Self::dfs(&l.right, &r.left)
    }

    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::dfs(&root, &root)
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
    public bool IsSymmetric(TreeNode root) {
        return dfs(root, root);
    }

    private bool dfs(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
