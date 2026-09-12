---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [742. 二叉树最近的叶节点 🔒](https://leetcode.cn/problems/closest-leaf-in-a-binary-tree)

[English Version](/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>每个结点的值互不相同</strong>&nbsp;的二叉树，和一个目标整数值 <code>k</code>，返回 <em>树中与目标值 <code>k</code>&nbsp; <strong>最近的叶结点</strong></em> 。&nbsp;</p>

<p><strong>与叶结点最近</strong><em> </em>表示在二叉树中到达该叶节点需要行进的边数与到达其它叶结点相比最少。而且，当一个结点没有孩子结点时称其为叶结点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/images/closest1-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1, 3, 2], k = 1
<strong>输出：</strong> 2
<strong>解释：</strong> 2 和 3 都是距离目标 1 最近的叶节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/images/closest2-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1], k = 1
<strong>输出：</strong>1
<strong>解释：</strong>最近的叶节点是根结点自身。
</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/images/closest3-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4,null,null,null,5,null,6], k = 2
<strong>输出：</strong>3
<strong>解释：</strong>值为 3（而不是值为 6）的叶节点是距离结点 2 的最近结点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树节点数在&nbsp;<code>[1, 1000]</code> 范围内</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
	<li>每个节点值都 <strong>不同</strong></li>
	<li>给定的二叉树中有某个结点使得&nbsp;<code>node.val == k</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + BFS

<!-- thinking:start -->

> **思考**
>
> 求与值为 $k$ 的节点最近的叶子。叶子可能在子树里，也可能要先上到父亲再下到另一枝。只在树上向下搜会漏掉。
>
> 把树当成无向图后，从 $k$ 出发的最短路第一次碰到的叶子就是答案。节点值互异，建图方便。
>
> DFS 记下每个节点与父节点的双向边，再从 $k$ 做 BFS，出队时若左右皆空则返回。时间 $O(n)$。

<!-- thinking:end -->

我们首先使用深度优先搜索构建一个无向图 $g$，其中 $g[node]$ 表示与节点 $node$ 相邻的节点集合。然后我们从节点 $k$ 开始进行广度优先搜索，直到找到一个叶节点为止，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树节点个数。

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
    def findClosestLeaf(self, root: Optional[TreeNode], k: int) -> int:
        def dfs(root: Optional[TreeNode], fa: Optional[TreeNode]):
            if root:
                g[root].append(fa)
                g[fa].append(root)
                dfs(root.left, root)
                dfs(root.right, root)

        g = defaultdict(list)
        dfs(root, None)
        q = deque(node for node in g if node and node.val == k)
        vis = set(q)
        while 1:
            node = q.popleft()
            if node:
                if node.left == node.right:
                    return node.val
                for nxt in g[node]:
                    if nxt not in vis:
                        vis.add(nxt)
                        q.append(nxt)
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
    private Map<TreeNode, List<TreeNode>> g = new HashMap<>();

    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root, null);
        Deque<TreeNode> q = new LinkedList<>();
        Set<TreeNode> vis = new HashSet<>(q.size());
        for (TreeNode node : g.keySet()) {
            if (node != null && node.val == k) {
                vis.add(node);
                q.offer(node);
                break;
            }
        }
        while (true) {
            TreeNode node = q.poll();
            if (node != null) {
                if (node.left == node.right) {
                    return node.val;
                }
                for (TreeNode nxt : g.get(node)) {
                    if (vis.add(nxt)) {
                        q.offer(nxt);
                    }
                }
            }
        }
    }

    private void dfs(TreeNode root, TreeNode fa) {
        if (root != null) {
            g.computeIfAbsent(root, k -> new ArrayList<>()).add(fa);
            g.computeIfAbsent(fa, k -> new ArrayList<>()).add(root);
            dfs(root.left, root);
            dfs(root.right, root);
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int findClosestLeaf(TreeNode* root, int k) {
        unordered_map<TreeNode*, vector<TreeNode*>> g;
        function<void(TreeNode*, TreeNode*)> dfs = [&](TreeNode* root, TreeNode* fa) {
            if (root) {
                g[root].push_back(fa);
                g[fa].push_back(root);
                dfs(root->left, root);
                dfs(root->right, root);
            }
        };
        dfs(root, nullptr);
        queue<TreeNode*> q;
        unordered_set<TreeNode*> vis;
        for (auto& [node, _] : g) {
            if (node && node->val == k) {
                q.push(node);
                vis.insert(node);
            }
        }
        while (1) {
            auto node = q.front();
            q.pop();
            if (node) {
                if (node->left == node->right) {
                    return node->val;
                }
                for (auto& nxt : g[node]) {
                    if (vis.count(nxt)) {
                        continue;
                    }
                    q.push(nxt);
                    vis.insert(nxt);
                }
            }
        }
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
func findClosestLeaf(root *TreeNode, k int) int {
	g := map[*TreeNode][]*TreeNode{}
	var dfs func(*TreeNode, *TreeNode)
	dfs = func(root, fa *TreeNode) {
		if root != nil {
			g[root] = append(g[root], fa)
			g[fa] = append(g[fa], root)
			dfs(root.Left, root)
			dfs(root.Right, root)
		}
	}
	dfs(root, nil)
	q := []*TreeNode{}
	vis := map[*TreeNode]bool{}
	for node := range g {
		if node != nil && node.Val == k {
			q = append(q, node)
			vis[node] = true
			break
		}
	}
	for {
		node := q[0]
		q = q[1:]
		if node != nil {
			if node.Left == node.Right {
				return node.Val
			}
			for _, nxt := range g[node] {
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
	}
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
