# [112. Path Sum](https://leetcode.com/problems/path-sum)

[中文文档](/solution/0100-0199/0112.Path%20Sum/README.md)

## Description

<p>Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.</p>

<p><strong>Note:</strong>&nbsp;A leaf is a node with no children.</p>

<p><strong>Example:</strong></p>

<p>Given the below binary tree and <code>sum = 22</code>,</p>

<pre>

      <strong>5</strong>

     <strong>/</strong> \

    <strong>4</strong>   8

   <strong>/</strong>   / \

  <strong>11</strong>  13  4

 /  <strong>\</strong>      \

7    <strong>2</strong>      1

</pre>

<p>return true, as there exist a root-to-leaf path <code>5-&gt;4-&gt;11-&gt;2</code> which sum is 22.</p>

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
    def hasPathSum(self, root: TreeNode, sum: int) -> bool:
        if root is None:
            return False
        if root.left is None and root.right is None:
            return root.val == sum
        return self.hasPathSum(root.left, sum - root.val) or self.hasPathSum(root.right, sum - root.val)
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
