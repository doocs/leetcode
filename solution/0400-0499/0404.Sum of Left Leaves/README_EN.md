# [404. Sum of Left Leaves](https://leetcode.com/problems/sum-of-left-leaves)

[中文文档](/solution/0400-0499/0404.Sum%20of%20Left%20Leaves/README.md)

## Description

<p>Find the sum of all left leaves in a given binary tree.</p>

<p><b>Example:</b>

<pre>

    3

   / \

  9  20

    /  \

   15   7



There are two left leaves in the binary tree, with values <b>9</b> and <b>15</b> respectively. Return <b>24</b>.

</pre>

</p>

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
    def sumOfLeftLeaves(self, root: TreeNode) -> int:
        if root is None:
            return 0
        res = 0
        if root.left and root.left.left is None and root.left.right is None:
            res += root.left.val
        res += self.sumOfLeftLeaves(root.left)
        res += self.sumOfLeftLeaves(root.right)
        return res
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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }
        res += sumOfLeftLeaves(root.left);
        res += sumOfLeftLeaves(root.right);
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
