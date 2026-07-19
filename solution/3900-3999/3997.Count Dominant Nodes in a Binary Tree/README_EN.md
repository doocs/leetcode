---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3997.Count%20Dominant%20Nodes%20in%20a%20Binary%20Tree/README_EN.md
---

<!-- problem:start -->

# [3997. Count Dominant Nodes in a Binary Tree](https://leetcode.com/problems/count-dominant-nodes-in-a-binary-tree)

[中文文档](/solution/3900-3999/3997.Count%20Dominant%20Nodes%20in%20a%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a <span data-keyword="complete-binary-tree">complete binary tree</span>.</p>

<p>A node <code>x</code> is called <strong>dominant</strong> if its value is equal to the maximum value among all nodes in the <span data-keyword="subtree">subtree</span> rooted at <code>x</code>.</p>

<p>Return the number of dominant nodes in the tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3997.Count%20Dominant%20Nodes%20in%20a%20Binary%20Tree/images/tnew.png" style="width: 300px; height: 193px;" /></p>

<p><strong>Input:</strong> <span class="example-io">root = [5,3,8,2,4,7,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The leaf nodes with values 2, 4, 7, and 1 are dominant.</li>
	<li>The node with value 8 is dominant because its value is the maximum value in its subtree <code>[8, 7, 1]</code>.</li>
	<li>Thus, the answer is 5.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3997.Count%20Dominant%20Nodes%20in%20a%20Binary%20Tree/images/t9.png" style="width: 250px; height: 183px;" /></p>

<p><strong>Input:</strong> <span class="example-io">root = [1,2,3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The leaf nodes with values 1, 2, and 3 are dominant.</li>
	<li>The node with value 2 whose subtree is <code>[2, 1, 2]</code> is dominant because its value is the maximum value in its subtree.</li>
	<li>Thus, the answer is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>The tree is guaranteed to be a complete binary tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

A node is dominant if its value equals the maximum value in the subtree rooted at it. Therefore, for each node, we only need the maximum values of its left and right subtrees, then compare them with the node itself.

Perform a bottom-up DFS: return $-\infty$ for a null node (implemented with the language's minimum integer value), and for the current node compute $\textit{mx} = \max(\textit{leftMax}, \textit{rightMax}, \textit{node.val})$. If $\textit{mx} = \textit{node.val}$, the node is dominant and the answer is incremented by one. Finally return $\textit{mx}$ for the parent node.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

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
    def countDominantNodes(self, root: TreeNode | None) -> int:
        def dfs(node: TreeNode | None) -> int:
            if node is None:
                return -inf
            l = dfs(node.left)
            r = dfs(node.right)
            mx = max(l, r, node.val)
            if mx == node.val:
                nonlocal ans
                ans += 1
            return mx

        ans = 0
        dfs(root)
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
    private int ans;

    public int countDominantNodes(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int l = dfs(node.left);
        int r = dfs(node.right);
        int mx = Math.max(Math.max(l, r), node.val);
        if (mx == node.val) {
            ++ans;
        }
        return mx;
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
    int countDominantNodes(TreeNode* root) {
        int ans = 0;
        auto dfs = [&](this auto&& dfs, TreeNode* node) -> int {
            if (!node) {
                return INT_MIN;
            }
            int l = dfs(node->left);
            int r = dfs(node->right);
            int mx = max({l, r, node->val});
            if (mx == node->val) {
                ++ans;
            }
            return mx;
        };
        dfs(root);
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
func countDominantNodes(root *TreeNode) int {
	ans := 0
	var dfs func(*TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return math.MinInt32
		}
		l := dfs(node.Left)
		r := dfs(node.Right)
		mx := max(l, r, node.Val)
		if mx == node.Val {
			ans++
		}
		return mx
	}
	dfs(root)
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

function countDominantNodes(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (node: TreeNode | null): number => {
        if (!node) {
            return -Infinity;
        }
        const l = dfs(node.left);
        const r = dfs(node.right);
        const mx = Math.max(l, r, node.val);
        if (mx === node.val) {
            ++ans;
        }
        return mx;
    };
    dfs(root);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
