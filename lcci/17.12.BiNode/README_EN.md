# [17.12. BiNode](https://leetcode.cn/problems/binode-lcci)

[中文文档](/lcci/17.12.BiNode/README.md)

## Description

<p>The data structure&nbsp;<code>TreeNode</code>&nbsp;is used for binary tree, but it can also used to represent a single linked list (where left is null, and right is the next node in the list). Implement a method to convert a binary search tree (implemented with <code>TreeNode</code>) into a single&nbsp;linked list. The values should be kept in order and the operation should be performed in place (that is, on the original data structure).</p>

<p>Return the head node of the linked list after converting.</p>

<p><b>Note:&nbsp;</b>This problem is slightly different from the original one in the book.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong> [4,2,5,1,3,null,6,0]

<strong>Output: </strong> [0,null,1,null,2,null,3,null,4,null,5,null,6]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li>The number of nodes will not exceed&nbsp;100000.</li>
</ul>

## Solutions

Similar to [897. Increasing Order Search Tree](/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/README_EN.md).

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def convertBiNode(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            if root is None:
                return
            nonlocal prev
            dfs(root.left)
            prev.right = root
            root.left = None
            prev = root
            dfs(root.right)

        dummy = TreeNode(val=0, right=root)
        prev = dummy
        dfs(root)
        return dummy.right
```

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private TreeNode prev;

    public TreeNode convertBiNode(TreeNode root) {
        TreeNode dummy = new TreeNode(0, null, root);
        prev = dummy;
        dfs(root);
        return dummy.right;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        prev.right = root;
        root.left = null;
        prev = root;
        dfs(root.right);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* prev;

    TreeNode* convertBiNode(TreeNode* root) {
        TreeNode* dummy = new TreeNode(0, nullptr, root);
        prev = dummy;
        dfs(root);
        return dummy->right;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        prev->right = root;
        root->left = nullptr;
        prev = root;
        dfs(root->right);
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
func convertBiNode(root *TreeNode) *TreeNode {
	dummy := &TreeNode{Val: 0, Right: root}
	prev := dummy
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		prev.Right = root
		root.Left = nil
		prev = root
		dfs(root.Right)
	}
	dfs(root)
	return dummy.Right
}
```

### **...**

```

```

<!-- tabs:end -->
