# [783. 二叉搜索树节点最小距离](https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes)

[English Version](/solution/0700-0799/0783.Minimum%20Distance%20Between%20BST%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉搜索树的根节点 <code>root</code> ，返回 <strong>树中任意两不同节点值之间的最小差值</strong> 。</p>

<p><strong>注意：</strong>本题与 530：<a href="https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/">https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/</a> 相同</p>

<p> </p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0783.Minimum%20Distance%20Between%20BST%20Nodes/images/bst1.jpg" style="width: 292px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [4,2,6,1,3]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0783.Minimum%20Distance%20Between%20BST%20Nodes/images/bst2.jpg" style="width: 282px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [1,0,48,null,null,12,49]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[2, 100]</code> 内</li>
	<li><code>0 <= Node.val <= 10<sup>5</sup></code></li>
	<li>差值是一个正数，其数值等于两值之差的绝对值</li>
</ul>
</div>
</div>

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
