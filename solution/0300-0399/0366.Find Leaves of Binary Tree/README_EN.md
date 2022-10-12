# [366. Find Leaves of Binary Tree](https://leetcode.com/problems/find-leaves-of-binary-tree)

[中文文档](/solution/0300-0399/0366.Find%20Leaves%20of%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, collect a tree&#39;s nodes as if you were doing this:</p>

<ul>
	<li>Collect all the leaf nodes.</li>
	<li>Remove all the leaf&nbsp;nodes.</li>
	<li>Repeat until the tree is empty.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0366.Find%20Leaves%20of%20Binary%20Tree/images/remleaves-tree.jpg" style="width: 500px; height: 215px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5]
<strong>Output:</strong> [[4,5,3],[2],[1]]
Explanation:
[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> [[1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
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
    def findLeaves(self, root: TreeNode) -> List[List[int]]:
        def dfs(root, prev, t):
            if root is None:
                return
            if root.left is None and root.right is None:
                t.append(root.val)
                if prev.left == root:
                    prev.left = None
                else:
                    prev.right = None
            dfs(root.left, root, t)
            dfs(root.right, root, t)

        res = []
        prev = TreeNode(left=root)
        while prev.left:
            t = []
            dfs(prev.left, prev, t)
            res.append(t)
        return res
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        TreeNode prev = new TreeNode(0, root, null);
        while (prev.left != null) {
            List<Integer> t = new ArrayList<>();
            dfs(prev.left, prev, t);
            res.add(t);
        }
        return res;
    }

    private void dfs(TreeNode root, TreeNode prev, List<Integer> t) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            t.add(root.val);
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
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
    vector<vector<int>> findLeaves(TreeNode* root) {
        vector<vector<int>> res;
        TreeNode* prev = new TreeNode(0, root, nullptr);
        while (prev->left) {
            vector<int> t;
            dfs(prev->left, prev, t);
            res.push_back(t);
        }
        return res;
    }

    void dfs(TreeNode* root, TreeNode* prev, vector<int>& t) {
        if (!root) return;
        if (!root->left && !root->right) {
            t.push_back(root->val);
            if (prev->left == root)
                prev->left = nullptr;
            else
                prev->right = nullptr;
        }
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
func findLeaves(root *TreeNode) [][]int {
	prev := &TreeNode{
		Val:   0,
		Left:  root,
		Right: nil,
	}
	var res [][]int
	for prev.Left != nil {
		var t []int
		dfs(prev.Left, prev, &t)
		res = append(res, t)
	}
	return res
}

func dfs(root, prev *TreeNode, t *[]int) {
	if root == nil {
		return
	}
	if root.Left == nil && root.Right == nil {
		*t = append(*t, root.Val)
		if prev.Left == root {
			prev.Left = nil
		} else {
			prev.Right = nil
		}
	}
	dfs(root.Left, root, t)
	dfs(root.Right, root, t)
}
```

### **...**

```

```

<!-- tabs:end -->
