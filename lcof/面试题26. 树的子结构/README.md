# [面试题 26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

## 题目描述

输入两棵二叉树 A 和 B，判断 B 是不是 A W 的子结构。(约定空树不是任意一个树的子结构)

B 是 A 的子结构， 即 A 中有出现和 B 相同的结构和节点值。

**例如:**

给定的树 A:

```
     3
    / \
   4   5
  / \
 1   2
```

给定的树 B：

```
   4 
  /
 1
```

返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

**示例 1：**

```
输入：A = [1,2,3], B = [3,1]
输出：false
```

**示例 2：**

```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```

**限制：**

- `0 <= 节点个数 <= 10000`

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
    def isSubStructure(self, A: TreeNode, B: TreeNode) -> bool:
        def sub(A, B):
            """判断从当前A节点开始，是否包含B"""
            if B is None:
                return True
            if A is None:
                return False
            return A.val == B.val and sub(A.left, B.left) and sub(A.right, B.right)
        if B is None or A is None:
            return False
        if A.val != B.val:
            return self.isSubStructure(A.left, B) or self.isSubStructure(A.right, B)
        return sub(A, B) or self.isSubStructure(A.left, B) or self.isSubStructure(A.right, B)
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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        if (A.val != B.val) return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        return sub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean sub(TreeNode A, TreeNode B) {
        // 判断从当前A节点开始，是否包含B
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && sub(A.left, B.left) && sub(A.right, B.right);
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
 * @param {TreeNode} A
 * @param {TreeNode} B
 * @return {boolean}
 */
var isSubStructure = function (A, B) {
    function sub(A, B) {
        if (!B) return true;
        if (!A) return false;
        return A.val == B.val && sub(A.left, B.left) && sub(A.right, B.right);
    }
    if (!B || !A) return false;
    if (A.val != B.val)
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    return sub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
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
func isSubStructure(A *TreeNode, B *TreeNode) bool {
    // 约定空树不是任意一个树的子结构
    if A == nil || B == nil {
        return false
    }
    return helper(A,B) || isSubStructure(A.Left,B) || isSubStructure(A.Right,B)
}

func helper(a *TreeNode, b *TreeNode) bool {
    if b ==  nil {
        return true
    }
    if a == nil {
        return false
    }
    return a.Val == b.Val && helper(a.Left, b.Left) && helper(a.Right, b.Right)
}
```

### **C++**

```cpp
class Solution {
public:
    bool isSubTree(TreeNode* a, TreeNode* b) {
        if (nullptr == b) {
            // 如果小树走到头，则表示ok了
            return true;
        }

        if (nullptr == a) {
            // 如果大树走到头，小树却没走到头，说明不对了
            return false;
        }

        if (a->val != b->val) {
            return false;
        }

        return isSubTree(a->left, b->left) && isSubTree(a->right, b->right);
    }

    bool isSubStructure(TreeNode* a, TreeNode* b) {
        bool ret = false;
        if (nullptr != a && nullptr != b) {
            // 题目约定，空树不属于任何一个数的子树
            if (a->val == b->val) {
                // 如果值相等，才进入判定
                ret = isSubTree(a, b);
            }

            if (false == ret) {
                ret = isSubStructure(a->left, b);
            }

            if (false == ret) {
                ret = isSubStructure(a->right, b);
            }
        }

        return ret;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
