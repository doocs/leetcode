# [897. 递增顺序搜索树](https://leetcode-cn.com/problems/increasing-order-search-tree)

[English Version](/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉搜索树，请你 <strong>按中序遍历</strong> 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/images/ex1.jpg" style="width: 600px; height: 350px;" />
<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/images/ex2.jpg" style="width: 300px; height: 114px;" />
<pre>
<strong>输入：</strong>root = [5,1,7]
<strong>输出：</strong>[1,null,5,null,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数的取值范围是 <code>[1, 100]</code></li>
	<li><code>0 <= Node.val <= 1000</code></li>
</ul>

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
