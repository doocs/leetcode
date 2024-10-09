---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/README_EN.md
tags:
    - Tree
    - Binary Search Tree
    - Binary Tree
---

<!-- problem:start -->

# [700. Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree)

[中文文档](/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a binary search tree (BST) and an integer <code>val</code>.</p>

<p>Find the node in the BST that the node&#39;s value equals <code>val</code> and return the subtree rooted with that node. If such a node does not exist, return <code>null</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/images/tree1.jpg" style="width: 422px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [4,2,7,1,3], val = 2
<strong>Output:</strong> [2,1,3]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0700.Search%20in%20a%20Binary%20Search%20Tree/images/tree2.jpg" style="width: 422px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [4,2,7,1,3], val = 5
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 5000]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>7</sup></code></li>
	<li><code>root</code> is a binary search tree.</li>
	<li><code>1 &lt;= val &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

We check if the current node is null or if the current node's value equals the target value. If so, we return the current node.

Otherwise, if the current node's value is greater than the target value, we recursively search the left subtree; otherwise, we recursively search the right subtree.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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
    def searchBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        if root is None or root.val == val:
            return root
        return (
            self.searchBST(root.left, val)
            if root.val > val
            else self.searchBST(root.right, val)
        )
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
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
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
    TreeNode* searchBST(TreeNode* root, int val) {
        if (!root || root->val == val) {
            return root;
        }
        return root->val > val ? searchBST(root->left, val) : searchBST(root->right, val);
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
 func searchBST(root *TreeNode, val int) *TreeNode {
    if root == nil || root.Val == val {
        return root
    }
    if root.Val > val {
        return searchBST(root.Left, val)
    }
    return searchBST(root.Right, val)
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

function searchBST(root: TreeNode | null, val: number): TreeNode | null {
    if (root === null || root.val === val) {
        return root;
    }
    return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
