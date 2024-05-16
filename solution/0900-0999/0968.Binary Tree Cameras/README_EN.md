---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0968.Binary%20Tree%20Cameras/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Binary Tree
---

<!-- problem:start -->

# [968. Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras)

[中文文档](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README.md)

## Description

<p>You are given the <code>root</code> of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.</p>

<p>Return <em>the minimum number of cameras needed to monitor all nodes of the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_01.png" style="width: 138px; height: 163px;" />
<pre>
<strong>Input:</strong> root = [0,0,null,0,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> One camera is enough to monitor all nodes if placed as shown.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_02.png" style="width: 139px; height: 312px;" />
<pre>
<strong>Input:</strong> root = [0,0,null,0,null,0,null,null,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong> At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>Node.val == 0</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return inf, 0, 0
            la, lb, lc = dfs(root.left)
            ra, rb, rc = dfs(root.right)
            a = min(la, lb, lc) + min(ra, rb, rc) + 1
            b = min(la + rb, lb + ra, la + ra)
            c = lb + rb
            return a, b, c

        a, b, _ = dfs(root)
        return min(a, b)
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
    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1 << 29, 0, 0};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
        int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
        int c = l[1] + r[1];
        return new int[] {a, b, c};
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
struct Status {
    int a, b, c;
};

class Solution {
public:
    int minCameraCover(TreeNode* root) {
        auto [a, b, _] = dfs(root);
        return min(a, b);
    }

    Status dfs(TreeNode* root) {
        if (!root) {
            return {1 << 29, 0, 0};
        }
        auto [la, lb, lc] = dfs(root->left);
        auto [ra, rb, rc] = dfs(root->right);
        int a = 1 + min({la, lb, lc}) + min({ra, rb, rc});
        int b = min({la + ra, la + rb, lb + ra});
        int c = lb + rb;
        return {a, b, c};
    };
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
func minCameraCover(root *TreeNode) int {
	var dfs func(*TreeNode) (int, int, int)
	dfs = func(root *TreeNode) (int, int, int) {
		if root == nil {
			return 1 << 29, 0, 0
		}
		la, lb, lc := dfs(root.Left)
		ra, rb, rc := dfs(root.Right)
		a := 1 + min(la, min(lb, lc)) + min(ra, min(rb, rc))
		b := min(la+ra, min(la+rb, lb+ra))
		c := lb + rb
		return a, b, c
	}
	a, b, _ := dfs(root)
	return min(a, b)
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

function minCameraCover(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null): number[] => {
        if (!root) {
            return [1 << 29, 0, 0];
        }
        const [la, lb, lc] = dfs(root.left);
        const [ra, rb, rc] = dfs(root.right);
        const a = 1 + Math.min(la, lb, lc) + Math.min(ra, rb, rc);
        const b = Math.min(la + ra, la + rb, lb + ra);
        const c = lb + rb;
        return [a, b, c];
    };
    const [a, b, _] = dfs(root);
    return Math.min(a, b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
