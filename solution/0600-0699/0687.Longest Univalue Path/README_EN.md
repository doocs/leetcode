# [687. Longest Univalue Path](https://leetcode.com/problems/longest-univalue-path)

[中文文档](/solution/0600-0699/0687.Longest%20Univalue%20Path/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the length of the longest path, where each node in the path has the same value</em>. This path may or may not pass through the root.</p>

<p><strong>The length of the path</strong> between two nodes is represented by the number of edges between them.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0687.Longest%20Univalue%20Path/images/ex1.jpg" style="width: 571px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [5,4,5,1,1,5]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0687.Longest%20Univalue%20Path/images/ex2.jpg" style="width: 571px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,4,5,4,4,5]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li>The depth of the tree will not exceed <code>1000</code>.</li>
</ul>

## Solutions

Similar to problem [543. Diameter of Binary Tree](/solution/0500-0599/0543.Diameter%20of%20Binary%20Tree/README_EN.md).

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestUnivaluePath(self, root: TreeNode) -> int:
        res = 0

        def dfs(root):
            nonlocal res
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)

            l = r = 0
            if root.left and root.left.val == root.val:
                l = left + 1
            if root.right and root.right.val == root.val:
                r = right + 1
            res = max(res, l + r)
            return max(l, r)

        dfs(root)
        return res
```

### **Java**

```java
class Solution {
    public int longestUnivaluePath(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        left = root.left != null && root.left.val == root.val ? left + 1 : 0;
        right = root.right != null && root.right.val == root.val ? right + 1 : 0;
        res[0] = Math.max(res[0], left + right);
        return Math.max(left, right);
    }
}
```

### **C++**

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
    int res;
    int longestUnivaluePath(TreeNode* root) {
        res = 0;
        dfs(root);
        return res;
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        int left = dfs(root->left), right = dfs(root->right);
        left = root->left && root->left->val == root->val ? left + 1 : 0;
        right = root->right && root->right->val == root->val ? right + 1 : 0;
        res = max(res, left + right);
        return max(left, right);
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var res int

func longestUnivaluePath(root *TreeNode) int {
	res = 0
	dfs(root)
	return res
}

func dfs(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left, right := dfs(root.Left), dfs(root.Right)
	if root.Left != nil && root.Left.Val == root.Val {
		left++
	} else {
		left = 0
	}
	if root.Right != nil && root.Right.Val == root.Val {
		right++
	} else {
		right = 0
	}
	res = max(res, left+right)
	return max(left, right)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
