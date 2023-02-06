# [776. Split BST](https://leetcode.com/problems/split-bst)

[中文文档](/solution/0700-0799/0776.Split%20BST/README.md)

## Description

<p>Given the <code>root</code> of a binary search tree (BST) and an integer <code>target</code>, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value. It Is not necessarily the case that the tree contains a node with the value <code>target</code>.</p>

<p>Additionally, most of the structure of the original tree should remain. Formally, for any child <code>c</code> with parent <code>p</code> in the original tree, if they are both in the same subtree after the split, then node <code>c</code> should still have the parent <code>p</code>.</p>

<p>Return <em>an array of the two roots of the two subtrees</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0776.Split%20BST/images/split-tree.jpg" style="width: 600px; height: 193px;" />
<pre>
<strong>Input:</strong> root = [4,2,6,1,3,5,7], target = 2
<strong>Output:</strong> [[2,1],[4,3,6,null,null,5,7]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1], target = 1
<strong>Output:</strong> [[1],[]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 50]</code>.</li>
	<li><code>0 &lt;= Node.val, target &lt;= 1000</code></li>
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
    def splitBST(self, root: Optional[TreeNode], target: int) -> List[Optional[TreeNode]]:
        def dfs(root):
            if root is None:
                return [None, None]
            if root.val <= target:
                l, r = dfs(root.right)
                root.right = l
                return [root, r]
            else:
                l, r = dfs(root.left)
                root.left = r
                return [l, root]

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
    private int t;

    public TreeNode[] splitBST(TreeNode root, int target) {
        t = target;
        return dfs(root);
    }

    private TreeNode[] dfs(TreeNode root) {
        if (root == null) {
            return new TreeNode[] {null, null};
        }
        if (root.val <= t) {
            TreeNode[] ans = dfs(root.right);
            root.right = ans[0];
            ans[0] = root;
            return ans;
        } else {
            TreeNode[] ans = dfs(root.left);
            root.left = ans[1];
            ans[1] = root;
            return ans;
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
    int t;

    vector<TreeNode*> splitBST(TreeNode* root, int target) {
        t = target;
        return dfs(root);
    }

    vector<TreeNode*> dfs(TreeNode* root) {
        if (!root) return {nullptr, nullptr};
        if (root->val <= t) {
            auto ans = dfs(root->right);
            root->right = ans[0];
            ans[0] = root;
            return ans;
        } else {
            auto ans = dfs(root->left);
            root->left = ans[1];
            ans[1] = root;
            return ans;
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
func splitBST(root *TreeNode, target int) []*TreeNode {
	if root == nil {
		return []*TreeNode{nil, nil}
	}
	if root.Val <= target {
		ans := splitBST(root.Right, target)
		root.Right = ans[0]
		ans[0] = root
		return ans
	} else {
		ans := splitBST(root.Left, target)
		root.Left = ans[1]
		ans[1] = root
		return ans
	}
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} target
 * @return {TreeNode[]}
 */
var splitBST = function (root, target) {
    let ans = [null, null];
    if (!root) {
        return ans;
    }
    if (root.val <= target) {
        ans = splitBST(root.right, target);
        root.right = ans[0];
        ans[0] = root;
    } else {
        ans = splitBST(root.left, target);
        root.left = ans[1];
        ans[1] = root;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
