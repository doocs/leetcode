# [面试题 04.12. 求和路径](https://leetcode-cn.com/problems/paths-with-sum-lcci)

[English Version](/lcci/04.12.Paths%20with%20Sum/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。</p>

<p><strong>示例:</strong><br>
给定如下二叉树，以及目标和&nbsp;<code>sum = 22</code>，</p>

<pre>              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
</pre>

<p>返回:</p>

<pre>3
<strong>解释：</strong>和为 22&nbsp;的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]</pre>

<p>提示：</p>

<ul>
	<li><code>节点总数 &lt;= 10000</code></li>
</ul>


## 解法
<!-- 这里可写通用的实现逻辑 -->
DFS深度优先搜索

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->
采用递归的思想，每递归到某个节点时：
- 若root.val-sum == 0，结果加1
- 考虑将此节点纳入或不纳入路径两种情况

特殊情况：若此节点的父节点在路径中，此节点必纳入路径（路径不能断）

```python
class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> int:
        def dfs(root, sum, flag):
            nonlocal ans
            if not root:
                return 0
            if sum-root.val == 0:
                ans += 1
            if flag == 0:
                dfs(root.left, sum, 0)
                dfs(root.right, sum, 0)
            dfs(root.left, sum-root.val, 1)
            dfs(root.right, sum-root.val, 1)
        
        if not root:
            return 0
        ans = 0
        dfs(root, sum, 0)
        return ans
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->
使用到2个递归过程：

- BFS:（traverse）遍历每个树节点；
- DFS: 从每个树节点出发，节点求和，看是否能满足sum。

需要注意，节点值有正有负，需要穷尽所有的可能路径。

```java
class Solution {
    int ans = 0;
    public int pathSum(TreeNode root, int sum) {
        traverse(root, sum);
        return ans;
    }

    void traverse(TreeNode root, int sum) {
        if (root == null) return;
        ans += dfs(root, sum, 0);
        traverse(root.left,  sum);
        traverse(root.right, sum);
    }

    // check if sum of path is sum.
    int dfs(TreeNode root, int sum, int cur) {
        if (root == null) return 0;
        cur += root.val;
        int res = 0;
        if (cur == sum) res++;
        res += dfs(root.left,  sum, cur);
        res += dfs(root.right, sum, cur);
        return res;
    }
}
```

### ...
```

```
