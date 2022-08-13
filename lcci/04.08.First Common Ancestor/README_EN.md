# [04.08. First Common Ancestor](https://leetcode.cn/problems/first-common-ancestor-lcci)

[中文文档](/lcci/04.08.First%20Common%20Ancestor/README.md)

## Description

<p>Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not necessarily a binary search tree.</p>

<p>For example, Given the following tree: root = [3,5,1,6,2,0,8,null,null,7,4]</p>

<pre>

    3

   / \

  5   1

 / \ / \

6  2 0  8

  / \

 7   4

</pre>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1

<strong>Input:</strong> 3

<strong>Explanation:</strong> The first common ancestor of node 5 and node 1 is node 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4

<strong>Output:</strong> 5

<strong>Explanation:</strong> The first common ancestor of node 5 and node 4 is node 5.</pre>

<p><strong>Notes:</strong></p>

<ul>
	<li>All node values are pairwise distinct.</li>
	<li>p, q are different node and both can be found in the given tree.</li>
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
    def lowestCommonAncestor(
        self, root: TreeNode, p: TreeNode, q: TreeNode
    ) -> TreeNode:
        if root is None or root == p or root == q:
            return root
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        return right if left is None else (left if right is None else root)
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : (right == null ? left : root);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
