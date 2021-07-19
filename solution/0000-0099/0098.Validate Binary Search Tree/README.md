# [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree)

[English Version](/solution/0000-0099/0098.Validate%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，判断其是否是一个有效的二叉搜索树。</p>

<p>假设一个二叉搜索树具有如下特征：</p>

<ul>
	<li>节点的左子树只包含<strong>小于</strong>当前节点的数。</li>
	<li>节点的右子树只包含<strong>大于</strong>当前节点的数。</li>
	<li>所有左子树和右子树自身必须也是二叉搜索树。</li>
</ul>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong>
    2
   / \
  1   3
<strong>输出:</strong> true
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:
</strong>    5
   / \
  1   4
&nbsp;    / \
&nbsp;   3   6
<strong>输出:</strong> false
<strong>解释:</strong> 输入为: [5,1,4,null,null,3,6]。
&nbsp;    根节点的值为 5 ，但是其右子节点值为 4 。
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

中序遍历，若是一个有效的二叉搜索树，那么遍历到的序列应该是单调递增的。所以只要比较判断遍历到的当前数是否 `>=` 上一个数即可。

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
    pre = None
    def isValidBST(self, root: TreeNode) -> bool:
        if not root:
            return True
        if not self.isValidBST(root.left):
            return False
        if self.pre is not None and self.pre >= root.val:
            return False
        self.pre = root.val
        if not self.isValidBST(root.right):
            return False
        return True
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
    private Integer pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre != null && pre >= root.val) return false;
        pre = root.val;
        if (!isValidBST(root.right)) return false;
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isValidBST = function (root) {
    let isValidBSTRec = function (root, min, max) {
        if (!root) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTRec(root.left, min, root.val) && isValidBSTRec(root.right, root.val, max);
    }
    return isValidBSTRec(root, -Infinity, Infinity);
};
```

### **...**

```

```

<!-- tabs:end -->
