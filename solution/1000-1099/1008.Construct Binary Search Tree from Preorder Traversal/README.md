# [1008. 前序遍历构造二叉搜索树](https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal)

[English Version](/solution/1000-1099/1008.Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>返回与给定前序遍历&nbsp;<code>preorder</code> 相匹配的二叉搜索树（binary <strong>search</strong> tree）的根结点。</p>

<p><em>(回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于&nbsp;<code>node.left</code>&nbsp;的任何后代，值总 <code>&lt; node.val</code>，而 <code>node.right</code> 的任何后代，值总 <code>&gt; node.val</code>。此外，前序遍历首先显示节点&nbsp;<code>node</code> 的值，然后遍历 <code>node.left</code>，接着遍历 <code>node.right</code>。）</em></p>

<p>题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[8,5,1,7,10,12]
<strong>输出：</strong>[8,5,10,1,7,null,12]
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1008.Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/images/1266.png" style="height: 200px; width: 306px;">
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 100</code></li>
	<li><code>1 &lt;= preorder[i]&nbsp;&lt;= 10^8</code></li>
	<li><code>preorder</code> 中的值互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

根据二叉搜索树的性质，DFS 构建即可。

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
    def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
        def dfs(preorder):
            if not preorder:
                return None
            root = TreeNode(preorder[0])
            left, right = 1, len(preorder)
            while left < right:
                mid = (left + right) >> 1
                if preorder[mid] > preorder[0]:
                    right = mid
                else:
                    left = mid + 1
            root.left = dfs(preorder[1:left])
            root.right = dfs(preorder[left:])
            return root

        return dfs(preorder)
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

    public TreeNode bstFromPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int i, int j) {
        if (i > j || i >= preorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        int left = i + 1, right = j + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preorder[mid] > preorder[i]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        root.left = dfs(preorder, i + 1, left - 1);
        root.right = dfs(preorder, left, j);
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
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        return dfs(preorder, 0, preorder.size() - 1);
    }

    TreeNode* dfs(vector<int>& preorder, int i, int j) {
        if (i > j || i >= preorder.size()) return nullptr;
        TreeNode* root = new TreeNode(preorder[i]);
        int left = i + 1, right = j + 1;
        while (left < right)
        {
            int mid = (left + right) >> 1;
            if (preorder[mid] > preorder[i]) right = mid;
            else left = mid + 1;
        }
        root->left = dfs(preorder, i + 1, left - 1);
        root->right = dfs(preorder, left, j);
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
func bstFromPreorder(preorder []int) *TreeNode {
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j || i >= len(preorder) {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		left, right := i+1, len(preorder)
		for left < right {
			mid := (left + right) >> 1
			if preorder[mid] > preorder[i] {
				right = mid
			} else {
				left = mid + 1
			}
		}
		root.Left = dfs(i+1, left-1)
		root.Right = dfs(left, j)
		return root
	}
	return dfs(0, len(preorder)-1)
}
```

### **...**

```

```

<!-- tabs:end -->
