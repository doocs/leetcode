---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Binary Tree
---

<!-- problem:start -->

# [2313. Minimum Flips in Binary Tree to Get Result 🔒](https://leetcode.com/problems/minimum-flips-in-binary-tree-to-get-result)

[中文文档](/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/README.md)

## Description

<p>You are given the <code>root</code> of a <strong>binary tree</strong> with the following properties:</p>

<ul>
	<li><strong>Leaf nodes</strong> have either the value <code>0</code> or <code>1</code>, representing <code>false</code> and <code>true</code> respectively.</li>
	<li><strong>Non-leaf nodes</strong> have either the value <code>2</code>, <code>3</code>, <code>4</code>, or <code>5</code>, representing the boolean operations <code>OR</code>, <code>AND</code>, <code>XOR</code>, and <code>NOT</code>, respectively.</li>
</ul>

<p>You are also given a boolean <code>result</code>, which is the desired result of the <strong>evaluation</strong> of the <code>root</code> node.</p>

<p>The evaluation of a node is as follows:</p>

<ul>
	<li>If the node is a leaf node, the evaluation is the <strong>value</strong> of the node, i.e. <code>true</code> or <code>false</code>.</li>
	<li>Otherwise, <strong>evaluate</strong> the node&#39;s children and <strong>apply</strong> the boolean operation of its value with the children&#39;s evaluations.</li>
</ul>

<p>In one operation, you can <strong>flip</strong> a leaf node, which causes a <code>false</code> node to become <code>true</code>, and a <code>true</code> node to become <code>false</code>.</p>

<p>Return<em> the minimum number of operations that need to be performed such that the evaluation of </em><code>root</code><em> yields </em><code>result</code>. It can be shown that there is always a way to achieve <code>result</code>.</p>

<p>A <strong>leaf node</strong> is a node that has zero children.</p>

<p>Note: <code>NOT</code> nodes have either a left child or a right child, but other non-leaf nodes have both a left child and a right child.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/images/operationstree.png" style="width: 500px; height: 179px;" />
<pre>
<strong>Input:</strong> root = [3,5,4,2,null,1,1,1,0], result = true
<strong>Output:</strong> 2
<strong>Explanation:</strong>
It can be shown that a minimum of 2 nodes have to be flipped to make the root of the tree
evaluate to true. One way to achieve this is shown in the diagram above.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [0], result = false
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The root of the tree already evaluates to false, so 0 nodes have to be flipped.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 5</code></li>
	<li><code>OR</code>, <code>AND</code>, and <code>XOR</code> nodes have <code>2</code> children.</li>
	<li><code>NOT</code> nodes have <code>1</code> child.</li>
	<li>Leaf nodes have a value of <code>0</code> or <code>1</code>.</li>
	<li>Non-leaf nodes have a value of <code>2</code>, <code>3</code>, <code>4</code>, or <code>5</code>.</li>
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
    def minimumFlips(self, root: Optional[TreeNode], result: bool) -> int:
        def dfs(root: Optional[TreeNode]) -> (int, int):
            if root is None:
                return inf, inf
            x = root.val
            if x in (0, 1):
                return x, x ^ 1
            l, r = dfs(root.left), dfs(root.right)
            if x == 2:
                return l[0] + r[0], min(l[0] + r[1], l[1] + r[0], l[1] + r[1])
            if x == 3:
                return min(l[0] + r[0], l[0] + r[1], l[1] + r[0]), l[1] + r[1]
            if x == 4:
                return min(l[0] + r[0], l[1] + r[1]), min(l[0] + r[1], l[1] + r[0])
            return min(l[1], r[1]), min(l[0], r[0])

        return dfs(root)[int(result)]
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
    public int minimumFlips(TreeNode root, boolean result) {
        return dfs(root)[result ? 1 : 0];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1 << 30, 1 << 30};
        }
        int x = root.val;
        if (x < 2) {
            return new int[] {x, x ^ 1};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int a = 0, b = 0;
        if (x == 2) {
            a = l[0] + r[0];
            b = Math.min(l[0] + r[1], Math.min(l[1] + r[0], l[1] + r[1]));
        } else if (x == 3) {
            a = Math.min(l[0] + r[0], Math.min(l[0] + r[1], l[1] + r[0]));
            b = l[1] + r[1];
        } else if (x == 4) {
            a = Math.min(l[0] + r[0], l[1] + r[1]);
            b = Math.min(l[0] + r[1], l[1] + r[0]);
        } else {
            a = Math.min(l[1], r[1]);
            b = Math.min(l[0], r[0]);
        }
        return new int[] {a, b};
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
    int minimumFlips(TreeNode* root, bool result) {
        function<pair<int, int>(TreeNode*)> dfs = [&](TreeNode* root) -> pair<int, int> {
            if (!root) {
                return {1 << 30, 1 << 30};
            }
            int x = root->val;
            if (x < 2) {
                return {x, x ^ 1};
            }
            auto [l0, l1] = dfs(root->left);
            auto [r0, r1] = dfs(root->right);
            int a = 0, b = 0;
            if (x == 2) {
                a = l0 + r0;
                b = min({l0 + r1, l1 + r0, l1 + r1});
            } else if (x == 3) {
                a = min({l0 + r0, l0 + r1, l1 + r0});
                b = l1 + r1;
            } else if (x == 4) {
                a = min(l0 + r0, l1 + r1);
                b = min(l0 + r1, l1 + r0);
            } else {
                a = min(l1, r1);
                b = min(l0, r0);
            }
            return {a, b};
        };
        auto [a, b] = dfs(root);
        return result ? b : a;
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
func minimumFlips(root *TreeNode, result bool) int {
	var dfs func(*TreeNode) (int, int)
	dfs = func(root *TreeNode) (int, int) {
		if root == nil {
			return 1 << 30, 1 << 30
		}
		x := root.Val
		if x < 2 {
			return x, x ^ 1
		}
		l0, l1 := dfs(root.Left)
		r0, r1 := dfs(root.Right)
		var a, b int
		if x == 2 {
			a = l0 + r0
			b = min(l0+r1, min(l1+r0, l1+r1))
		} else if x == 3 {
			a = min(l0+r0, min(l0+r1, l1+r0))
			b = l1 + r1
		} else if x == 4 {
			a = min(l0+r0, l1+r1)
			b = min(l0+r1, l1+r0)
		} else {
			a = min(l1, r1)
			b = min(l0, r0)
		}
		return a, b
	}
	a, b := dfs(root)
	if result {
		return b
	}
	return a
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

function minimumFlips(root: TreeNode | null, result: boolean): number {
    const dfs = (root: TreeNode | null): [number, number] => {
        if (!root) {
            return [1 << 30, 1 << 30];
        }
        const x = root.val;
        if (x < 2) {
            return [x, x ^ 1];
        }
        const [l0, l1] = dfs(root.left);
        const [r0, r1] = dfs(root.right);
        if (x === 2) {
            return [l0 + r0, Math.min(l0 + r1, l1 + r0, l1 + r1)];
        }
        if (x === 3) {
            return [Math.min(l0 + r0, l0 + r1, l1 + r0), l1 + r1];
        }
        if (x === 4) {
            return [Math.min(l0 + r0, l1 + r1), Math.min(l0 + r1, l1 + r0)];
        }
        return [Math.min(l1, r1), Math.min(l0, r0)];
    };
    return dfs(root)[result ? 1 : 0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
