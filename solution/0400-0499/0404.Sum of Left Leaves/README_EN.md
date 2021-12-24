# [404. Sum of Left Leaves](https://leetcode.com/problems/sum-of-left-leaves)

[中文文档](/solution/0400-0499/0404.Sum%20of%20Left%20Leaves/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return the sum of all left leaves.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0404.Sum%20of%20Left%20Leaves/images/leftsum-tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> 24
<strong>Explanation:</strong> There are two left leaves in the binary tree, with values 9 and 15 respectively.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
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
