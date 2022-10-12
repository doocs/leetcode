# [951. Flip Equivalent Binary Trees](https://leetcode.com/problems/flip-equivalent-binary-trees)

[中文文档](/solution/0900-0999/0951.Flip%20Equivalent%20Binary%20Trees/README.md)

## Description

<p>For a binary tree <strong>T</strong>, we can define a <strong>flip operation</strong> as follows: choose any node, and swap the left and right child subtrees.</p>

<p>A binary tree <strong>X</strong>&nbsp;is <em>flip equivalent</em> to a binary tree <strong>Y</strong> if and only if we can make <strong>X</strong> equal to <strong>Y</strong> after some number of flip operations.</p>

<p>Given the roots of two binary trees <code>root1</code> and <code>root2</code>, return <code>true</code> if the two trees are flip equivalent or <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="Flipped Trees Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0951.Flip%20Equivalent%20Binary%20Trees/images/tree_ex.png" style="width: 500px; height: 220px;" />
<pre>
<strong>Input:</strong> root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
<strong>Output:</strong> true
<strong>Explanation: </strong>We flipped at nodes with values 1, 3, and 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root1 = [], root2 = []
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root1 = [], root2 = [1]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in each tree is in the range <code>[0, 100]</code>.</li>
	<li>Each tree will have <strong>unique node values</strong> in the range <code>[0, 99]</code>.</li>
</ul>

## Solutions

DFS.

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
    def flipEquiv(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        def dfs(root1, root2):
            if root1 == root2 or (root1 is None and root2 is None):
                return True
            if root1 is None or root2 is None or root1.val != root2.val:
                return False
            return (dfs(root1.left, root2.left) and dfs(root1.right, root2.right)) or (
                dfs(root1.left, root2.right) and dfs(root1.right, root2.left)
            )

        return dfs(root1, root2)
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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == root2 || (root1 == null && root2 == null)) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return (dfs(root1.left, root2.left) && dfs(root1.right, root2.right))
            || (dfs(root1.left, root2.right) && dfs(root1.right, root2.left));
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
    bool flipEquiv(TreeNode* root1, TreeNode* root2) {
        return dfs(root1, root2);
    }

    bool dfs(TreeNode* root1, TreeNode* root2) {
        if (root1 == root2 || (!root1 && !root2)) return true;
        if (!root1 || !root2 || root1->val != root2->val) return false;
        return (dfs(root1->left, root2->left) && dfs(root1->right, root2->right)) || (dfs(root1->left, root2->right) && dfs(root1->right, root2->left));
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
func flipEquiv(root1 *TreeNode, root2 *TreeNode) bool {
	var dfs func(root1, root2 *TreeNode) bool
	dfs = func(root1, root2 *TreeNode) bool {
		if root1 == root2 || (root1 == nil && root2 == nil) {
			return true
		}
		if root1 == nil || root2 == nil || root1.Val != root2.Val {
			return false
		}
		return (dfs(root1.Left, root2.Left) && dfs(root1.Right, root2.Right)) || (dfs(root1.Left, root2.Right) && dfs(root1.Right, root2.Left))
	}
	return dfs(root1, root2)
}
```

### **...**

```

```

<!-- tabs:end -->
