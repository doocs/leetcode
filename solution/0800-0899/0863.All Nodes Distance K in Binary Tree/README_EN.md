---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Binary Tree
---

<!-- problem:start -->

# [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree)

[中文文档](/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> of a binary tree, the value of a target node <code>target</code>, and an integer <code>k</code>, return <em>an array of the values of all nodes that have a distance </em><code>k</code><em> from the target node.</em></p>

<p>You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/images/sketch0.png" style="width: 500px; height: 429px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
<strong>Output:</strong> [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1], target = 1, k = 3
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 500]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 500</code></li>
	<li>All the values <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>target</code> is the value of one of the nodes in the tree.</li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Hash Table

We first use DFS to traverse the entire tree and save each node's parent node in the hash table $\textit{g}$.

Next, we use DFS again, starting from $\textit{target}$, to search for nodes at a distance of $k$ both upwards and downwards, and add them to the result array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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
