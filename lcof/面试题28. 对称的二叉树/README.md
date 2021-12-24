# [面试题 28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)

## 题目描述

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树  `[1,2,2,3,4,4,3]` 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个  `[1,2,2,null,3,null,3]` 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```

**示例 1：**

```
输入：root = [1,2,2,3,4,4,3]
输出：true
```

**示例 2：**

```
输入：root = [1,2,2,null,3,null,3]
输出：false
```

**限制：**

- `0 <= 节点个数 <= 1000`

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
    def isSymmetric(self, root: TreeNode) -> bool:
        def is_symmetric(left, right):
            if left is None and right is None:
                return True
            if left is None or right is None or left.val != right.val:
                return False
            return is_symmetric(left.left, right.right) and is_symmetric(left.right, right.left)
        if root is None:
            return True
        return is_symmetric(root.left, root.right)

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
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
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
var isSymmetric = function (root) {
    function dfs(left, right) {
        if (!left && !right) return true;
        if (!left || !right || left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
    if (!root) return true;
    return dfs(root.left, root.right);
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
    if root == nil {
        return true
    }
    return isSymme(root.Left, root.Right)
}

func isSymme(left *TreeNode, right *TreeNode) bool {
    if left == nil && right == nil {
        return true
    }
    if left == nil || right == nil || left.Val != right.Val {
        return false
    }
    return isSymme(left.Left, right.Right) && isSymme(left.Right, right.Left)
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
    bool isSymmetric(TreeNode* a, TreeNode* b) {
        // 均为空，则直接返回true。有且仅有一个不为空，则返回false
        if (a == nullptr && b == nullptr) {
            return true;
        } else if (a == nullptr && b != nullptr) {
            return false;
        } else if (a != nullptr && b == nullptr) {
            return false;
        }

        // 判定值是否相等，和下面的节点是否对称
        return (a->val == b->val) && isSymmetric(a->left, b->right) && isSymmetric(a->right, b->left);
    }

    bool isSymmetric(TreeNode* root) {
        if (root == nullptr) {
            return true;
        }

        return isSymmetric(root->left, root->right);
    }
};

```

### **...**

```

```

<!-- tabs:end -->
