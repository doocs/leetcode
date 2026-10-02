---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Binary Tree
    - Tree DP
---

<!-- problem:start -->

# [337. House Robber III](https://leetcode.com/problems/house-robber-iii)

[中文文档](/solution/0300-0399/0337.House%20Robber%20III/README.md)

## Description

<!-- description:start -->

<p>The thief has found himself a new place for his thievery again. There is only one entrance to this area, called <code>root</code>.</p>

<p>Besides the <code>root</code>, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if <strong>two directly-linked houses were broken into on the same night</strong>.</p>

<p>Given the <code>root</code> of the binary tree, return <em>the maximum amount of money the thief can rob <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0337.House%20Robber%20III/images/rob1-tree.jpg" style="width: 277px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,2,3,null,3,null,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0337.House%20Robber%20III/images/rob2-tree.jpg" style="width: 357px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,3,null,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 4 + 5 = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Adjacent nodes cannot both be robbed. Computing steal/skip separately and walking each subtree twice repeats work.
>
> Postorder returns (rob root, skip root). Robbing the root forces both children to skip; skipping takes the better of each child. The answer is the max at the root. Each node is visited once.

<!-- thinking:end -->

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
    def rob(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        order = []
        stack = [root]
        while stack:
            node = stack.pop()
            order.append(node)
            if node.left:
                stack.append(node.left)
            if node.right:
                stack.append(node.right)

        dp = {}
        for node in reversed(order):
            la, lb = dp.get(id(node.left), (0, 0))
            ra, rb = dp.get(id(node.right), (0, 0))
            dp[id(node)] = (node.val + lb + rb, max(la, lb) + max(ra, rb))
        return max(dp[id(root)])
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
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> order = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            order.add(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Map<TreeNode, int[]> dp = new IdentityHashMap<>();
        for (int i = order.size() - 1; i >= 0; --i) {
            TreeNode node = order.get(i);
            int[] left = node.left == null ? new int[2] : dp.get(node.left);
            int[] right = node.right == null ? new int[2] : dp.get(node.right);
            dp.put(node, new int[] {node.val + left[1] + right[1], Math.max(left[0], left[1]) + Math.max(right[0], right[1])});
        }
        int[] ans = dp.get(root);
        return Math.max(ans[0], ans[1]);
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
    int rob(TreeNode* root) {
        if (!root) return 0;
        vector<TreeNode*> order;
        stack<TreeNode*> st{{root}};
        while (!st.empty()) {
            TreeNode* node = st.top();
            st.pop();
            order.push_back(node);
            if (node->left) st.push(node->left);
            if (node->right) st.push(node->right);
        }
        unordered_map<TreeNode*, pair<int, int>> dp;
        for (auto it = order.rbegin(); it != order.rend(); ++it) {
            TreeNode* node = *it;
            auto left = node->left ? dp[node->left] : pair<int, int>{0, 0};
            auto right = node->right ? dp[node->right] : pair<int, int>{0, 0};
            dp[node] = {node->val + left.second + right.second, max(left.first, left.second) + max(right.first, right.second)};
        }
        auto [a, b] = dp[root];
        return max(a, b);
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
func rob(root *TreeNode) int {
	if root == nil {
		return 0
	}
	order := []*TreeNode{}
	stack := []*TreeNode{root}
	for len(stack) > 0 {
		node := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		order = append(order, node)
		if node.Left != nil {
			stack = append(stack, node.Left)
		}
		if node.Right != nil {
			stack = append(stack, node.Right)
		}
	}
	type pair struct{ rob, skip int }
	dp := make(map[*TreeNode]pair, len(order))
	for i := len(order) - 1; i >= 0; i-- {
		node := order[i]
		left, right := dp[node.Left], dp[node.Right]
		dp[node] = pair{node.Val + left.skip + right.skip, max(left.rob, left.skip) + max(right.rob, right.skip)}
	}
	result := dp[root]
	a, b := result.rob, result.skip
	return max(a, b)
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

function rob(root: TreeNode | null): number {
    if (root === null) {
        return 0;
    }
    const order: TreeNode[] = [];
    const stack = [root];
    while (stack.length > 0) {
        const node = stack.pop()!;
        order.push(node);
        if (node.left) stack.push(node.left);
        if (node.right) stack.push(node.right);
    }
    const dp = new Map<TreeNode, [number, number]>();
    for (let i = order.length - 1; i >= 0; --i) {
        const node = order[i];
        const [la, lb] = node.left ? dp.get(node.left)! : [0, 0];
        const [ra, rb] = node.right ? dp.get(node.right)! : [0, 0];
        dp.set(node, [node.val + lb + rb, Math.max(la, lb) + Math.max(ra, rb)]);
    }
    return Math.max(...dp.get(root)!);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
