# [530. Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst)

[中文文档](/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/README.md)

## Description

<p>Given a binary search tree with non-negative values, find the minimum <a href="https://en.wikipedia.org/wiki/Absolute_difference">absolute difference</a> between values of any two nodes.</p>

<p><b>Example:</b></p>

<pre>
<b>Input:</b>

   1
    \
     3
    /
   2

<b>Output:</b>
1

<b>Explanation:</b>
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ul>
	<li>There are at least two nodes in this BST.</li>
	<li>This question is the same as 783:&nbsp;<a href="https://leetcode.com/problems/minimum-distance-between-bst-nodes/">https://leetcode.com/problems/minimum-distance-between-bst-nodes/</a></li>
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
    def getMinimumDifference(self, root: TreeNode) -> int:
        def inorder(root):
            if not root:
                return
            inorder(root.left)
            if self.pre is not None:
                self.min_diff = min(self.min_diff, abs(root.val - self.pre))
            self.pre = root.val
            inorder(root.right)

        self.pre = None
        self.min_diff = 10 ** 5
        inorder(root)
        return self.min_diff
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

    private int minDiff = Integer.MAX_VALUE;
    private Integer pre;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (pre != null) minDiff = Math.min(minDiff, Math.abs(root.val - pre));
        pre = root.val;
        inorder(root.right);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
