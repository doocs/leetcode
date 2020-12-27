# [17.12. BiNode](https://leetcode-cn.com/problems/binode-lcci)

[中文文档](/lcci/17.12.BiNode/README.md)

## Description

<p>The data structure&nbsp;<code>TreeNode</code>&nbsp;is used for binary tree, but it can also used to represent a single linked list (where left is null, and right is the next node in the list). Implement a method to convert a binary search tree (implemented with <code>TreeNode</code>) into a single&nbsp;linked list. The values should be kept in order and the operation should be performed in place (that is, on the original data structure).</p>

<p>Return the head node of the linked list after converting.</p>

<p><b>Note:&nbsp;</b>This problem is slightly different from the original one in the book.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong> [4,2,5,1,3,null,6,0]

<strong>Output: </strong> [0,null,1,null,2,null,3,null,4,null,5,null,6]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li>The number of nodes will not exceed&nbsp;100000.</li>
</ul>

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
    def convertBiNode(self, root: TreeNode) -> TreeNode:
        if root is None:
            return None
        left = self.convertBiNode(root.left)
        right = self.convertBiNode(root.right)
        if left is None:
            root.right = right
            return root
        res = left
        while left and left.right:
            left = left.right
        left.right = root
        root.right = right
        root.left = None
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
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        TreeNode left = convertBiNode(root.left);
        TreeNode right = convertBiNode(root.right);
        if (left == null) {
            root.right = right;
            return root;
        }
        TreeNode res = left;
        while (left != null && left.right != null) {
            left = left.right;
        }
        left.right = root;
        root.right = right;
        root.left = null;
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
