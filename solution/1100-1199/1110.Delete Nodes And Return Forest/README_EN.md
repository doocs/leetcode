# [1110. Delete Nodes And Return Forest](https://leetcode.com/problems/delete-nodes-and-return-forest)

[中文文档](/solution/1100-1199/1110.Delete%20Nodes%20And%20Return%20Forest/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, each node in the tree has a distinct value.</p>

<p>After deleting all nodes with a value in <code>to_delete</code>, we are left with a forest (a disjoint union of trees).</p>

<p>Return the roots of the trees in the remaining forest. You may return the result in any order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1110.Delete%20Nodes%20And%20Return%20Forest/images/screen-shot-2019-07-01-at-53836-pm.png" style="width: 237px; height: 150px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,7], to_delete = [3,5]
<strong>Output:</strong> [[1,2,null,4],[6],[7]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2,4,null,3], to_delete = [3]
<strong>Output:</strong> [[1,2,4]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the given tree is at most <code>1000</code>.</li>
	<li>Each node has a distinct value between <code>1</code> and <code>1000</code>.</li>
	<li><code>to_delete.length &lt;= 1000</code></li>
	<li><code>to_delete</code> contains distinct values between <code>1</code> and <code>1000</code>.</li>
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
    def delNodes(self, root: TreeNode, to_delete: List[int]) -> List[TreeNode]:
        def dfs(fa, root):
            if root is None:
                return
            dfs(root, root.left)
            dfs(root, root.right)
            if root.val in s:
                if fa and fa.left == root:
                    fa.left = None
                if fa and fa.right == root:
                    fa.right = None
                if root.left:
                    ans.append(root.left)
                if root.right:
                    ans.append(root.right)

        s = set(to_delete)
        ans = []
        if root.val not in s:
            ans.append(root)
        dfs(None, root)
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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        boolean[] del = new boolean[1001];
        for (int d : to_delete) {
            del[d] = true;
        }
        List<TreeNode> res = new ArrayList<>();
        dfs(root, true, del, res);
        return res;
    }

    private TreeNode dfs(TreeNode root, boolean isRoot, boolean[] del, List<TreeNode> res) {
        if (root == null) {
            return null;
        }
        boolean flag = del[root.val];
        if (!flag && isRoot) {
            res.add(root);
        }
        root.left = dfs(root.left, flag, del, res);
        root.right = dfs(root.right, flag, del, res);
        return flag ? null : root;
    }
}
```

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
    private List<TreeNode> ans = new ArrayList<>();
    private Set<Integer> s = new HashSet<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for (int v : to_delete) {
            s.add(v);
        }
        if (!s.contains(root.val)) {
            ans.add(root);
        }
        dfs(null, root);
        return ans;
    }

    private void dfs(TreeNode fa, TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root, root.left);
        dfs(root, root.right);
        if (s.contains(root.val)) {
            if (fa != null && fa.left == root) {
                fa.left = null;
            }
            if (fa != null && fa.right == root) {
                fa.right = null;
            }
            if (root.left != null) {
                ans.add(root.left);
            }
            if (root.right != null) {
                ans.add(root.right);
            }
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
    vector<TreeNode*> delNodes(TreeNode* root, vector<int>& to_delete) {
        vector<TreeNode*> ans;
        unordered_set<int> s(to_delete.begin(), to_delete.end());
        if (!s.count(root->val)) ans.push_back(root);
        dfs(nullptr, root, s, ans);
        return ans;
    }

    void dfs(TreeNode* fa, TreeNode* root, unordered_set<int>& s, vector<TreeNode*>& ans) {
        if (!root) return;
        dfs(root, root->left, s, ans);
        dfs(root, root->right, s, ans);
        if (s.count(root->val)) {
            if (fa && fa->left == root) fa->left = nullptr;
            if (fa && fa->right == root) fa->right = nullptr;
            if (root->left) ans.push_back(root->left);
            if (root->right) ans.push_back(root->right);
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
func delNodes(root *TreeNode, to_delete []int) []*TreeNode {
	s := map[int]bool{}
	for _, v := range to_delete {
		s[v] = true
	}
	ans := []*TreeNode{}
	if !s[root.Val] {
		ans = append(ans, root)
	}
	var fa *TreeNode
	var dfs func(fa, root *TreeNode)
	dfs = func(fa, root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root, root.Left)
		dfs(root, root.Right)
		if s[root.Val] {
			if fa != nil && fa.Left == root {
				fa.Left = nil
			}
			if fa != nil && fa.Right == root {
				fa.Right = nil
			}
			if root.Left != nil {
				ans = append(ans, root.Left)
			}
			if root.Right != nil {
				ans = append(ans, root.Right)
			}
		}
	}
	dfs(fa, root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
