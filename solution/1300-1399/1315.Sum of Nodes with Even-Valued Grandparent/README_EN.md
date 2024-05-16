---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/README_EN.md
rating: 1426
source: Biweekly Contest 17 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Binary Tree
---

# [1315. Sum of Nodes with Even-Valued Grandparent](https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent)

[中文文档](/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the sum of values of nodes with an <strong>even-valued grandparent</strong></em>. If there are no nodes with an <strong>even-valued grandparent</strong>, return <code>0</code>.</p>

<p>A <strong>grandparent</strong> of a node is the parent of its parent if it exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/images/even1-tree.jpg" style="width: 504px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
<strong>Output:</strong> 18
<strong>Explanation:</strong> The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/images/even2-tree.jpg" style="width: 64px; height: 65px;" />
<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: DFS

We design a function $dfs(root, x)$, which represents the sum of the values of the nodes that meet the conditions in the subtree with $root$ as the root node and $x$ as the value of the parent node of $root$. The answer is $dfs(root, 1)$.

The execution process of the function $dfs(root, x)$ is as follows:

-   If $root$ is null, return $0$.
-   Otherwise, we recursively calculate the answers of the left and right subtrees of $root$, that is, $dfs(root.left, root.val)$ and $dfs(root.right, root.val)$, and add them to the answer. If $x$ is even, we check whether the left and right children of $root$ exist. If they exist, we add their values to the answer.
-   Finally, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumEvenGrandparent(self, root: TreeNode) -> int:
        def dfs(root: TreeNode, x: int) -> int:
            if root is None:
                return 0
            ans = dfs(root.left, root.val) + dfs(root.right, root.val)
            if x % 2 == 0:
                if root.left:
                    ans += root.left.val
                if root.right:
                    ans += root.right.val
            return ans

        return dfs(root, 1)
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
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, 1);
    }

    private int dfs(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
        int ans = dfs(root.left, root.val) + dfs(root.right, root.val);
        if (x % 2 == 0) {
            if (root.left != null) {
                ans += root.left.val;
            }
            if (root.right != null) {
                ans += root.right.val;
            }
        }
        return ans;
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
    int sumEvenGrandparent(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int x) {
            if (!root) {
                return 0;
            }
            int ans = dfs(root->left, root->val) + dfs(root->right, root->val);
            if (x % 2 == 0) {
                if (root->left) {
                    ans += root->left->val;
                }
                if (root->right) {
                    ans += root->right->val;
                }
            }
            return ans;
        };
        return dfs(root, 1);
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
func sumEvenGrandparent(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, x int) int {
		if root == nil {
			return 0
		}
		ans := dfs(root.Left, root.Val) + dfs(root.Right, root.Val)
		if x%2 == 0 {
			if root.Left != nil {
				ans += root.Left.Val
			}
			if root.Right != nil {
				ans += root.Right.Val
			}
		}
		return ans
	}
	return dfs(root, 1)
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

function sumEvenGrandparent(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, x: number): number => {
        if (!root) {
            return 0;
        }
        const { val, left, right } = root;
        let ans = dfs(left, val) + dfs(right, val);
        if (x % 2 === 0) {
            ans += left?.val ?? 0;
            ans += right?.val ?? 0;
        }
        return ans;
    };
    return dfs(root, 1);
}
```

<!-- tabs:end -->

<!-- end -->
