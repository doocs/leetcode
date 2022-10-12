# [938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst)

[中文文档](/solution/0900-0999/0938.Range%20Sum%20of%20BST/README.md)

## Description

<p>Given the <code>root</code> node of a binary search tree and two integers <code>low</code> and <code>high</code>, return <em>the sum of values of all nodes with a value in the <strong>inclusive</strong> range </em><code>[low, high]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0938.Range%20Sum%20of%20BST/images/bst1.jpg" style="width: 400px; height: 222px;" />
<pre>
<strong>Input:</strong> root = [10,5,15,3,7,null,18], low = 7, high = 15
<strong>Output:</strong> 32
<strong>Explanation:</strong> Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0938.Range%20Sum%20of%20BST/images/bst2.jpg" style="width: 400px; height: 335px;" />
<pre>
<strong>Input:</strong> root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
<strong>Output:</strong> 23
<strong>Explanation:</strong> Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 2 * 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= low &lt;= high &lt;= 10<sup>5</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
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
    def rangeSumBST(self, root: TreeNode, low: int, high: int) -> int:
        def search(node):
            if not node:
                return
            if low <= node.val <= high:
                self.ans += node.val
                search(node.left)
                search(node.right)
            elif node.val < low:
                search(node.right)
            elif node.val > high:
                search(node.left)

        self.ans = 0
        search(root)
        return self.ans
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
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (low <= root.val && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high)
                + rangeSumBST(root.right, low, high);
        } else if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high);
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
    int rangeSumBST(TreeNode* root, int low, int high) {
        if (root == nullptr) return 0;
        if (low <= root->val && root->val <= high) {
            return root->val + rangeSumBST(root->left, low, high) + rangeSumBST(root->right, low, high);
        } else if (root->val < low) {
            return rangeSumBST(root->right, low, high);
        } else {
            return rangeSumBST(root->left, low, high);
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
func rangeSumBST(root *TreeNode, low int, high int) int {
	if root == nil {
		return 0
	}
	if low <= root.Val && root.Val <= high {
		return root.Val + rangeSumBST(root.Left, low, high) + rangeSumBST(root.Right, low, high)
	} else if root.Val < low {
		return rangeSumBST(root.Right, low, high)
	} else {
		return rangeSumBST(root.Left, low, high)
	}
}
```

### **...**

```

```

<!-- tabs:end -->
