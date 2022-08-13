# [337. House Robber III](https://leetcode.com/problems/house-robber-iii)

[中文文档](/solution/0300-0399/0337.House%20Robber%20III/README.md)

## Description

<p>The thief has found himself a new place for his thievery again. There is only one entrance to this area, called <code>root</code>.</p>

<p>Besides the <code>root</code>, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if <strong>two directly-linked houses were broken into on the same night</strong>.</p>

<p>Given the <code>root</code> of the binary tree, return <em>the maximum amount of money the thief can rob <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0337.House%20Robber%20III/images/rob1-tree.jpg" style="width: 277px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,2,3,null,3,null,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0337.House%20Robber%20III/images/rob2-tree.jpg" style="width: 357px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,3,null,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 4 + 5 = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
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
    def rob(self, root: TreeNode) -> int:
        @cache
        def dfs(root):
            if root is None:
                return 0
            if root.left is None and root.right is None:
                return root.val
            a = dfs(root.left) + dfs(root.right)
            b = root.val
            if root.left:
                b += dfs(root.left.left) + dfs(root.left.right)
            if root.right:
                b += dfs(root.right.left) + dfs(root.right.right)
            return max(a, b)

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
    private Map<TreeNode, Integer> memo;

    public int rob(TreeNode root) {
        memo = new HashMap<>();
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int a = dfs(root.left) + dfs(root.right);
        int b = root.val;
        if (root.left != null) {
            b += dfs(root.left.left) + dfs(root.left.right);
        }
        if (root.right != null) {
            b += dfs(root.right.left) + dfs(root.right.right);
        }
        int res = Math.max(a, b);
        memo.put(root, res);
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
    unordered_map<TreeNode*, int> memo;

    int rob(TreeNode* root) {
        return dfs(root);
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        if (memo.count(root)) return memo[root];
        int a = dfs(root->left) + dfs(root->right);
        int b = root->val;
        if (root->left) b += dfs(root->left->left) + dfs(root->left->right);
        if (root->right) b += dfs(root->right->left) + dfs(root->right->right);
        int res = max(a, b);
        memo[root] = res;
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
func rob(root *TreeNode) int {
	memo := make(map[*TreeNode]int)
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if _, ok := memo[root]; ok {
			return memo[root]
		}
		a := dfs(root.Left) + dfs(root.Right)
		b := root.Val
		if root.Left != nil {
			b += dfs(root.Left.Left) + dfs(root.Left.Right)
		}
		if root.Right != nil {
			b += dfs(root.Right.Left) + dfs(root.Right.Right)
		}
		res := max(a, b)
		memo[root] = res
		return res
	}
	return dfs(root)
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
