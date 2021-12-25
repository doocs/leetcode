# [655. Print Binary Tree](https://leetcode.com/problems/print-binary-tree)

[中文文档](/solution/0600-0699/0655.Print%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, construct a <strong>0-indexed</strong> <code>m x n</code> string matrix <code>res</code> that represents a <strong>formatted layout</strong> of the tree. The formatted layout matrix should be constructed using the following rules:</p>

<ul>
	<li>The <strong>height</strong> of the tree is <code>height</code>&nbsp;and the number of rows <code>m</code> should be equal to <code>height + 1</code>.</li>
	<li>The number of columns <code>n</code> should be equal to <code>2<sup>height+1</sup> - 1</code>.</li>
	<li>Place the <strong>root node</strong> in the <strong>middle</strong> of the <strong>top row</strong> (more formally, at location <code>res[0][(n-1)/2]</code>).</li>
	<li>For each node that has been placed in the matrix at position <code>res[r][c]</code>, place its <strong>left child</strong> at <code>res[r+1][c-2<sup>height-r-1</sup>]</code> and its <strong>right child</strong> at <code>res[r+1][c+2<sup>height-r-1</sup>]</code>.</li>
	<li>Continue this process until all the nodes in the tree have been placed.</li>
	<li>Any empty cells should contain the empty string <code>&quot;&quot;</code>.</li>
</ul>

<p>Return <em>the constructed matrix </em><code>res</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0655.Print%20Binary%20Tree/images/print1-tree.jpg" style="width: 141px; height: 181px;" />
<pre>
<strong>Input:</strong> root = [1,2]
<strong>Output:</strong> 
[[&quot;&quot;,&quot;1&quot;,&quot;&quot;],
&nbsp;[&quot;2&quot;,&quot;&quot;,&quot;&quot;]]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0655.Print%20Binary%20Tree/images/print2-tree.jpg" style="width: 207px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4]
<strong>Output:</strong> 
[[&quot;&quot;,&quot;&quot;,&quot;&quot;,&quot;1&quot;,&quot;&quot;,&quot;&quot;,&quot;&quot;],
&nbsp;[&quot;&quot;,&quot;2&quot;,&quot;&quot;,&quot;&quot;,&quot;&quot;,&quot;3&quot;,&quot;&quot;],
&nbsp;[&quot;&quot;,&quot;&quot;,&quot;4&quot;,&quot;&quot;,&quot;&quot;,&quot;&quot;,&quot;&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 2<sup>10</sup>]</code>.</li>
	<li><code>-99 &lt;= Node.val &lt;= 99</code></li>
	<li>The depth of the tree will be in the range <code>[1, 10]</code>.</li>
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
    def printTree(self, root: TreeNode) -> List[List[str]]:
        def height(root):
            if root is None:
                return -1
            return 1 + max(height(root.left), height(root.right))

        def dfs(root, r, c):
            if root is None:
                return
            ans[r][c] = str(root.val)
            dfs(root.left, r + 1, c - 2 ** (h - r - 1))
            dfs(root.right, r + 1, c + 2 ** (h - r - 1))

        h = height(root)
        m, n = h + 1, 2 ** (h + 1) - 1
        ans = [[""] * n for _ in range(m)]
        dfs(root, 0, (n - 1) // 2)
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
    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        String[][] res = new String[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(res[i], "");
        }
        dfs(root, res, h, 0, (n - 1) / 2);
        List<List<String>> ans = new ArrayList<>();
        for (String[] t : res) {
            ans.add(Arrays.asList(t));
        }
        return ans;
    }

    private void dfs(TreeNode root, String[][] res, int h, int r, int c) {
        if (root == null) {
            return;
        }
        res[r][c] = String.valueOf(root.val);
        dfs(root.left, res, h, r + 1, c - (1 << (h - r - 1)));
        dfs(root.right, res, h, r + 1, c + (1 << (h - r - 1)));
    }

    private int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
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
    vector<vector<string>> printTree(TreeNode* root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        vector<vector<string>> ans(m, vector<string>(n, ""));
        dfs(root, ans, h, 0, (n - 1) / 2);
        return ans;
    }

    void dfs(TreeNode* root, vector<vector<string>>& ans, int h, int r, int c) {
        if (!root) return;
        ans[r][c] = to_string(root->val);
        dfs(root->left, ans, h, r + 1, c - pow(2, h - r - 1));
        dfs(root->right, ans, h, r + 1, c + pow(2, h - r - 1));
    }

    int height(TreeNode* root) {
        if (!root) return -1;
        return 1 + max(height(root->left), height(root->right));
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
func printTree(root *TreeNode) [][]string {
	var height func(root *TreeNode) int
	height = func(root *TreeNode) int {
		if root == nil {
			return -1
		}
		return 1 + max(height(root.Left), height(root.Right))
	}
	h := height(root)
	m, n := h+1, (1<<(h+1))-1
	ans := make([][]string, m)
	for i := range ans {
		ans[i] = make([]string, n)
		for j := range ans[i] {
			ans[i][j] = ""
		}
	}
	var dfs func(root *TreeNode, r, c int)
	dfs = func(root *TreeNode, r, c int) {
		if root == nil {
			return
		}
		ans[r][c] = strconv.Itoa(root.Val)
		dfs(root.Left, r+1, c-int(math.Pow(float64(2), float64(h-r-1))))
		dfs(root.Right, r+1, c+int(math.Pow(float64(2), float64(h-r-1))))
	}

	dfs(root, 0, (n-1)/2)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
