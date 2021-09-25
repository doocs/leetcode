# [1315. Sum of Nodes with Even-Valued Grandparent](https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent)

[中文文档](/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/README.md)

## Description

<p>Given a binary tree, return the sum of values of nodes with even-valued grandparent.&nbsp; (A <em>grandparent</em> of a node is the parent of its parent, if it exists.)</p>

<p>If there are no nodes with an even-valued grandparent, return&nbsp;<code>0</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/images/1473_ex1.png" style="width: 350px; height: 214px;" /></strong></p>

<pre>

<strong>Input:</strong> root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]

<strong>Output:</strong> 18

<b>Explanation:</b> The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>10^4</code>.</li>
	<li>The value of nodes is between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>100</code>.</li>
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
    def sumEvenGrandparent(self, root: TreeNode) -> int:
        self.res = 0

        def dfs(g, p):
            if p is None:
                return
            if g.val % 2 == 0:
                if p.left:
                    self.res += p.left.val
                if p.right:
                    self.res += p.right.val
            dfs(p, p.left)
            dfs(p, p.right)

        dfs(root, root.left)
        dfs(root, root.right)
        return self.res
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
    private int res;

    public int sumEvenGrandparent(TreeNode root) {
        res = 0;
        dfs(root, root.left);
        dfs(root, root.right);
        return res;
    }

    private void dfs(TreeNode g, TreeNode p) {
        if (p == null) {
            return;
        }
        if (g.val % 2 == 0) {
            if (p.left != null) {
                res += p.left.val;
            }
            if (p.right != null) {
                res += p.right.val;
            }
        }
        dfs(p, p.left);
        dfs(p, p.right);
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
    int res;

    int sumEvenGrandparent(TreeNode* root) {
        res = 0;
        dfs(root, root->left);
        dfs(root, root->right);
        return res;
    }

    void dfs(TreeNode* g, TreeNode* p) {
        if (!p) return;
        if (g->val % 2 == 0)
        {
            if (p->left) res += p->left->val;
            if (p->right) res += p->right->val;
        }
        dfs(p, p->left);
        dfs(p, p->right);
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

var res int

func sumEvenGrandparent(root *TreeNode) int {
	res = 0
	dfs(root, root.Left)
	dfs(root, root.Right)
	return res
}

func dfs(g, p *TreeNode) {
	if p == nil {
		return
	}
	if g.Val%2 == 0 {
		if p.Left != nil {
			res += p.Left.Val
		}
		if p.Right != nil {
			res += p.Right.Val
		}
	}
	dfs(p, p.Left)
	dfs(p, p.Right)
}
```

### **...**

```

```

<!-- tabs:end -->
