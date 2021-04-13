# [783. 二叉搜索树结点最小距离](https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes)

[English Version](/solution/0700-0799/0783.Minimum%20Distance%20Between%20BST%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个二叉搜索树的根结点&nbsp;<code>root</code>，返回树中任意两节点的差的最小值。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> root = [4,2,6,1,3,null,null]
<strong>输出:</strong> 1
<strong>解释:</strong>
注意，root是树结点对象(TreeNode object)，而不是数组。

给定的树 [4,2,6,1,3,null,null] 可表示为下图:

          4
        /   \
      2      6
     / \    
    1   3  

最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。</pre>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ol>
	<li>二叉树的大小范围在 <code>2</code> 到&nbsp;<code>100</code>。</li>
	<li>二叉树总是有效的，每个节点的值都是整数，且不重复。</li>
	<li>本题与 530：<a href="https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/">https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/</a> 相同</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

中序遍历二叉搜索树，获取当前节点与上个节点的差值的最小值即可。

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
    def minDiffInBST(self, root: TreeNode) -> int:
        def inorder(root):
            if not root:
                return
            inorder(root.left)
            if self.pre is not None:
                self.min_diff = min(self.min_diff, abs(root.val - self.pre))
            self.pre = root.val
            inorder(root.right)

        self.pre = None
        self.min_diff = 10 ** 5
        inorder(root)
        return self.min_diff
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

    private int minDiff = Integer.MAX_VALUE;
    private Integer pre;

    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (pre != null) minDiff = Math.min(minDiff, Math.abs(root.val - pre));
        pre = root.val;
        inorder(root.right);
    }
}
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var res int
var preNode *TreeNode
func minDiffInBST(root *TreeNode) int {
    res = int(^uint(0) >> 1)
    preNode = nil
    helper(root)
    return res
}

func helper(root *TreeNode)  {
    if root == nil {
        return
    }
    helper(root.Left)
    if preNode != nil {
        res = getMinInt(res, root.Val - preNode.Val)
    }
    preNode = root
    helper(root.Right)
}

func getMinInt(a,b int) int {
    if a < b {
        return a
    }
    return b
}
```

<!-- tabs:end -->
