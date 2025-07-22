---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [863. 二叉树中所有距离为 K 的结点](https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree)

[English Version](/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二叉树（具有根结点&nbsp;<code>root</code>），&nbsp;一个目标结点&nbsp;<code>target</code>&nbsp;，和一个整数值 <code>k</code>&nbsp;，返回到目标结点 <code>target</code> 距离为 <code>k</code> 的所有结点的值的数组。</p>

<p>答案可以以 <strong>任何顺序</strong> 返回。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/images/sketch0.png" style="height: 429px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
<strong>输出：</strong>[7,4,1]
<strong>解释：</strong>所求结点为与目标结点（值为 5）距离为 2 的结点，值分别为 7，4，以及 1
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [1], target = 1, k = 3
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数在&nbsp;<code>[1, 500]</code>&nbsp;范围内</li>
	<li><code>0 &lt;= Node.val &lt;= 500</code></li>
	<li><code>Node.val</code>&nbsp;中所有值 <strong>不同</strong></li>
	<li>目标结点&nbsp;<code>target</code>&nbsp;是树上的结点。</li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 哈希表

我们先用 DFS 遍历整棵树，将每个节点的父节点保存到哈希表 $\textit{g}$ 中。

接下来，我们再次用 DFS，从 $\textit{target}$ 出发，向上向下搜索距离为 $k$ 的节点，添加到结果数组中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def dfs(root, fa):
            if root is None:
                return
            g[root] = fa
            dfs(root.left, root)
            dfs(root.right, root)

        def dfs2(root, fa, k):
            if root is None:
                return
            if k == 0:
                ans.append(root.val)
                return
            for nxt in (root.left, root.right, g[root]):
                if nxt != fa:
                    dfs2(nxt, root, k - 1)

        g = {}
        dfs(root, None)
        ans = []
        dfs2(target, None, k)
        return ans
```

#### Java

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
    private Map<TreeNode, TreeNode> g = new HashMap<>();
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root, null);
        dfs2(target, null, k);
        return ans;
    }

    private void dfs(TreeNode root, TreeNode fa) {
        if (root == null) {
            return;
        }
        g.put(root, fa);
        dfs(root.left, root);
        dfs(root.right, root);
    }

    private void dfs2(TreeNode root, TreeNode fa, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        for (TreeNode nxt : new TreeNode[] {root.left, root.right, g.get(root)}) {
            if (nxt != fa) {
                dfs2(nxt, root, k - 1);
            }
        }
    }
}
```

#### C++

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    vector<int> distanceK(TreeNode* root, TreeNode* target, int k) {
        unordered_map<TreeNode*, TreeNode*> g;
        vector<int> ans;

        auto dfs = [&](this auto&& dfs, TreeNode* node, TreeNode* fa) {
            if (!node) return;
            g[node] = fa;
            dfs(node->left, node);
            dfs(node->right, node);
        };

        auto dfs2 = [&](this auto&& dfs2, TreeNode* node, TreeNode* fa, int k) {
            if (!node) return;
            if (k == 0) {
                ans.push_back(node->val);
                return;
            }
            for (auto&& nxt : {node->left, node->right, g[node]}) {
                if (nxt != fa) {
                    dfs2(nxt, node, k - 1);
                }
            }
        };

        dfs(root, nullptr);
        dfs2(target, nullptr, k);
        return ans;
    }
};
```

#### Go

```go
func distanceK(root *TreeNode, target *TreeNode, k int) []int {
	g := make(map[*TreeNode]*TreeNode)
	ans := []int{}

	var dfs func(node, fa *TreeNode)
	dfs = func(node, fa *TreeNode) {
		if node == nil {
			return
		}
		g[node] = fa
		dfs(node.Left, node)
		dfs(node.Right, node)
	}

	var dfs2 func(node, fa *TreeNode, k int)
	dfs2 = func(node, fa *TreeNode, k int) {
		if node == nil {
			return
		}
		if k == 0 {
			ans = append(ans, node.Val)
			return
		}
		for _, nxt := range []*TreeNode{node.Left, node.Right, g[node]} {
			if nxt != fa {
				dfs2(nxt, node, k-1)
			}
		}
	}

	dfs(root, nil)
	dfs2(target, nil, k)

	return ans
}
```

#### TypeScript

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

function distanceK(root: TreeNode | null, target: TreeNode | null, k: number): number[] {
    const g = new Map<TreeNode, TreeNode | null>();
    const ans: number[] = [];

    const dfs = (node: TreeNode | null, fa: TreeNode | null) => {
        if (!node) {
            return;
        }
        g.set(node, fa);
        dfs(node.left, node);
        dfs(node.right, node);
    };

    const dfs2 = (node: TreeNode | null, fa: TreeNode | null, k: number) => {
        if (!node) {
            return;
        }
        if (k === 0) {
            ans.push(node.val);
            return;
        }
        for (const nxt of [node.left, node.right, g.get(node) || null]) {
            if (nxt !== fa) {
                dfs2(nxt, node, k - 1);
            }
        }
    };

    dfs(root, null);
    dfs2(target, null, k);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
