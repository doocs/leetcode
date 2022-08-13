# [1325. Delete Leaves With a Given Value](https://leetcode.com/problems/delete-leaves-with-a-given-value)

[中文文档](/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/README.md)

## Description

<p>Given a binary tree <code>root</code> and an integer <code>target</code>, delete all the <strong>leaf nodes</strong> with value <code>target</code>.</p>

<p>Note that once you delete a leaf node with value <code>target</code><strong>, </strong>if its parent node becomes a leaf node and has the value <code>target</code>, it should also be deleted (you need to continue doing that until you cannot).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_1_1684.png" style="width: 500px; height: 112px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3,2,null,2,4], target = 2
<strong>Output:</strong> [1,null,3,null,4]
<strong>Explanation:</strong> Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_2_1684.png" style="width: 400px; height: 154px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,3,3,3,2], target = 3
<strong>Output:</strong> [1,3,null,null,2]
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_3_1684.png" style="width: 500px; height: 166px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [1,2,null,2,null,2], target = 2
<strong>Output:</strong> [1]
<strong>Explanation:</strong> Leaf nodes in green with value (target = 2) are removed at each step.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 3000]</code>.</li>
	<li><code>1 &lt;= Node.val, target &lt;= 1000</code></li>
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
    def removeLeafNodes(
        self, root: Optional[TreeNode], target: int
    ) -> Optional[TreeNode]:
        def dfs(root, prev):
            if root is None:
                return
            dfs(root.left, root)
            dfs(root.right, root)
            if root.left is None and root.right is None and root.val == target:
                if prev.left == root:
                    prev.left = None
                else:
                    prev.right = None

        p = TreeNode(val=0, left=root)
        dfs(root, p)
        return p.left
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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode p = new TreeNode(0, root, null);
        dfs(root, p, target);
        return p.left;
    }

    private void dfs(TreeNode root, TreeNode prev, int target) {
        if (root == null) {
            return;
        }
        dfs(root.left, root, target);
        dfs(root.right, root, target);
        if (root.left == null && root.right == null && root.val == target) {
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
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
    TreeNode* removeLeafNodes(TreeNode* root, int target) {
        TreeNode* p = new TreeNode(0, root, nullptr);
        dfs(root, p, target);
        return p->left;
    }

    void dfs(TreeNode* root, TreeNode* prev, int target) {
        if (!root) return;
        dfs(root->left, root, target);
        dfs(root->right, root, target);
        if (!root->left && !root->right && root->val == target) {
            if (prev->left == root)
                prev->left = nullptr;
            else
                prev->right = nullptr;
        }
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
func removeLeafNodes(root *TreeNode, target int) *TreeNode {
	p := &TreeNode{0, root, nil}
	var dfs func(root, prev *TreeNode)
	dfs = func(root, prev *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left, root)
		dfs(root.Right, root)
		if root.Left == nil && root.Right == nil && root.Val == target {
			if prev.Left == root {
				prev.Left = nil
			} else {
				prev.Right = nil
			}
		}
	}
	dfs(root, p)
	return p.Left
}
```

### **...**

```

```

<!-- tabs:end -->
