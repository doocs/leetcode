# [04.04. Check Balance](https://leetcode.cn/problems/check-balance-lcci)

[中文文档](/lcci/04.04.Check%20Balance/README.md)

## Description

<p>Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.</p>

<p><br />

<strong>Example 1:</strong></p>

<pre>

Given tree [3,9,20,null,null,15,7]

    3

   / \

  9  20

    /  \

   15   7

return true.</pre>

<p><strong>Example 2:</strong></p>

<pre>

Given [1,2,2,3,3,null,null,4,4]

      1

     / \

    2   2

   / \

  3   3

 / \

4   4

return&nbsp;false.</pre>

<p>&nbsp;</p>

## Solutions

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
        if not root:
            return True
        l, r = self._height(root.left), self._height(root.right)
        return (
            abs(l - r) < 2
            and self.isBalanced(root.left)
            and self.isBalanced(root.right)
        )

    def _height(self, node):
        if not node:
            return 0
        return 1 + max(self._height(node.left), self._height(node.right))
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
        int l = height(root.left), r = height(root.right);
        return Math.abs(l - r) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
```

### **Go**

Bottom-up recursion

```go
func isBalanced(root *TreeNode) bool {
	return depth(root) >= 0
}

func depth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left := depth(root.Left)
	right := depth(root.Right)
	if left == -1 || right == -1 || abs(left-right) > 1 {
		return -1
	}
	return max(left, right) + 1
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
