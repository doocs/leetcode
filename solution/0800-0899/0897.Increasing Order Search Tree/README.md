# [897. 递增顺序查找树](https://leetcode-cn.com/problems/increasing-order-search-tree)

[English Version](/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个树，请你 <strong>按中序遍历</strong> 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。</p>

<p>&nbsp;</p>

<p><strong>示例 ：</strong></p>

<pre><strong>输入：</strong>[5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
&nbsp;/        / \ 
1        7   9

<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
&nbsp; \
&nbsp;  2
&nbsp;   \
&nbsp;    3
&nbsp;     \
&nbsp;      4
&nbsp;       \
&nbsp;        5
&nbsp;         \
&nbsp;          6
&nbsp;           \
&nbsp;            7
&nbsp;             \
&nbsp;              8
&nbsp;               \
                 9  </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>给定树中的结点数介于 <code>1</code> 和&nbsp;<code>100</code> 之间。</li>
	<li>每个结点都有一个从 <code>0</code> 到 <code>1000</code> 范围内的唯一整数值。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

递归将左子树、右子树转换为左、右链表 left 和 right。然后将左链表 left 的最后一个结点的 right 指针指向 root，root 的 right 指针指向右链表 right，并将 root 的 left 指针值为空。

同[面试题 17.12. BiNode](/lcci/17.12.BiNode/README.md)。

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
    def increasingBST(self, root: TreeNode) -> TreeNode:
        if root is None:
            return None
        left = self.increasingBST(root.left)
        right = self.increasingBST(root.right)
        if left is None:
            root.right = right
            return root
        res = left
        while left and left.right:
            left = left.right
        left.right = root
        root.right = right
        root.left = None
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
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        TreeNode left = increasingBST(root.left);
        TreeNode right = increasingBST(root.right);
        if (left == null) {
            root.right = right;
            return root;
        }
        TreeNode res = left;
        while (left != null && left.right != null) left = left.right;
        left.right = root;
        root.right = right;
        root.left = null;
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
