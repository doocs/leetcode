# [面试题26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

## 题目描述
输入两棵二叉树 A 和 B，判断 B 是不是 A W的子结构。(约定空树不是任意一个树的子结构)

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
### Python3
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSubStructure(self, A: TreeNode, B: TreeNode) -> bool:
        return self.sub(A, B) if B else False

    def sub(self, A: TreeNode, B: TreeNode) -> bool:
        if B is None:
            return True
        if A is None:
            return False
        if A.val == B.val:
            return self.same(A, B) or self.sub(A.left, B) or self.sub(A.right, B)
        return self.sub(A.left, B) or self.sub(A.right, B)

    def same(self, A: TreeNode, B: TreeNode) -> bool:
        if B is None:
            return True
        if A is None or A.val != B.val:
            return False
        return self.same(A.left, B.left) and self.same(A.right, B.right)
```

### Java
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
        return B == null ? false : sub(A, B);
    }

    private boolean sub(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            return isSame(A, B) || sub(A.left, B) || sub(A.right, B);
        }
        return sub(A.left, B) || sub(A.right, B);
        
    }

    private boolean isSame(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }
}
```

### ...
```

```
