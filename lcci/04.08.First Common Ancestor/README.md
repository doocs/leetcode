# [面试题 04.08. 首个共同祖先](https://leetcode.cn/problems/first-common-ancestor-lcci)

[English Version](/lcci/04.08.First%20Common%20Ancestor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。</p><p>例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]</p><pre>    3<br>   / &#92<br>  5   1<br> / &#92 / &#92<br>6  2 0  8<br>  / &#92<br> 7   4<br></pre><strong>示例 1:</strong><pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1<br><strong>输入:</strong> 3<br><strong>解释:</strong> 节点 5 和节点 1 的最近公共祖先是节点 3。</pre><strong>示例 2:</strong><pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4<br><strong>输出:</strong> 5<br><strong>解释:</strong> 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。</pre><strong>说明:</strong><pre>所有节点的值都是唯一的。<br>p、q 为不同节点且均存在于给定的二叉树中。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
