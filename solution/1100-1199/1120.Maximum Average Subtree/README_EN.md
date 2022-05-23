# [1120. Maximum Average Subtree](https://leetcode.com/problems/maximum-average-subtree)

[中文文档](/solution/1100-1199/1120.Maximum%20Average%20Subtree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the maximum <strong>average</strong> value of a <strong>subtree</strong> of that tree</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>A <strong>subtree</strong> of a tree is any node of that tree plus all its descendants.</p>

<p>The <strong>average</strong> value of a tree is the sum of its values, divided by the number of nodes.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1120.Maximum%20Average%20Subtree/images/1308_example_1.png" style="width: 132px; height: 123px;" />
<pre>
<strong>Input:</strong> root = [5,6,1]
<strong>Output:</strong> 6.00000
<strong>Explanation:</strong> 
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [0,null,1]
<strong>Output:</strong> 1.00000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
    def maximumAverageSubtree(self, root: TreeNode) -> float:
        def dfs(root):
            if root is None:
                return 0, 0
            ls, ln = dfs(root.left)
            rs, rn = dfs(root.right)
            s = ls + root.val + rs
            n = ln + 1 + rn
            nonlocal ans
            ans = max(ans, s / n)
            return s, n

        ans = 0
        dfs(root)
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
    private double ans;

    public double maximumAverageSubtree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int s = l[0] + root.val + r[0];
        int n = l[1] + 1 + r[1];
        ans = Math.max(ans, s * 1.0 / n);
        return new int[]{s, n};
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
    double ans;

    double maximumAverageSubtree(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    pair<int, int> dfs(TreeNode* root) {
        if (!root) return {0, 0};
        auto l = dfs(root->left);
        auto r = dfs(root->right);
        int s = l.first + root->val + r.first;
        int n = l.second + 1 + r.second;
        ans = max(ans, s * 1.0 / n);
        return {s, n};
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
func maximumAverageSubtree(root *TreeNode) float64 {
	var ans float64
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{0, 0}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		s := l[0] + root.Val + r[0]
		n := l[1] + 1 + r[1]
		ans = math.Max(ans, float64(s)/float64(n))
		return []int{s, n}
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
