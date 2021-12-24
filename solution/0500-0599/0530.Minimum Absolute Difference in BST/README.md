# [530. 二叉搜索树的最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst)

[English Version](/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>

   1
    \
     3
    /
   2

<strong>输出：</strong>
1

<strong>解释：
</strong>最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中至少有 2 个节点。</li>
	<li>本题与 783 <a href="https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/">https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/</a> 相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

中序遍历二叉搜索树，获取当前节点与上个节点的差值的最小值即可。

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
    def getMinimumDifference(self, root: TreeNode) -> int:
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private int minDiff = Integer.MAX_VALUE;
    private Integer pre;

    public int getMinimumDifference(TreeNode root) {
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
var res int
var preNode *TreeNode
func getMinimumDifference(root *TreeNode) int {
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
