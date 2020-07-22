# [面试题28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)

## 题目描述
请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:

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
        if root is None:
            return True
        return self.symmetric(root.left, root.right)
    
    def symmetric(self, node1, node2) -> bool:
        if node1 is None and node2 is None:
            return True
        if node1 is None or node2 is None or node1.val != node2.val:
            return False
        return self.symmetric(node1.left, node2.right) and self.symmetric(node1.right, node2.left)
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
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
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
var isSymmetric = function(root) {
    function dfs(a,b) {
        if(!a && !b) return true
        if(!a || !b) return false
        return a.val === b.val && dfs(a.left,b.right) && dfs(a.right,b.left)
    }
    if(!root) return true
    return dfs(root.left,root.right)
};
```

### **Go**

```go
func isSymmetric(root *TreeNode) bool {
    if root == nil {
        return true
    }
    return isSymme(root.Left, root.Right)
}

func isSymme(a *TreeNode, b *TreeNode) bool {
    if a == nil && b == nil {
        return true
    }
    if a == nil || b ==nil {
        return false
    }
    if a.Val != b.Val {
        return false
    }
    return isSymme(a.Left,b.Right) && isSymme(a.Right, b.Left)
}
```



### **...**

```

```

<!-- tabs:end -->