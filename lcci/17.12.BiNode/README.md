# [面试题 17.12. BiNode](https://leetcode-cn.com/problems/binode-lcci)

[English Version](/lcci/17.12.BiNode/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>二叉树数据结构<code>TreeNode</code>可用来表示单向链表（其中<code>left</code>置空，<code>right</code>为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。</p>

<p>返回转换后的单向链表的头节点。</p>

<p><strong>注意：</strong>本题相对原题稍作改动</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> [4,2,5,1,3,null,6,0]
<strong>输出：</strong> [0,null,1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>节点数量不会超过 100000。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

递归将左子树、右子树转换为左、右链表 left 和 right。然后将左链表 left 的最后一个结点的 right 指针指向 root，root 的 right 指针指向右链表 right，并将 root 的 left 指针值为空。

同 [897. 递增顺序查找树](/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/README.md)。

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
    def convertBiNode(self, root: TreeNode) -> TreeNode:
        if root is None:
            return None
        left = self.convertBiNode(root.left)
        right = self.convertBiNode(root.right)
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        TreeNode left = convertBiNode(root.left);
        TreeNode right = convertBiNode(root.right);
        if (left == null) {
            root.right = right;
            return root;
        }
        TreeNode res = left;
        while (left != null && left.right != null) {
            left = left.right;
        }
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
