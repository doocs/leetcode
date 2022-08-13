# [298. 二叉树最长连续序列](https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence)

[English Version](/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵指定的二叉树的根节点 <code>root</code> ，请你计算其中 <strong>最长连续序列路径</strong> 的长度。</p>

<p><strong>最长连续序列路径</strong> 是依次递增 1 的路径。该路径，可以是从某个初始节点到树中任意节点，通过「父 - 子」关系连接而产生的任意路径。且必须从父节点到子节点，反过来是不可以的。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/images/consec1-1-tree.jpg" style="width: 322px; height: 421px;" />
<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,null,null,5]
<strong>输出：</strong>3
<strong>解释：</strong>当中，最长连续序列是 <code>3-4-5 ，所以</code>返回结果为 <code>3 。</code>
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/images/consec1-2-tree.jpg" style="width: 262px; height: 421px;" />
<pre>
<strong>输入：</strong>root = [2,null,3,2,null,1]
<strong>输出：</strong>2
<strong>解释：</strong>当中，最长连续序列是 <code>2-3 。注意，不是</code> <code>3-2-1，所以</code>返回 <code>2 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 3 * 10<sup>4</sup>]</code> 内</li>
	<li><code>-3 * 10<sup>4</sup> &lt;= Node.val &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

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
    def longestConsecutive(self, root: TreeNode) -> int:
        def dfs(root, p, t):
            nonlocal ans
            if root is None:
                return
            t = t + 1 if p is not None and p.val + 1 == root.val else 1
            ans = max(ans, t)
            dfs(root.left, root, t)
            dfs(root.right, root, t)

        ans = 1
        dfs(root, None, 1)
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

    public int longestConsecutive(TreeNode root) {
        ans = 1;
        dfs(root, null, 1);
        return ans;
    }

    private void dfs(TreeNode root, TreeNode p, int t) {
        if (root == null) {
            return;
        }
        t = p != null && p.val + 1 == root.val ? t + 1 : 1;
        ans = Math.max(ans, t);
        dfs(root.left, root, t);
        dfs(root.right, root, t);
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
    int ans;

    int longestConsecutive(TreeNode* root) {
        ans = 1;
        dfs(root, nullptr, 1);
        return ans;
    }

    void dfs(TreeNode* root, TreeNode* p, int t) {
        if (!root) return;
        t = p != nullptr && p->val + 1 == root->val ? t + 1 : 1;
        ans = max(ans, t);
        dfs(root->left, root, t);
        dfs(root->right, root, t);
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
func longestConsecutive(root *TreeNode) int {
	ans := 1
	var dfs func(root, p *TreeNode, t int)
	dfs = func(root, p *TreeNode, t int) {
		if root == nil {
			return
		}
		if p != nil && p.Val+1 == root.Val {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
		dfs(root.Left, root, t)
		dfs(root.Right, root, t)
	}
	dfs(root, nil, 1)
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
