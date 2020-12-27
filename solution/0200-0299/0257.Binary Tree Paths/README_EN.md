# [257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths)

[中文文档](/solution/0200-0299/0257.Binary%20Tree%20Paths/README.md)

## Description

<p>Given a binary tree, return all root-to-leaf paths.</p>

<p><strong>Note:</strong>&nbsp;A leaf is a node with no children.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong>



   1

 /   \

2     3

 \

  5



<strong>Output:</strong> [&quot;1-&gt;2-&gt;5&quot;, &quot;1-&gt;3&quot;]



<strong>Explanation:</strong> All root-to-leaf paths are: 1-&gt;2-&gt;5, 1-&gt;3

</pre>

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
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        def dfs(root):
            if root is None:
                return
            path.append(str(root.val))
            if root.left is None and root.right is None:
                res.append("->".join(path))
            dfs(root.left)
            dfs(root.right)
            path.pop()
        res = []
        path = []
        dfs(root)
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
    private List<String> res;
    private List<String> path;

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return Collections.emptyList();
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        path.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            res.add(String.join("->", path));
        }
        dfs(root.left);
        dfs(root.right);
        path.remove(path.size() - 1);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
