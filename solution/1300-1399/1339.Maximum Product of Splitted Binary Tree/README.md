---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/README.md
rating: 1674
source: 第 174 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

# [1339. 分裂二叉树的最大乘积](https://leetcode.cn/problems/maximum-product-of-splitted-binary-tree)

[English Version](/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树，它的根为&nbsp;<code>root</code> 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。</p>

<p>由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/images/sample_1_1699.png" style="height: 200px; width: 495px;"></strong></p>

<pre><strong>输入：</strong>root = [1,2,3,4,5,6]
<strong>输出：</strong>110
<strong>解释：</strong>删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/images/sample_2_1699.png" style="height: 200px; width: 495px;"></p>

<pre><strong>输入：</strong>root = [1,null,2,3,4,null,null,5,6]
<strong>输出：</strong>90
<strong>解释：</strong>移除红色的边，得到 2 棵子树，和分别是 15 和 6 。它们的乘积为 90 （15*6）
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [2,3,9,10,7,8,6,5,4,11,1]
<strong>输出：</strong>1025
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root = [1,1]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树最多有&nbsp;<code>50000</code>&nbsp;个节点，且至少有&nbsp;<code>2</code>&nbsp;个节点。</li>
	<li>每个节点的值在&nbsp;<code>[1, 10000]</code>&nbsp;之间。</li>
</ul>

## 解法

### 方法一：两次 DFS

我们可以用两次 DFS 来解决这个问题。

第一次，我们用一个 $sum(root)$ 函数递归求出整棵树所有节点的和，记为 $s$。

第二次，我们用一个 $dfs(root)$ 函数递归遍历每个节点，求出以当前节点为根的子树的节点和 $t$，那么当前节点与其父节点分裂后两棵子树的节点和分别为 $t$ 和 $s - t$，它们的乘积为 $t \times (s - t)$，我们遍历所有节点，求出乘积的最大值，即为答案。

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
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        def sum(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            return root.val + sum(root.left) + sum(root.right)

        def dfs(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            t = root.val + dfs(root.left) + dfs(root.right)
            nonlocal ans, s
            if t < s:
                ans = max(ans, t * (s - t))
            return t

        mod = 10**9 + 7
        s = sum(root)
        ans = 0
        dfs(root)
        return ans % mod
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
    private long ans;
    private long s;

    public int maxProduct(TreeNode root) {
        final int mod = (int) 1e9 + 7;
        s = sum(root);
        dfs(root);
        return (int) (ans % mod);
    }

    private long dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long t = root.val + dfs(root.left) + dfs(root.right);
        if (t < s) {
            ans = Math.max(ans, t * (s - t));
        }
        return t;
    }

    private long sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
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
    int maxProduct(TreeNode* root) {
        using ll = long long;
        ll ans = 0;
        const int mod = 1e9 + 7;

        function<ll(TreeNode*)> sum = [&](TreeNode* root) -> ll {
            if (!root) {
                return 0;
            }
            return root->val + sum(root->left) + sum(root->right);
        };

        ll s = sum(root);

        function<ll(TreeNode*)> dfs = [&](TreeNode* root) -> ll {
            if (!root) {
                return 0;
            }
            ll t = root->val + dfs(root->left) + dfs(root->right);
            if (t < s) {
                ans = max(ans, t * (s - t));
            }
            return t;
        };

        dfs(root);
        return ans % mod;
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
func maxProduct(root *TreeNode) (ans int) {
	const mod = 1e9 + 7
	var sum func(*TreeNode) int
	sum = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		return root.Val + sum(root.Left) + sum(root.Right)
	}
	s := sum(root)
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		t := root.Val + dfs(root.Left) + dfs(root.Right)
		if t < s {
			ans = max(ans, t*(s-t))
		}
		return t
	}
	dfs(root)
	ans %= mod
	return
}
```

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function maxProduct(root: TreeNode | null): number {
    const sum = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    };
    const s = sum(root);
    let ans = 0;
    const mod = 1e9 + 7;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const t = root.val + dfs(root.left) + dfs(root.right);
        if (t < s) {
            ans = Math.max(ans, t * (s - t));
        }
        return t;
    };
    dfs(root);
    return ans % mod;
}
```

<!-- tabs:end -->

<!-- end -->
