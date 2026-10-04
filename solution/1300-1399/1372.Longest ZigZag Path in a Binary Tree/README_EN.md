---
comments: true
difficulty: Medium
rating: 1713
source: Biweekly Contest 21 Q3
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Binary Tree
    - Tree DP
---

<!-- problem:start -->

# [1372. Longest ZigZag Path in a Binary Tree](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree)

[中文文档](/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a binary tree.</p>

<p>A ZigZag path for a binary tree is defined as follow:</p>

<ul>
	<li>Choose <strong>any </strong>node in the binary tree and a direction (right or left).</li>
	<li>If the current direction is right, move to the right child of the current node; otherwise, move to the left child.</li>
	<li>Change the direction from right to left or from left to right.</li>
	<li>Repeat the second and third steps until you can&#39;t move in the tree.</li>
</ul>

<p>Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).</p>

<p>Return <em>the longest <strong>ZigZag</strong> path contained in that tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/images/sample_1_1702.png" style="width: 221px; height: 383px;" />
<pre>
<strong>Input:</strong> root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Longest ZigZag path in blue nodes (right -&gt; left -&gt; right).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/images/sample_2_1702.png" style="width: 157px; height: 329px;" />
<pre>
<strong>Input:</strong> root = [1,1,1,null,1,null,null,1,1,null,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Longest ZigZag path in blue nodes (left -&gt; right -&gt; left -&gt; right).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A zigzag must alternate left and right while descending. Restarting from every node and direction repeats subtrees. DFS carries the length already obtained if the last step was left ($l$) or right ($r$). The left child continues with $r+1$ and resets the right length; the right child is symmetric. A global maximum is kept.

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
    def longestZigZag(self, root: TreeNode) -> int:
        def dfs(root, l, r):
            if root is None:
                return
            nonlocal ans
            ans = max(ans, l, r)
            dfs(root.left, r + 1, 0)
            dfs(root.right, 0, l + 1)

        ans = 0
        dfs(root, 0, 0)
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

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode root, int l, int r) {
        if (root == null) {
            return;
        }
        ans = Math.max(ans, Math.max(l, r));
        dfs(root.left, r + 1, 0);
        dfs(root.right, 0, l + 1);
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
    int ans = 0;

    int longestZigZag(TreeNode* root) {
        dfs(root, 0, 0);
        return ans;
    }

    void dfs(TreeNode* root, int l, int r) {
        if (!root) return;
        ans = max(ans, max(l, r));
        dfs(root->left, r + 1, 0);
        dfs(root->right, 0, l + 1);
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
func longestZigZag(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode, l, r int)
	dfs = func(root *TreeNode, l, r int) {
		if root == nil {
			return
		}
		ans = max(ans, max(l, r))
		dfs(root.Left, r+1, 0)
		dfs(root.Right, 0, l+1)
	}
	dfs(root, 0, 0)
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> A zigzag must alternate left and right as it descends. Carrying the length of a path that last stepped left ($l$) or right ($r$), then recursing, is enough on a short tree: the left child continues with $r+1$ and a zero right length, and the right child is symmetric.
>
> The tree can contain $5\times 10^4$ nodes. A chain recurses once per node and overflows the call stack.
>
> The lengths at a node depend only on the parent's other direction, and the answer is a running maximum, so the walk does not need a return value.
>
> An explicit stack stores each node with the $l$ and $r$ of the step that reached it. After a node is popped, the maximum is updated and its children are pushed with those new lengths. The left child is pushed last, so it is visited first.

<!-- thinking:end -->

We walk the tree with an explicit stack. Each frame stores the current node and the zigzag lengths $l$ and $r$ of the step that arrived there.

After a node is popped, $\max(l, r)$ updates the answer. A left child is pushed with left length $r+1$ and right length $0$; a right child is pushed with right length $l+1$ and left length $0$. The root starts with both lengths equal to $0$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of nodes.

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
    def longestZigZag(self, root: TreeNode) -> int:
        ans = 0
        stk = [(root, 0, 0)]
        while stk:
            node, l, r = stk.pop()
            if node is None:
                continue
            ans = max(ans, l, r)
            stk.append((node.right, 0, l + 1))
            stk.append((node.left, r + 1, 0))
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
    private static class Frame {
        TreeNode node;
        int l;
        int r;

        Frame(TreeNode node, int l, int r) {
            this.node = node;
            this.l = l;
            this.r = r;
        }
    }

    public int longestZigZag(TreeNode root) {
        int ans = 0;
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, 0, 0));
        }
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            ans = Math.max(ans, Math.max(cur.l, cur.r));
            if (cur.node.right != null) {
                stk.push(new Frame(cur.node.right, 0, cur.l + 1));
            }
            if (cur.node.left != null) {
                stk.push(new Frame(cur.node.left, cur.r + 1, 0));
            }
        }
        return ans;
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
    int longestZigZag(TreeNode* root) {
        int ans = 0;
        vector<tuple<TreeNode*, int, int>> stk{{root, 0, 0}};
        while (!stk.empty()) {
            auto [node, l, r] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            ans = max(ans, max(l, r));
            stk.emplace_back(node->right, 0, l + 1);
            stk.emplace_back(node->left, r + 1, 0);
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
func longestZigZag(root *TreeNode) int {
	ans := 0
	type frame struct {
		node *TreeNode
		l, r int
	}
	stk := []frame{{root, 0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if cur.node == nil {
			continue
		}
		ans = max(ans, max(cur.l, cur.r))
		stk = append(stk, frame{cur.node.Right, 0, cur.l + 1}, frame{cur.node.Left, cur.r + 1, 0})
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
