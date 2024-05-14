---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/README.md
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

# [1973. 值等于子节点值之和的节点数量 🔒](https://leetcode.cn/problems/count-nodes-equal-to-sum-of-descendants)

[English Version](/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一颗二叉树的根节点&nbsp;<code>root</code>&nbsp;，返回满足条件：节点的值等于该节点所有子节点的值之和&nbsp;<em>的节点的数量。</em></p>

<p>一个节点&nbsp;<code>x</code>&nbsp;的&nbsp;<strong>子节点</strong>&nbsp;是指从节点&nbsp;<code>x</code>&nbsp;出发，到所有叶子节点路径上的节点。没有子节点的节点的子节点和视为&nbsp;<code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-16-50-diagram-drawio-diagrams-net.png" style="width: 250px; height: 207px;" />
<pre>
<strong>输入:</strong> root = [10,3,4,2,1]
<strong>输出:</strong> 2
<strong>解释:</strong>
对于值为10的节点: 其子节点之和为： 3+4+2+1 = 10。
对于值为3的节点：其子节点之和为： 2+1 = 3。
</pre>

<p><strong>示例&nbsp;2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-25-21-diagram-drawio-diagrams-net.png" style="height: 196px; width: 200px;" />
<pre>
<strong>输入:</strong> root = [2,3,null,2,null]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有节点满足其值等于子节点之和。
</pre>

<p><strong>示例&nbsp;3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-23-53-diagram-drawio-diagrams-net.png" style="width: 50px; height: 50px;" />
<pre>
<strong>输入:</strong> root = [0]
<strong>输出:</strong> 1
<strong>解释:</strong>
对于值为0的节点：因为它没有子节点，所以自己点之和为0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量范围：&nbsp;<code>[1, 10<sup>5</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：递归

我们设计一个函数 $dfs(root)$，该函数返回以 $root$ 为根节点的子树的所有节点值之和。函数 $dfs(root)$ 的执行过程如下：

-   如果 $root$ 为空，返回 $0$；
-   否则，我们递归地计算 $root$ 的左子树和右子树的节点值之和，记为 $l$ 和 $r$；如果 $l + r = root.val$，说明以 $root$ 为根节点的子树满足条件，我们将答案加 $1$；最后，返回 $root.val + l + r$。

然后我们调用函数 $dfs(root)$，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def equalToDescendants(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            if l + r == root.val:
                nonlocal ans
                ans += 1
            return root.val + l + r

        ans = 0
        dfs(root)
        return ans
```

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

    public int equalToDescendants(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (l + r == root.val) {
            ++ans;
        }
        return root.val + l + r;
    }
}
```

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
    int equalToDescendants(TreeNode* root) {
        int ans = 0;
        function<long long(TreeNode*)> dfs = [&](TreeNode* root) -> long long {
            if (!root) {
                return 0;
            }
            auto l = dfs(root->left);
            auto r = dfs(root->right);
            ans += l + r == root->val;
            return root->val + l + r;
        };
        dfs(root);
        return ans;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func equalToDescendants(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l+r == root.Val {
			ans++
		}
		return root.Val + l + r
	}
	dfs(root)
	return
}
```

<!-- tabs:end -->

<!-- end -->
