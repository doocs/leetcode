# [1120. 子树的最大平均值](https://leetcode.cn/problems/maximum-average-subtree)

[English Version](/solution/1100-1199/1120.Maximum%20Average%20Subtree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点&nbsp;<code>root</code>，找出这棵树的 <strong>每一棵</strong> 子树的 <strong>平均值</strong> 中的 <strong>最大</strong> 值。</p>

<p>子树是树中的任意节点和它的所有后代构成的集合。</p>

<p>树的平均值是树中节点值的总和除以节点数。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1120.Maximum%20Average%20Subtree/images/1308_example_1.png" style="height: 123px; width: 132px;"></p>

<pre><strong>输入：</strong>[5,6,1]
<strong>输出：</strong>6.00000
<strong>解释： </strong>
以 value = 5 的节点作为子树的根节点，得到的平均值为 (5 + 6 + 1) / 3 = 4。
以 value = 6 的节点作为子树的根节点，得到的平均值为 6 / 1 = 6。
以 value = 1 的节点作为子树的根节点，得到的平均值为 1 / 1 = 1。
所以答案取最大值 6。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>树中的节点数介于&nbsp;<code>1</code> 到&nbsp;<code>5000</code>之间。</li>
	<li>每个节点的值介于&nbsp;<code>0</code> 到&nbsp;<code>100000</code>&nbsp;之间。</li>
	<li>如果结果与标准答案的误差不超过&nbsp;<code>10^-5</code>，那么该结果将被视为正确答案。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

后序遍历获取每个子树的结点个数以及结点和，求每个结点平均值的最大值。

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
    def maximumAverageSubtree(self, root: TreeNode) -> float:
        def dfs(root):
            if root is None:
                return 0, 0
            ls, ln = dfs(root.left)
            rs, rn = dfs(root.right)
            s = ls + root.val + rs
            n = ln + 1 + rn
            nonlocal ans
            ans = max(ans, s / n)
            return s, n

        ans = 0
        dfs(root)
        return ans
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
    private double ans;

    public double maximumAverageSubtree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int s = l[0] + root.val + r[0];
        int n = l[1] + 1 + r[1];
        ans = Math.max(ans, s * 1.0 / n);
        return new int[]{s, n};
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    double ans;

    double maximumAverageSubtree(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    pair<int, int> dfs(TreeNode* root) {
        if (!root) return {0, 0};
        auto l = dfs(root->left);
        auto r = dfs(root->right);
        int s = l.first + root->val + r.first;
        int n = l.second + 1 + r.second;
        ans = max(ans, s * 1.0 / n);
        return {s, n};
    }
};
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
func maximumAverageSubtree(root *TreeNode) float64 {
	var ans float64
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{0, 0}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		s := l[0] + root.Val + r[0]
		n := l[1] + 1 + r[1]
		ans = math.Max(ans, float64(s)/float64(n))
		return []int{s, n}
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
