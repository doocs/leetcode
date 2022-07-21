# [655. 输出二叉树](https://leetcode.cn/problems/print-binary-tree)

[English Version](/solution/0600-0699/0655.Print%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> ，请你构造一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的字符串矩阵 <code>res</code> ，用以表示树的 <strong>格式化布局</strong> 。构造此格式化布局矩阵需要遵循以下规则：</p>

<ul>
	<li>树的 <strong>高度</strong> 为 <code>height</code> ，矩阵的行数 <code>m</code> 应该等于 <code>height + 1</code> 。</li>
	<li>矩阵的列数 <code>n</code> 应该等于 <code>2<sup>height+1</sup> - 1</code> 。</li>
	<li><strong>根节点</strong> 需要放置在 <strong>顶行</strong> 的 <strong>正中间</strong> ，对应位置为 <code>res[0][(n-1)/2]</code> 。</li>
	<li>对于放置在矩阵中的每个节点，设对应位置为 <code>res[r][c]</code> ，将其左子节点放置在 <code>res[r+1][c-2<sup>height-r-1</sup>]</code> ，右子节点放置在 <code>res[r+1][c+2<sup>height-r-1</sup>]</code> 。</li>
	<li>继续这一过程，直到树中的所有节点都妥善放置。</li>
	<li>任意空单元格都应该包含空字符串 <code>""</code> 。</li>
</ul>

<p>返回构造得到的矩阵<em> </em><code>res</code> 。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0655.Print%20Binary%20Tree/images/print1-tree.jpg" style="width: 141px; height: 181px;" />
<pre>
<strong>输入：</strong>root = [1,2]
<strong>输出：</strong>
[["","1",""],
&nbsp;["2","",""]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0655.Print%20Binary%20Tree/images/print2-tree.jpg" style="width: 207px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,null,4]
<strong>输出：</strong>
[["","","","1","","",""],
&nbsp;["","2","","","","3",""],
&nbsp;["","","4","","","",""]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数在范围 <code>[1, 2<sup>10</sup>]</code> 内</li>
	<li><code>-99 &lt;= Node.val &lt;= 99</code></li>
	<li>树的深度在范围 <code>[1, 10]</code> 内</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

> 中文题意不容易读懂，可以参考[英文版本的题目描述](/solution/0600-0699/0655.Print%20Binary%20Tree/README_EN.md)。

先求二叉树的高度 h，然后根据 h 求得结果列表的行数和列数 m, n。

根据 m, n 初始化结果列表，然后 dfs 遍历二叉树，依次在每个位置填入二叉树节点值（字符串形式）即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
