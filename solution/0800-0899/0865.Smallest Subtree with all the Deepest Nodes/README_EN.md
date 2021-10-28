# [865. Smallest Subtree with all the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes)

[中文文档](/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, the depth of each node is <strong>the shortest distance to the root</strong>.</p>

<p>Return <em>the smallest subtree</em> such that it contains <strong>all the deepest nodes</strong> in the original tree.</p>

<p>A node is called <strong>the&nbsp;deepest</strong> if it has the largest depth possible among&nbsp;any node in the entire tree.</p>

<p>The <strong>subtree</strong> of a node is tree consisting of that node, plus the set of all descendants of that node.</p>

<p><strong>Note:</strong> This question is the same as 1123: <a href="https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/" target="_blank">https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/</a></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/images/sketch1.png" style="width: 600px; height: 510px;" />

<pre>

<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4]

<strong>Output:</strong> [2,7,4]

<strong>Explanation:</strong> We return the node with value 2, colored in yellow in the diagram.

The nodes coloured in blue are the deepest nodes of the tree.

Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> root = [1]

<strong>Output:</strong> [1]

<strong>Explanation:</strong> The root is the deepest node in the tree.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> root = [0,1,3,null,2]

<strong>Output:</strong> [2]

<strong>Explanation:</strong> The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree will be in the range <code>[1, 500]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 500</code></li>
	<li>The values of the nodes in the tree&nbsp;are <strong>unique</strong>.</li>
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
    def subtreeWithAllDeepest(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            if not root:
                return None, 0
            l, d1 = dfs(root.left)
            r, d2 = dfs(root.right)
            if d1 > d2:
                return l, d1 + 1
            if d1 < d2:
                return r, d2 + 1
            return root, d1 + 1

        return dfs(root)[0]
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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root)[0];
    }

    private TreeNode[] dfs(TreeNode root) {
        if (root == null) {
            return new TreeNode[]{null, new TreeNode(0)};
        }
        TreeNode[] left = dfs(root.left);
        TreeNode[] right = dfs(root.right);
        int d1 = left[1].val, d2 = right[1].val;
        if (d1 > d2) {
            return new TreeNode[]{left[0], new TreeNode(d1 + 1)};
        }
        if (d1 < d2) {
            return new TreeNode[]{right[0], new TreeNode(d2 + 1)};
        }
        return new TreeNode[]{root, new TreeNode(d1 + 1)};
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
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        return dfs(root).first;
    }

    pair<TreeNode*, int> dfs(TreeNode* root) {
        if (!root) return {nullptr, 0};
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        int d1 = left.second, d2 = right.second;
        if (d1 > d2) return {left.first, d1 + 1};
        if (d1 < d2) return {right.first, d2 + 1};
        return {root, d1 + 1};
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
type Result struct {
	Node  *TreeNode
	Depth int
}

func subtreeWithAllDeepest(root *TreeNode) *TreeNode {
	return dfs(root).Node
}

func dfs(root *TreeNode) Result {
	if root == nil {
		return Result{
			nil, 0,
		}
	}
	left, right := dfs(root.Left), dfs(root.Right)
	d1, d2 := left.Depth, right.Depth
	if d1 > d2 {
		return Result{left.Node, d1 + 1}
	}
	if d1 < d2 {
		return Result{right.Node, d2 + 1}
	}
	return Result{root, d1 + 1}
}
```

### **...**

```

```

<!-- tabs:end -->
