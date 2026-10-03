---
comments: true
difficulty: Hard
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Binary Tree
    - Tree DP
---

<!-- problem:start -->

# [968. Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras)

[中文文档](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming (Tree DP)

<!-- thinking:start -->

> **Thinking**
>
> A camera covers itself, its parent, and its children; we want as few as possible. The optimum at a node depends on the children, so we distinguish “has a camera / covered by a child / uncovered”. Tree DP returns the three minima bottom-up; the root may not stay uncovered.

<!-- thinking:end -->

For each node, we define three states:

- `a`: The current node has a camera
- `b`: The current node does not have a camera, but is monitored by its children
- `c`: The current node does not have a camera and is not monitored by its children

Next, we design a function $dfs(root)$, which will return an array of length 3, representing the minimum number of cameras in the subtree rooted at `root` for the three states. The answer is $\min(dfs(root)[0], dfs(root)[1])$.

The calculation process of the function $dfs(root)$ is as follows:

If `root` is null, return $[inf, 0, 0]$, where `inf` represents a very large number, used to indicate an impossible situation.

Otherwise, we recursively calculate the left and right subtrees of `root`, obtaining $[la, lb, lc]$ and $[ra, rb, rc]$ respectively.

- If the current node has a camera, then its left and right children must be in a monitored state, i.e., $a = \min(la, lb, lc) + \min(ra, rb, rc) + 1$.
- If the current node does not have a camera but is monitored by its children, then one or both of the children must have a camera, i.e., $b = \min(la + rb, lb + ra, la + ra)$.
- If the current node does not have a camera and is not monitored by its children, then the children must be monitored by their children, i.e., $c = lb + rb$.

Finally, we return $[a, b, c]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary tree.

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

<!-- solution:start -->

### Solution 2: Tree DP on an Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> A camera covers itself, its parent, and its children, so the minimum depends on how each node is covered. Bottom-up, the three states are “has a camera”, “covered by a child”, and “uncovered”. On a short tree, returning those three minima is enough, and the root may not stay uncovered.
>
> The tree can contain $1000$ nodes. A left chain makes this postorder walk recurse once per node and overflow the call stack.
>
> The three states of a node depend only on the three states of its children, so children must be finished before their parent.
>
> An explicit stack performs that postorder walk. On entry it pushes an exit marker and the two children; on exit it writes the node’s states with the original three formulas. The answer is the smaller of the root’s first two states.

<!-- thinking:end -->

For each node, we define three states:

- `a`: The current node has a camera
- `b`: The current node does not have a camera, but is monitored by its children
- `c`: The current node does not have a camera and is not monitored by its children

A null node corresponds to $(inf, 0, 0)$, where $inf$ is a very large number used for an impossible situation.

We walk the binary tree in postorder with an explicit stack. On entry we push an exit marker, then the right child and the left child. On exit the children’s states are already stored as $[la, lb, lc]$ and $[ra, rb, rc]$.

- If the current node has a camera, then its left and right children must be in a monitored state, i.e., $a = \min(la, lb, lc) + \min(ra, rb, rc) + 1$.
- If the current node does not have a camera but is monitored by its children, then one or both of the children must have a camera, i.e., $b = \min(la + rb, lb + ra, la + ra)$.
- If the current node does not have a camera and is not monitored by its children, then the children must be monitored by their children, i.e., $c = lb + rb$.

The root must not stay uncovered, so the answer is $\min(a, b)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary tree.

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
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        stk = [(root, 0)]
        sub = {}
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.right, 0))
                stk.append((node.left, 0))
                continue
            la, lb, lc = sub[id(node.left)] if node.left is not None else (inf, 0, 0)
            ra, rb, rc = sub[id(node.right)] if node.right is not None else (inf, 0, 0)
            a = min(la, lb, lc) + min(ra, rb, rc) + 1
            b = min(la + rb, lb + ra, la + ra)
            c = lb + rb
            sub[id(node)] = (a, b, c)
        a, b, _ = sub[id(root)]
        return min(a, b)
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
    private static final int INF = 1 << 29;

    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, int[]> sub = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, 1));
                if (node.right != null) {
                    stk.push(new Frame(node.right, 0));
                }
                if (node.left != null) {
                    stk.push(new Frame(node.left, 0));
                }
                continue;
            }
            int[] l = node.left == null ? new int[] {INF, 0, 0} : sub.get(node.left);
            int[] r = node.right == null ? new int[] {INF, 0, 0} : sub.get(node.right);
            int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
            int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
            int c = l[1] + r[1];
            sub.put(node, new int[] {a, b, c});
        }
        int[] ans = sub.get(root);
        return Math.min(ans[0], ans[1]);
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
    int minCameraCover(TreeNode* root) {
        if (!root) {
            return 0;
        }
        const int inf = 1 << 29;
        unordered_map<TreeNode*, array<int, 3>> sub;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.emplace_back(node, 1);
                if (node->right) {
                    stk.emplace_back(node->right, 0);
                }
                if (node->left) {
                    stk.emplace_back(node->left, 0);
                }
                continue;
            }
            array<int, 3> l = node->left ? sub[node->left] : array<int, 3>{inf, 0, 0};
            array<int, 3> r = node->right ? sub[node->right] : array<int, 3>{inf, 0, 0};
            int a = 1 + min({l[0], l[1], l[2]}) + min({r[0], r[1], r[2]});
            int b = min({l[0] + r[0], l[0] + r[1], l[1] + r[0]});
            int c = l[1] + r[1];
            sub[node] = {a, b, c};
        }
        auto ans = sub[root];
        return min(ans[0], ans[1]);
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
func minCameraCover(root *TreeNode) int {
	if root == nil {
		return 0
	}
	const inf = 1 << 29
	sub := map[*TreeNode][3]int{}
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node := cur.node
		if cur.state == 0 {
			stk = append(stk, frame{node, 1})
			if node.Right != nil {
				stk = append(stk, frame{node.Right, 0})
			}
			if node.Left != nil {
				stk = append(stk, frame{node.Left, 0})
			}
			continue
		}
		l := [3]int{inf, 0, 0}
		r := [3]int{inf, 0, 0}
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		a := 1 + min(l[0], min(l[1], l[2])) + min(r[0], min(r[1], r[2]))
		b := min(l[0]+r[0], min(l[0]+r[1], l[1]+r[0]))
		c := l[1] + r[1]
		sub[node] = [3]int{a, b, c}
	}
	ans := sub[root]
	return min(ans[0], ans[1])
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

function minCameraCover(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    const inf = 1 << 29;
    const sub = new Map<TreeNode, number[]>();
    const stk: [TreeNode, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (state === 0) {
            stk.push([node, 1]);
            if (node.right) {
                stk.push([node.right, 0]);
            }
            if (node.left) {
                stk.push([node.left, 0]);
            }
            continue;
        }
        const l = node.left ? sub.get(node.left)! : [inf, 0, 0];
        const r = node.right ? sub.get(node.right)! : [inf, 0, 0];
        const a = 1 + Math.min(l[0], l[1], l[2]) + Math.min(r[0], r[1], r[2]);
        const b = Math.min(l[0] + r[0], l[0] + r[1], l[1] + r[0]);
        const c = l[1] + r[1];
        sub.set(node, [a, b, c]);
    }
    const ans = sub.get(root)!;
    return Math.min(ans[0], ans[1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
