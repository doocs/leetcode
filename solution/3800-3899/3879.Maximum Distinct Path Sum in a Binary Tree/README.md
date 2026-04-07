---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3879.Maximum%20Distinct%20Path%20Sum%20in%20a%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [3879. 二叉树中的最大不同路径和 🔒](https://leetcode.cn/problems/maximum-distinct-path-sum-in-a-binary-tree)

[English Version](/solution/3800-3899/3879.Maximum%20Distinct%20Path%20Sum%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵 <strong>二叉树</strong>&nbsp;的&nbsp;<code>root</code>，其中每个节点包含一个整数值。</p>

<p>树中的 <strong>有效</strong> 路径是指一系列 <strong>相连</strong> 的节点，使得：</p>

<ul>
	<li>路径可以在树中的 <strong>任意节点</strong> 开始和结束。</li>
	<li>路径 <strong>不</strong> 需要经过根节点。</li>
	<li>路径上的所有节点值都是 <strong>不同</strong> 的。</li>
</ul>

<p>返回一个整数，表示所有有效路径中节点值的 <strong>最大</strong> 可能总和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3879.Maximum%20Distinct%20Path%20Sum%20in%20a%20Binary%20Tree/images/screenshot-2026-01-29-at-12940am.png" style="width: 200px; height: 175px;" /></p>

<p><span class="example-io"><b>输入：</b>root = [2,2,1]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>路径&nbsp;<code>2 → 2</code>&nbsp;是无效的，因为值 2 不唯一。</li>
	<li>最大和的有效路径是 <code>2 → 1</code>，其和&nbsp;= <code>2 + 1 = 3</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3879.Maximum%20Distinct%20Path%20Sum%20in%20a%20Binary%20Tree/images/screenshot-2026-01-29-at-15149am.png" style="width: 200px; height: 204px;" /></p>

<p><span class="example-io"><b>输入：</b>root = [1,-2,5,null,null,3,5]</span></p>

<p><strong>输出：</strong><span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>路径&nbsp;<code>3 → 5 → 5</code>&nbsp;是无效的，因为值 5 不唯一。</li>
	<li>最大和的有效路径是 <code>1 → 5 → 3</code>，其和 =&nbsp;<code>1 + 5 + 3 = 9</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3879.Maximum%20Distinct%20Path%20Sum%20in%20a%20Binary%20Tree/images/screenshot-2026-01-29-at-15555am.png" style="width: 180px; height: 217px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>root = [4,6,6,null,null,null,9]</span></p>

<p><span class="example-io"><b>输出：</b>19</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>路径&nbsp;<code>6 → 4 → 6 → 9</code>&nbsp;是无效的，因为值 6 出现了多次。</li>
	<li>最大和的有效路径是&nbsp;<code>4 → 6 → 9</code>，其和 =&nbsp;<code>4 + 6 + 9 = 19</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数范围为 <code>[1, 1000]</code>。</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000​​​​​​​</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 哈希表

我们可以将树看成一个无向图，使用一个哈希表 $g$ 来存储每个节点的相邻节点，其中 $g[node]$ 包含节点 $node$ 的父节点、左子节点和右子节点。

我们使用深度优先搜索来遍历树，并构建哈希表 $g$。对于每个节点，我们将其父节点、左子节点和右子节点添加到 $g[node]$ 中。

接下来，我们使用另一个深度优先搜索来计算以每个节点为起点的最大路径和。在这个过程中，我们使用一个哈希集合 $vis$ 来记录当前路径上已经访问过的节点值，以确保路径上的节点值都是不同的。对于每个节点，我们首先检查它是否已经在 $vis$ 中，如果是，则返回 0。否则，我们将节点值添加到 $vis$ 中，并计算以该节点为起点的路径和。我们遍历 $g[node]$ 中的相邻节点，递归地计算以相邻节点为起点的路径和，并更新当前节点的路径和。最后，我们将当前节点值从 $vis$ 中移除，并返回当前节点值加上最佳路径和。

我们对树中的每个节点执行上述计算，并记录最大路径和。最终返回最大路径和作为答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是树中的节点数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxSum(self, root: Optional[TreeNode]) -> int:
        def dfs(node, p):
            if node is None:
                return
            g[node].append(p)
            g[node].append(node.left)
            g[node].append(node.right)
            dfs(node.left, node)
            dfs(node.right, node)

        def dfs2(node):
            if node is None or node.val in vis:
                return 0
            vis.add(node.val)
            res = node.val
            best = 0
            for nxt in g[node]:
                best = max(best, dfs2(nxt))
            vis.remove(node.val)
            res += best
            return res

        g = defaultdict(list)
        dfs(root, None)
        vis = set()
        ans = -inf
        for node in g:
            ans = max(ans, dfs2(node))
            vis.clear()
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
    Map<TreeNode, List<TreeNode>> g = new HashMap<>();
    Set<Integer> vis = new HashSet<>();

    public int maxSum(TreeNode root) {
        dfs(root, null);

        int ans = Integer.MIN_VALUE;
        for (TreeNode node : g.keySet()) {
            ans = Math.max(ans, dfs2(node));
            vis.clear();
        }
        return ans;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null) {
            return;
        }
        g.computeIfAbsent(node, k -> new ArrayList<>());
        g.get(node).add(p);
        g.get(node).add(node.left);
        g.get(node).add(node.right);

        dfs(node.left, node);
        dfs(node.right, node);
    }

    private int dfs2(TreeNode node) {
        if (node == null || vis.contains(node.val)) {
            return 0;
        }
        vis.add(node.val);
        int res = node.val;
        int best = 0;
        for (TreeNode nxt : g.getOrDefault(node, Collections.emptyList())) {
            best = Math.max(best, dfs2(nxt));
        }
        vis.remove(node.val);
        return res + best;
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int maxSum(TreeNode* root) {
        unordered_map<TreeNode*, vector<TreeNode*>> g;
        unordered_set<int> vis;

        auto dfs = [&](this auto&& dfs, TreeNode* node, TreeNode* p) -> void {
            if (!node) return;
            g[node].push_back(p);
            g[node].push_back(node->left);
            g[node].push_back(node->right);
            dfs(node->left, node);
            dfs(node->right, node);
        };

        auto dfs2 = [&](this auto&& dfs2, TreeNode* node) -> int {
            if (!node || vis.count(node->val)) return 0;
            vis.insert(node->val);
            int res = node->val;
            int best = 0;
            for (auto nxt : g[node]) {
                best = max(best, dfs2(nxt));
            }
            vis.erase(node->val);
            return res + best;
        };

        dfs(root, nullptr);

        int ans = INT_MIN;
        for (auto& [node, _] : g) {
            ans = max(ans, dfs2(node));
            vis.clear();
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxSum(root *TreeNode) int {
	g := map[*TreeNode][]*TreeNode{}

	var dfs func(node, p *TreeNode)
	dfs = func(node, p *TreeNode) {
		if node == nil {
			return
		}
		g[node] = append(g[node], p, node.Left, node.Right)
		dfs(node.Left, node)
		dfs(node.Right, node)
	}

	vis := map[int]bool{}

	var dfs2 func(node *TreeNode) int
	dfs2 = func(node *TreeNode) int {
		if node == nil || vis[node.Val] {
			return 0
		}
		vis[node.Val] = true
		res := node.Val
		best := 0
		for _, nxt := range g[node] {
			if v := dfs2(nxt); v > best {
				best = v
			}
		}
		vis[node.Val] = false
		return res + best
	}

	dfs(root, nil)

	ans := math.MinInt
	for node := range g {
		ans = max(ans, dfs2(node))
		clear(vis)
	}
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
function maxSum(root: TreeNode | null): number {
    const g = new Map<TreeNode, (TreeNode | null)[]>();

    function dfs(node: TreeNode | null, p: TreeNode | null): void {
        if (!node) return;
        if (!g.has(node)) g.set(node, []);
        g.get(node)!.push(p, node.left, node.right);
        dfs(node.left, node);
        dfs(node.right, node);
    }

    const vis = new Set<number>();

    function dfs2(node: TreeNode | null): number {
        if (!node || vis.has(node.val)) return 0;
        vis.add(node.val);
        let res = node.val;
        let best = 0;
        for (const nxt of g.get(node) || []) {
            best = Math.max(best, dfs2(nxt));
        }
        vis.delete(node.val);
        return res + best;
    }

    dfs(root, null);

    let ans = -Infinity;
    for (const node of g.keys()) {
        ans = Math.max(ans, dfs2(node));
        vis.clear();
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
