# [99. Recover Binary Search Tree](https://leetcode.com/problems/recover-binary-search-tree)

[中文文档](/solution/0000-0099/0099.Recover%20Binary%20Search%20Tree/README.md)

## Description

<p>You are given the <code>root</code> of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. <em>Recover the tree without changing its structure</em>.</p>

<p><strong>Follow up:</strong> A solution using <code>O(n)</code> space is pretty straight forward. Could you devise a constant space solution?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0099.Recover%20Binary%20Search%20Tree/images/recover1.jpg" style="width: 422px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,3,null,null,2]
<strong>Output:</strong> [3,1,null,null,2]
<strong>Explanation:</strong> 3 cannot be a left child of 1 because 3 &gt; 1. Swapping 1 and 3 makes the BST valid.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0099.Recover%20Binary%20Search%20Tree/images/recover2.jpg" style="width: 581px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,1,4,null,null,2]
<strong>Output:</strong> [2,1,4,null,null,3]
<strong>Explanation:</strong> 2 cannot be in the right subtree of 3 because 2 &lt; 3. Swapping 2 and 3 makes the BST valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 1000]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>


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
    first = None
    second = None
    prev = None

    def recoverTree(self, root: TreeNode) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        def dfs(root):
            if root:
                dfs(root.left)
                if self.prev and root.val < self.prev.val:
                    if not self.first:
                        self.first = self.prev
                    self.second = root
                self.prev = root
                dfs(root.right)
        dfs(root)
        self.first.val, self.second.val = self.second.val, self.first.val
```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
