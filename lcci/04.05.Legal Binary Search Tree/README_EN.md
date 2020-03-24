# [04.05. Legal Binary Search Tree](https://leetcode-cn.com/problems/legal-binary-search-tree-lcci)

## Description
<p>Implement a function to check if a binary tree is a binary search tree.</p>



<p><strong>Example&nbsp;1:</strong></p>



<pre>

<strong>Input:</strong>

    2

   / \

  1   3

<strong>Output:</strong> true

</pre>



<p><strong>Example&nbsp;2:</strong></p>



<pre>

<strong>Input:</strong>

    5

   / \

  1   4

&nbsp;    / \

&nbsp;   3   6

<strong>Output:</strong> false

<strong>Explanation:</strong> Input: [5,1,4,null,null,3,6].

&nbsp;    the value of root node is 5, but its right child has value 4.</pre>




## Solutions


### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    res, t = True, None
    def isValidBST(self, root: TreeNode) -> bool:
        self.isValid(root)
        return self.res

    def isValid(self, root):
        if not root:
            return
        self.isValid(root.left)
        if self.t is None or self.t < root.val:
            self.t = root.val
        else:
            self.res = False
            return
        self.isValid(root.right)
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
    private boolean res = true;
    private Integer t = null;
    public boolean isValidBST(TreeNode root) {
        isValid(root);
        return res;
    }

    private void isValid(TreeNode root) {
        if (root == null) {
            return;
        }
        isValid(root.left);
        if (t == null || t < root.val) {
            t = root.val;
        } else {
            res = false;
            return;
        }
        isValid(root.right);
    }
}
```

### ...
```

```
