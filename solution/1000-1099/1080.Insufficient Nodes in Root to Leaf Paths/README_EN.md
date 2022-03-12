# [1080. Insufficient Nodes in Root to Leaf Paths](https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths)

[中文文档](/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and an integer <code>limit</code>, delete all <strong>insufficient nodes</strong> in the tree simultaneously, and return <em>the root of the resulting binary tree</em>.</p>

<p>A node is <strong>insufficient</strong> if every root to <strong>leaf</strong> path intersecting this node has a sum strictly less than <code>limit</code>.</p>

<p>A <strong>leaf</strong> is a node with no children.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-11.png" style="width: 500px; height: 207px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
<strong>Output:</strong> [1,2,3,4,null,null,7,8,9,null,14]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-3.png" style="width: 400px; height: 274px;" />
<pre>
<strong>Input:</strong> root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
<strong>Output:</strong> [5,4,8,11,null,17,4,7,null,null,null,5]
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/screen-shot-2019-06-11-at-83301-pm.png" style="width: 250px; height: 199px;" />
<pre>
<strong>Input:</strong> root = [1,2,-3,-5,null,4,null], limit = -1
<strong>Output:</strong> [1,null,-3,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 5000]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= limit &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sufficientSubset(self, root: TreeNode, limit: int) -> TreeNode:
        if root is None:
            return None

        limit -= root.val
        if root.left is None and root.right is None:
            return None if limit > 0 else root

        root.left = self.sufficientSubset(root.left, limit)
        root.right = self.sufficientSubset(root.right, limit)

        if root.left is None and root.right is None:
            return None
        return root
```

### **Java**

```java
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return null;
        }
        limit -= root.val;
        if (root.left == null && root.right == null) {
            return limit > 0 ? null : root;
        }
        root.left = sufficientSubset(root.left, limit);
        root.right = sufficientSubset(root.right, limit);
        return root.left == null && root.right == null ? null : root;
    }
}
```

### **Go**

```go
func sufficientSubset(root *TreeNode, limit int) *TreeNode {
	if root == nil {
		return nil
	}

	limit -= root.Val
	if root.Left == nil && root.Right == nil {
		if limit > 0 {
			return nil
		}
		return root
	}

	root.Left = sufficientSubset(root.Left, limit)
	root.Right = sufficientSubset(root.Right, limit)

	if root.Left == nil && root.Right == nil {
		return nil
	}
	return root
}
```

### **C++**

```cpp
class Solution {
public:
    TreeNode* sufficientSubset(TreeNode* root, int limit) {
        if (root == nullptr) return nullptr;

        limit -= root->val;
        if (root->left == nullptr && root->right == nullptr)
            return limit > 0 ? nullptr : root;

        root->left = sufficientSubset(root->left, limit);
        root->right = sufficientSubset(root->right, limit);

        if (root->left == nullptr && root->right == nullptr)
            return nullptr;
        return root;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
