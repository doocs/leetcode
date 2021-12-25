# [968. Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras)

[中文文档](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README.md)

## Description

<p>Given a binary tree, we install cameras on the nodes of the tree.&nbsp;</p>

<p>Each camera at&nbsp;a node can monitor <strong>its parent, itself, and its immediate children</strong>.</p>

<p>Calculate the minimum number of cameras needed to monitor all nodes of the tree.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_01.png" style="width: 138px; height: 163px;" />

<div>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[0,0,null,0,0]</span>

<strong>Output: </strong><span id="example-output-1">1</span>

<strong>Explanation: </strong>One camera is enough to monitor all nodes if placed as shown.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_02.png" style="width: 139px; height: 312px;" />

<pre>

<strong>Input: </strong><span id="example-input-2-1">[0,0,null,0,null,0,null,null,0]</span>

<strong>Output: </strong><span id="example-output-2">2

<strong>Explanation:</strong> At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.</span>

</pre>

<p><br />

<strong>Note:</strong></p>

<ol>
	<li>The number of nodes in the given tree will be in the range&nbsp;<code>[1, 1000]</code>.</li>
	<li><strong>Every</strong> node has value 0.</li>
</ol>

</div>

</div>

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
    def minCameraCover(self, root: TreeNode) -> int:
        def dfs(root):
            nonlocal ans
            if root is None:
                return 2
            left, right = dfs(root.left), dfs(root.right)
            if left == 0 or right == 0:
                ans += 1
                return 1
            return 2 if left == 1 or right == 1 else 0

        ans = 0
        return (dfs(root) == 0) + ans
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

    public int minCameraCover(TreeNode root) {
        ans = 0;
        return (dfs(root) == 0) ? ans + 1 : ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 2;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == 0 || right == 0) {
            ++ans;
            return 1;
        }
        if (left == 1 || right == 1) {
            return 2;
        }
        return 0;
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

    int minCameraCover(TreeNode* root) {
        ans = 0;
        if (dfs(root) == 0) return ans + 1;
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 2;
        int left = dfs(root->left), right = dfs(root->right);
        if (left == 0 || right == 0)
        {
            ++ans;
            return 1;
        }
        if (left == 1 || right == 1) return 2;
        return 0;
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
func minCameraCover(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 2
		}
		left, right := dfs(root.Left), dfs(root.Right)
		if left == 0 || right == 0 {
			ans++
			return 1
		}
		if left == 1 || right == 1 {
			return 2
		}
		return 0
	}
	if dfs(root) == 0 {
		return ans + 1
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
