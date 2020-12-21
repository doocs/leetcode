# [107. Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii)

[中文文档](/solution/0100-0199/0107.Binary%20Tree%20Level%20Order%20Traversal%20II/README.md)

## Description

<p>Given a binary tree, return the <i>bottom-up level order</i> traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).</p>

<p>

For example:<br />

Given binary tree <code>[3,9,20,null,null,15,7]</code>,<br />

<pre>

    3

   / \

  9  20

    /  \

   15   7

</pre>

</p>

<p>

return its bottom-up level order traversal as:<br />

<pre>

[

  [15,7],

  [9,20],

  [3]

]

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
    def levelOrderBottom(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = [root]
        res = []
        while q:
            size = len(q)
            t = []
            for _ in range(size):
                node = q.pop(0)
                t.append(node.val)
                if node.left is not None:
                    q.append(node.left)
                if node.right is not None:
                    q.append(node.right)
            res.append(t)
        return res[::-1]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> t = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(t);
        }
        Collections.reverse(res);
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
