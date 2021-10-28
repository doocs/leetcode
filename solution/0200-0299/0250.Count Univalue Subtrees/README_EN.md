# [250. Count Univalue Subtrees](https://leetcode.com/problems/count-univalue-subtrees)

[中文文档](/solution/0200-0299/0250.Count%20Univalue%20Subtrees/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return the number of <strong>uni-value</strong> subtrees.</p>

<p>A <strong>uni-value subtree</strong> means all nodes of the subtree have the same value.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0250.Count%20Univalue%20Subtrees/images/unival_e1.jpg" style="width: 450px; height: 258px;" />
<pre>
<strong>Input:</strong> root = [5,1,5,5,5,null,5]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [5,5,5,5,5,null,5]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The numbrt of the node in the tree will be in the range <code>[0, 1000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## Solutions

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
    def countUnivalSubtrees(self, root: TreeNode) -> int:
        if root is None:
            return 0
        cnt = 0

        def dfs(root):
            nonlocal cnt
            if root.left is None and root.right is None:
                cnt += 1
                return True
            res = True
            if root.left:
                # exec dfs(root.left) first
                res = dfs(root.left) and res and root.val == root.left.val
            if root.right:
                # exec dfs(root.right) first
                res = dfs(root.right) and res and root.val == root.right.val
            cnt += res
            return res

        dfs(root)
        return cnt
```

### **Java**

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
    private int cnt;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        cnt = 0;
        dfs(root);
        return cnt;
    }

    private boolean dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            ++cnt;
            return true;
        }
        boolean res = true;
        if (root.left != null) {
            // exec dfs(root.left) first
            res = dfs(root.left) && res && root.val == root.left.val;
        }
        if (root.right != null) {
            // exec dfs(root.right) first
            res = dfs(root.right) && res && root.val == root.right.val;
        }
        if (res) {
            ++cnt;
        }
        return res;
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
    int cnt;

    int countUnivalSubtrees(TreeNode* root) {
        if (!root) return 0;
        cnt = 0;
        dfs(root);
        return cnt;
    }

    bool dfs(TreeNode* root) {
        if (!root->left && !root->right)
        {
            ++cnt;
            return true;
        }
        bool res = true;
        if (root->left) res = dfs(root->left) && res && root->val == root->left->val;
        if (root->right) res = dfs(root->right) && res && root->val == root->right->val;
        cnt += res;
        return res;

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
var cnt int

func countUnivalSubtrees(root *TreeNode) int {
	if root == nil {
		return 0
	}
	cnt = 0
	dfs(root)
	return cnt
}

func dfs(root *TreeNode) bool {
	if root.Left == nil && root.Right == nil {
		cnt++
		return true
	}
	res := true
	if root.Left != nil {
		res = dfs(root.Left) && res && root.Val == root.Left.Val
	}
	if root.Right != nil {
		res = dfs(root.Right) && res && root.Val == root.Right.Val
	}
	if res {
		cnt++
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
