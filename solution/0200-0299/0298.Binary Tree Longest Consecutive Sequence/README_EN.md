# [298. Binary Tree Longest Consecutive Sequence](https://leetcode.com/problems/binary-tree-longest-consecutive-sequence)

[中文文档](/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the length of the longest consecutive sequence path</em>.</p>

<p>The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/images/consec1-1-tree.jpg" style="width: 322px; height: 421px;" />
<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,null,null,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Longest consecutive sequence path is 3-4-5, so return 3.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/images/consec1-2-tree.jpg" style="width: 262px; height: 421px;" />
<pre>
<strong>Input:</strong> root = [2,null,3,2,null,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 3 * 10<sup>4</sup>]</code>.</li>
	<li><code>-3 * 10<sup>4</sup> &lt;= Node.val &lt;= 3 * 10<sup>4</sup></code></li>
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
    def longestConsecutive(self, root: TreeNode) -> int:
        def dfs(root, p, t):
            nonlocal ans
            if root is None:
                return
            t = t + 1 if p is not None and p.val + 1 == root.val else 1
            ans = max(ans, t)
            dfs(root.left, root, t)
            dfs(root.right, root, t)

        ans = 1
        dfs(root, None, 1)
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

    public int longestConsecutive(TreeNode root) {
        ans = 1;
        dfs(root, null, 1);
        return ans;
    }

    private void dfs(TreeNode root, TreeNode p, int t) {
        if (root == null) {
            return;
        }
        t = p != null && p.val + 1 == root.val ? t + 1 : 1;
        ans = Math.max(ans, t);
        dfs(root.left, root, t);
        dfs(root.right, root, t);
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

    int longestConsecutive(TreeNode* root) {
        ans = 1;
        dfs(root, nullptr, 1);
        return ans;
    }

    void dfs(TreeNode* root, TreeNode* p, int t) {
        if (!root) return;
        t = p != nullptr && p->val + 1 == root->val ? t + 1 : 1;
        ans = max(ans, t);
        dfs(root->left, root, t);
        dfs(root->right, root, t);
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
func longestConsecutive(root *TreeNode) int {
	ans := 1
	var dfs func(root, p *TreeNode, t int)
	dfs = func(root, p *TreeNode, t int) {
		if root == nil {
			return
		}
		if p != nil && p.Val+1 == root.Val {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
		dfs(root.Left, root, t)
		dfs(root.Right, root, t)
	}
	dfs(root, nil, 1)
	return ans
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
