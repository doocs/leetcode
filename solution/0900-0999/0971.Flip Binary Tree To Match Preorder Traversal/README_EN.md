---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

# [971. Flip Binary Tree To Match Preorder Traversal](https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal)

[中文文档](/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/README.md)

## Description

<p>You are given the <code>root</code> of a binary tree with <code>n</code> nodes, where each node is uniquely assigned a value from <code>1</code> to <code>n</code>. You are also given a sequence of <code>n</code> values <code>voyage</code>, which is the <strong>desired</strong> <a href="https://en.wikipedia.org/wiki/Tree_traversal#Pre-order" target="_blank"><strong>pre-order traversal</strong></a> of the binary tree.</p>

<p>Any node in the binary tree can be <strong>flipped</strong> by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/fliptree.jpg" style="width: 400px; height: 187px;" />
<p>Flip the <strong>smallest</strong> number of nodes so that the <strong>pre-order traversal</strong> of the tree <strong>matches</strong> <code>voyage</code>.</p>

<p>Return <em>a list of the values of all <strong>flipped</strong> nodes. You may return the answer in <strong>any order</strong>. If it is <strong>impossible</strong> to flip the nodes in the tree to make the pre-order traversal match </em><code>voyage</code><em>, return the list </em><code>[-1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/1219-01.png" style="width: 150px; height: 205px;" />
<pre>
<strong>Input:</strong> root = [1,2], voyage = [2,1]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> It is impossible to flip the nodes such that the pre-order traversal matches voyage.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/1219-02.png" style="width: 150px; height: 142px;" />
<pre>
<strong>Input:</strong> root = [1,2,3], voyage = [1,3,2]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0971.Flip%20Binary%20Tree%20To%20Match%20Preorder%20Traversal/images/1219-02.png" style="width: 150px; height: 142px;" />
<pre>
<strong>Input:</strong> root = [1,2,3], voyage = [1,2,3]
<strong>Output:</strong> []
<strong>Explanation:</strong> The tree&#39;s pre-order traversal already matches voyage, so no nodes need to be flipped.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>n == voyage.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= Node.val, voyage[i] &lt;= n</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li>All the values in <code>voyage</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

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
    def flipMatchVoyage(self, root: Optional[TreeNode], voyage: List[int]) -> List[int]:
        def dfs(root):
            nonlocal i, ok
            if root is None or not ok:
                return
            if root.val != voyage[i]:
                ok = False
                return
            i += 1
            if root.left is None or root.left.val == voyage[i]:
                dfs(root.left)
                dfs(root.right)
            else:
                ans.append(root.val)
                dfs(root.right)
                dfs(root.left)

        ans = []
        i = 0
        ok = True
        dfs(root)
        return ans if ok else [-1]
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
    private int i;
    private boolean ok;
    private int[] voyage;
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        ok = true;
        dfs(root);
        return ok ? ans : List.of(-1);
    }

    private void dfs(TreeNode root) {
        if (root == null || !ok) {
            return;
        }
        if (root.val != voyage[i]) {
            ok = false;
            return;
        }
        ++i;
        if (root.left == null || root.left.val == voyage[i]) {
            dfs(root.left);
            dfs(root.right);
        } else {
            ans.add(root.val);
            dfs(root.right);
            dfs(root.left);
        }
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
    vector<int> flipMatchVoyage(TreeNode* root, vector<int>& voyage) {
        bool ok = true;
        int i = 0;
        vector<int> ans;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root || !ok) {
                return;
            }
            if (root->val != voyage[i]) {
                ok = false;
                return;
            }
            ++i;
            if (!root->left || root->left->val == voyage[i]) {
                dfs(root->left);
                dfs(root->right);
            } else {
                ans.push_back(root->val);
                dfs(root->right);
                dfs(root->left);
            }
        };
        dfs(root);
        return ok ? ans : vector<int>{-1};
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
func flipMatchVoyage(root *TreeNode, voyage []int) []int {
	i := 0
	ok := true
	ans := []int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil || !ok {
			return
		}
		if root.Val != voyage[i] {
			ok = false
			return
		}
		i++
		if root.Left == nil || root.Left.Val == voyage[i] {
			dfs(root.Left)
			dfs(root.Right)
		} else {
			ans = append(ans, root.Val)
			dfs(root.Right)
			dfs(root.Left)
		}
	}
	dfs(root)
	if !ok {
		return []int{-1}
	}
	return ans
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

function flipMatchVoyage(root: TreeNode | null, voyage: number[]): number[] {
    let ok = true;
    let i = 0;
    const ans: number[] = [];
    const dfs = (root: TreeNode | null): void => {
        if (!root || !ok) {
            return;
        }
        if (root.val !== voyage[i++]) {
            ok = false;
            return;
        }
        if (!root.left || root.left.val === voyage[i]) {
            dfs(root.left);
            dfs(root.right);
        } else {
            ans.push(root.val);
            dfs(root.right);
            dfs(root.left);
        }
    };
    dfs(root);
    return ok ? ans : [-1];
}
```

<!-- tabs:end -->

<!-- end -->
