# [965. Univalued Binary Tree](https://leetcode.com/problems/univalued-binary-tree)

[中文文档](/solution/0900-0999/0965.Univalued%20Binary%20Tree/README.md)

## Description

<p>A binary tree is <strong>uni-valued</strong> if every node in the tree has the same value.</p>

<p>Given the <code>root</code> of a binary tree, return <code>true</code><em> if the given tree is <strong>uni-valued</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0965.Univalued%20Binary%20Tree/images/unival_bst_1.png" style="width: 265px; height: 172px;" />
<pre>
<strong>Input:</strong> root = [1,1,1,1,1,null,1]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0965.Univalued%20Binary%20Tree/images/unival_bst_2.png" style="width: 198px; height: 169px;" />
<pre>
<strong>Input:</strong> root = [2,2,2,5,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 100]</code>.</li>
	<li><code>0 &lt;= Node.val &lt; 100</code></li>
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
    def isUnivalTree(self, root: TreeNode) -> bool:
        def dfs(root):
            if root is None:
                return True
            if root.val != self.val:
                return False
            return dfs(root.left) and dfs(root.right)

        self.val = root.val
        return dfs(root)
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
    private int val;

    public boolean isUnivalTree(TreeNode root) {
        val = root.val;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return dfs(root.left) && dfs(root.right);
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
    int val;

    bool isUnivalTree(TreeNode* root) {
        val = root->val;
        return dfs(root);
    }

    bool dfs(TreeNode* root) {
        if (root == nullptr) return true;
        if (root->val != val) return false;
        return dfs(root->left) && dfs(root->right);
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
func isUnivalTree(root *TreeNode) bool {
	return dfs(root, root.Val)
}

func dfs(root *TreeNode, val int) bool {
	if root == nil {
		return true
	}
	if root.Val != val {
		return false
	}
	return dfs(root.Left, val) && dfs(root.Right, val)
}
```

### **...**

```

```

<!-- tabs:end -->
