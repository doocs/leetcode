# [222. Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes)

[中文文档](/solution/0200-0299/0222.Count%20Complete%20Tree%20Nodes/README.md)

## Description

<p>Given the <code>root</code> of a <strong>complete</strong> binary tree, return the number of the nodes in the tree.</p>

<p>According to <strong><a href="http://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees" target="_blank">Wikipedia</a></strong>, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between <code>1</code> and <code>2<sup>h</sup></code> nodes inclusive at the last level <code>h</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0222.Count%20Complete%20Tree%20Nodes/images/complete.jpg" style="width: 372px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6]
<strong>Output:</strong> 6
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 5 * 10<sup>4</sup></code></li>
	<li>The tree is guaranteed to be <strong>complete</strong>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Traversing the tree to count the number of nodes in the tree is an easy solution but with <code>O(n)</code> complexity. Could you find a faster algorithm?

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
    def countNodes(self, root: TreeNode) -> int:
        def depth(root):
            res = 0
            while root:
                res += 1
                root = root.left
            return res

        if root is None:
            return 0
        left_depth = depth(root.left)
        right_depth = depth(root.right)
        if left_depth > right_depth:
            return (1 << right_depth) + self.countNodes(root.left)
        return (1 << left_depth) + self.countNodes(root.right)
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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth > rightDepth) {
            return (1 << rightDepth) + countNodes(root.left);
        }
        return (1 << leftDepth) + countNodes(root.right);
    }

    private int depth(TreeNode root) {
        int res = 0;
        while (root != null) {
            ++res;
            root = root.left;
        }
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
    int countNodes(TreeNode* root) {
        if (!root) {
            return 0;
        }
        int leftDepth = depth(root->left);
        int rightDepth = depth(root->right);
        if (leftDepth > rightDepth) {
            return (1 << rightDepth) + countNodes(root->left);
        }
        return (1 << leftDepth) + countNodes(root->right);
    }

private:
    int depth(TreeNode* root) {
        int res = 0;
        while (root) {
            ++res;
            root = root->left;
        }
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
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	leftDepth := depth(root.Left)
	rightDepth := depth(root.Right)
	if leftDepth > rightDepth {
		return (1 << rightDepth) + countNodes(root.Left)
	}
	return (1 << leftDepth) + countNodes(root.Right)
}

func depth(root *TreeNode) int {
	res := 0
	for root != nil {
		res++
		root = root.Left
	}
	return res
}
```

### **C#**

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public int CountNodes(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth > rightDepth)
        {
            return (1 << rightDepth) + CountNodes(root.left);
        }
        return (1 << leftDepth) + CountNodes(root.right);
    }

    private int depth(TreeNode root) {
        int res = 0;
        while (root != null)
        {
            ++res;
            root = root.left;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
