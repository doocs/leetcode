# [513. 找树左下角的值](https://leetcode-cn.com/problems/find-bottom-left-tree-value)

[English Version](/solution/0500-0599/0513.Find%20Bottom%20Left%20Tree%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，在树的最后一行找到最左边的值。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>

    2
   / \
  1   3

<strong>输出:</strong>
1
</pre>

<p>&nbsp;</p>

<p><strong>示例 2: </strong></p>

<pre>
<strong>输入:</strong>

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

<strong>输出:</strong>
7
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong> 您可以假设树（即给定的根节点）不为 <strong>NULL</strong>。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“BFS 层次遍历”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findBottomLeftValue(self, root: TreeNode) -> int:
        res = 0
        q = collections.deque([root])
        while q:
            res = q[0].val
            n = len(q)
            for _ in range(n):
                node = q.popleft()
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int res = 0;
        while (!q.isEmpty()) {
            res = q.peek().val;
            for (int i = 0, n = q.size(); i < n; ++i) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
