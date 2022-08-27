# [1372. 二叉树中的最长交错路径](https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree)

[English Version](/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵以&nbsp;<code>root</code>&nbsp;为根的二叉树，二叉树中的交错路径定义如下：</p>

<ul>
	<li>选择二叉树中 <strong>任意</strong>&nbsp;节点和一个方向（左或者右）。</li>
	<li>如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。</li>
	<li>改变前进方向：左变右或者右变左。</li>
	<li>重复第二步和第三步，直到你在树中无法继续移动。</li>
</ul>

<p>交错路径的长度定义为：<strong>访问过的节点数目 - 1</strong>（单个节点的路径长度为 0 ）。</p>

<p>请你返回给定树中最长 <strong>交错路径</strong>&nbsp;的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/images/sample_1_1702.png" style="height: 283px; width: 151px;"></strong></p>

<pre><strong>输入：</strong>root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
<strong>输出：</strong>3
<strong>解释：</strong>蓝色节点为树中最长交错路径（右 -&gt; 左 -&gt; 右）。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/images/sample_2_1702.png" style="height: 253px; width: 120px;"></strong></p>

<pre><strong>输入：</strong>root = [1,1,1,null,1,null,null,1,1,null,1]
<strong>输出：</strong>4
<strong>解释：</strong>蓝色节点为树中最长交错路径（左 -&gt; 右 -&gt; 左 -&gt; 右）。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树最多有&nbsp;<code>50000</code>&nbsp;个节点。</li>
	<li>每个节点的值在&nbsp;<code>[1, 100]</code> 之间。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

时间复杂度 $O(n)$，其中 $n$ 是树中节点的个数。

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
    def longestZigZag(self, root: TreeNode) -> int:
        def dfs(root, l, r):
            if root is None:
                return
            nonlocal ans
            ans = max(ans, l, r)
            dfs(root.left, r + 1, 0)
            dfs(root.right, 0, l + 1)

        ans = 0
        dfs(root, 0, 0)
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
    private int ans;

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode root, int l, int r) {
        if (root == null) {
            return;
        }
        ans = Math.max(ans, Math.max(l, r));
        dfs(root.left, r + 1, 0);
        dfs(root.right, 0, l + 1);
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
    int ans = 0;

    int longestZigZag(TreeNode* root) {
        dfs(root, 0, 0);
        return ans;
    }

    void dfs(TreeNode* root, int l, int r) {
        if (!root) return;
        ans = max(ans, max(l, r));
        dfs(root->left, r + 1, 0);
        dfs(root->right, 0, l + 1);
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
func longestZigZag(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode, l, r int)
	dfs = func(root *TreeNode, l, r int) {
		if root == nil {
			return
		}
		ans = max(ans, max(l, r))
		dfs(root.Left, r+1, 0)
		dfs(root.Right, 0, l+1)
	}
	dfs(root, 0, 0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
