# [112. 路径总和](https://leetcode-cn.com/problems/path-sum)

[English Version](/solution/0100-0199/0112.Path%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你二叉树的根节点 <code>root</code> 和一个表示目标和的整数 <code>targetSum</code> ，判断该树中是否存在 <strong>根节点到叶子节点</strong> 的路径，这条路径上所有节点值相加等于目标和 <code>targetSum</code> 。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0112.Path%20Sum/images/pathsum1.jpg" style="width: 500px; height: 356px;" />
<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0112.Path%20Sum/images/pathsum2.jpg" />
<pre>
<strong>输入：</strong>root = [1,2,3], targetSum = 5
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2], targetSum = 0
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[0, 5000]</code> 内</li>
	<li><code>-1000 <= Node.val <= 1000</code></li>
	<li><code>-1000 <= targetSum <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

递归求解，递归地询问它的子节点是否能满足条件即可。

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
    def hasPathSum(self, root: TreeNode, sum: int) -> bool:
        def dfs(root, sum):
            if root is None:
                return False
            if root.val == sum and root.left is None and root.right is None:
                return True
            return dfs(root.left, sum - root.val) or dfs(root.right, sum - root.val)
        return dfs(root, sum)
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
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.val == sum && root.left == null && root.right == null) return true;
        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
