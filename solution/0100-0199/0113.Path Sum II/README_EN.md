# [113. Path Sum II](https://leetcode.com/problems/path-sum-ii)

[中文文档](/solution/0100-0199/0113.Path%20Sum%20II/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and an integer <code>targetSum</code>, return all <strong>root-to-leaf</strong> paths where each path&#39;s sum equals <code>targetSum</code>.</p>

<p>A <strong>leaf</strong> is a node with no children.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0113.Path%20Sum%20II/images/pathsumii1.jpg" style="width: 500px; height: 356px;" />
<pre>
<strong>Input:</strong> root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>Output:</strong> [[5,4,11,2],[5,8,4,5]]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0113.Path%20Sum%20II/images/pathsum2.jpg" style="width: 212px; height: 181px;" />
<pre>
<strong>Input:</strong> root = [1,2,3], targetSum = 5
<strong>Output:</strong> []
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2], targetSum = 0
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 5000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
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
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        def dfs(root, s):
            if root is None:
                return
            t.append(root.val)
            s += root.val
            if root.left is None and root.right is None:
                if s == targetSum:
                    ans.append(t[:])
            dfs(root.left, s)
            dfs(root.right, s)
            t.pop()

        ans = []
        t = []
        if root is None:
            return ans
        dfs(root, 0)
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
    private List<List<Integer>> ans;
    private List<Integer> t;
    private int target;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        t = new ArrayList<>();
        target = targetSum;
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int s) {
        if (root == null) {
            return;
        }
        t.add(root.val);
        s += root.val;
        if (root.left == null && root.right == null) {
            if (s == target) {
                ans.add(new ArrayList<>(t));
            }
        }
        dfs(root.left, s);
        dfs(root.right, s);
        t.remove(t.size() - 1);
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
    vector<vector<int>> ans;
    vector<int> t;
    int target;

    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        target = targetSum;
        dfs(root, 0);
        return ans;
    }

    void dfs(TreeNode* root, int s) {
        if (!root) return;
        t.push_back(root->val);
        s += root->val;
        if (!root->left && !root->right && s == target) ans.push_back(t);
        dfs(root->left, s);
        dfs(root->right, s);
        t.pop_back();
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
func pathSum(root *TreeNode, targetSum int) [][]int {
	ans := [][]int{}
	t := []int{}
	var dfs func(root *TreeNode, s int)
	dfs = func(root *TreeNode, s int) {
		if root == nil {
			return
		}
		t = append(t, root.Val)
		s += root.Val
		if root.Left == nil && root.Right == nil && s == targetSum {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
		}
		dfs(root.Left, s)
		dfs(root.Right, s)
		t = t[:len(t)-1]
	}
	dfs(root, 0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
