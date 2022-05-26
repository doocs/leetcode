# [250. Count Univalue Subtrees](https://leetcode.com/problems/count-univalue-subtrees)

[中文文档](/solution/0200-0299/0250.Count%20Univalue%20Subtrees/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return the number of <strong>uni-value</strong> subtrees.</p>

<p>A <strong>uni-value subtree</strong> means all nodes of the subtree have the same value.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0250.Count%20Univalue%20Subtrees/images/unival_e1.jpg" style="width: 450px; height: 258px;" />
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
	<li>The number of the node in the tree will be in the range <code>[0, 1000]</code>.</li>
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
    def countUnivalSubtrees(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return True
            left, right = dfs(root.left), dfs(root.right)
            t = True
            if root.left and root.left.val != root.val:
                t = False
            if root.right and root.right.val != root.val:
                t = False
            nonlocal ans
            if left and t and right:
                ans += 1
            return left and t and right

        ans = 0
        dfs(root)
        return ans
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
    private int ans;

    public int countUnivalSubtrees(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        boolean t = true;
        if (root.left != null && root.left.val != root.val) {
            t = false;
        }
        if (root.right != null && root.right.val != root.val) {
            t = false;
        }
        if (left && t && right) {
            ++ans;
        }
        return left && t && right;
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
    int ans;

    int countUnivalSubtrees(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    bool dfs(TreeNode* root) {
        if (!root) return 1;
        bool left = dfs(root->left);
        bool right = dfs(root->right);
        bool t = 1;
        if (root->left && root->left->val != root->val) t = 0;
        if (root->right && root->right->val != root->val) t = 0;
        if (left && t && right) ++ans;
        return left && t && right;
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
func countUnivalSubtrees(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		left, right := dfs(root.Left), dfs(root.Right)
		t := true
		if root.Left != nil && root.Left.Val != root.Val {
			t = false
		}
		if root.Right != nil && root.Right.Val != root.Val {
			t = false
		}
		if left && t && right {
			ans++
		}
		return left && t && right
	}
	dfs(root)
	return ans
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
 * @return {number}
 */
var countUnivalSubtrees = function (root) {
    let ans = 0;
    let dfs = function (root) {
        if (!root) {
            return true;
        }
        const left = dfs(root.left),
            right = dfs(root.right);
        let t = true;
        if (root.left && root.left.val != root.val) {
            t = false;
        }
        if (root.right && root.right.val != root.val) {
            t = false;
        }
        if (left && t && right) {
            ++ans;
        }
        return left && t && right;
    };
    dfs(root);
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
