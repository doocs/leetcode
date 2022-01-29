# [1382. Balance a Binary Search Tree](https://leetcode.com/problems/balance-a-binary-search-tree)

[中文文档](/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/README.md)

## Description

<p>Given a binary search tree, return a <strong>balanced</strong> binary search tree with the same node values.</p>

<p>A binary search tree is <em>balanced</em> if and only if&nbsp;the depth of the two subtrees of&nbsp;every&nbsp;node never differ by more than 1.</p>

<p>If there is more than one answer, return any of them.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/images/1515_ex1.png" style="width: 250px; height: 248px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/images/1515_ex1_out.png" style="width: 200px; height: 200px;" /></strong></p>

<pre>

<strong>Input:</strong> root = [1,null,2,null,3,null,4,null,null]

<strong>Output:</strong> [2,1,3,null,null,null,4]

<b>Explanation:</b> This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>10^4</code>.</li>
	<li>The tree nodes will have distinct values between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>10^5</code>.</li>
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
    def balanceBST(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            vals.append(root.val)
            dfs(root.right)

        def build(i, j):
            if i > j:
                return None
            mid = (i + j) >> 1
            root = TreeNode(vals[mid])
            root.left = build(i, mid - 1)
            root.right = build(mid + 1, j)
            return root

        vals = []
        dfs(root)
        return build(0, len(vals) - 1)
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
    private List<Integer> vals;

    public TreeNode balanceBST(TreeNode root) {
        vals = new ArrayList<>();
        dfs(root);
        return build(0, vals.size() - 1);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        vals.add(root.val);
        dfs(root.right);
    }

    private TreeNode build(int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) >> 1;
        TreeNode root = new TreeNode(vals.get(mid));
        root.left = build(i, mid - 1);
        root.right = build(mid + 1, j);
        return root;
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
    vector<int> vals;

    TreeNode* balanceBST(TreeNode* root) {
        dfs(root);
        return build(0, vals.size() - 1);
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        vals.push_back(root->val);
        dfs(root->right);
    }

    TreeNode* build(int i, int j) {
        if (i > j) return nullptr;
        int mid = (i + j) >> 1;
        TreeNode* root = new TreeNode(vals[mid]);
        root->left = build(i, mid - 1);
        root->right = build(mid + 1, j);
        return root;
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
func balanceBST(root *TreeNode) *TreeNode {
	var vals []int
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		vals = append(vals, root.Val)
		dfs(root.Right)
	}
	dfs(root)
	var build func(i, j int) *TreeNode
	build = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		mid := (i + j) >> 1
		return &TreeNode{vals[mid], build(i, mid-1), build(mid+1, j)}
	}
	return build(0, len(vals)-1)
}
```

### **...**

```

```

<!-- tabs:end -->
