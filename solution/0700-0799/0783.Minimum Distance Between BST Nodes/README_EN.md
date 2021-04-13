# [783. Minimum Distance Between BST Nodes](https://leetcode.com/problems/minimum-distance-between-bst-nodes)

[中文文档](/solution/0700-0799/0783.Minimum%20Distance%20Between%20BST%20Nodes/README.md)

## Description

<p>Given a Binary Search Tree (BST) with the root node <code>root</code>, return&nbsp;the minimum difference between the values of any two different nodes in the tree.</p>

<p><strong>Example :</strong></p>

<pre>
<strong>Input:</strong> root = [4,2,6,1,3,null,null]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \    
    1   3  

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>The size of the BST will be between 2 and&nbsp;<code>100</code>.</li>
	<li>The BST is always valid, each node&#39;s value is an integer, and each node&#39;s value is different.</li>
	<li>This question is the same as 530:&nbsp;<a href="https://leetcode.com/problems/minimum-absolute-difference-in-bst/">https://leetcode.com/problems/minimum-absolute-difference-in-bst/</a></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minDiffInBST(self, root: TreeNode) -> int:
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
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private int minDiff = Integer.MAX_VALUE;
    private Integer pre;

    public int minDiffInBST(TreeNode root) {
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
