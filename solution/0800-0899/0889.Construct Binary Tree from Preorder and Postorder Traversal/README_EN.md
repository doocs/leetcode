# [889. Construct Binary Tree from Preorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal)

[中文文档](/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/README.md)

## Description

<p>Given two integer arrays, <code>preorder</code> and <code>postorder</code> where <code>preorder</code> is the preorder traversal of a binary tree of <strong>distinct</strong> values and <code>postorder</code> is the postorder traversal of the same tree, reconstruct and return <em>the binary tree</em>.</p>

<p>If there exist multiple answers, you can <strong>return any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/images/lc-prepost.jpg" style="width: 304px; height: 265px;" />
<pre>
<strong>Input:</strong> preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
<strong>Output:</strong> [1,2,3,4,5,6,7]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> preorder = [1], postorder = [1]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 30</code></li>
	<li><code>1 &lt;= preorder[i] &lt;= preorder.length</code></li>
	<li>All the values of <code>preorder</code> are <strong>unique</strong>.</li>
	<li><code>postorder.length == preorder.length</code></li>
	<li><code>1 &lt;= postorder[i] &lt;= postorder.length</code></li>
	<li>All the values of <code>postorder</code> are <strong>unique</strong>.</li>
	<li>It is guaranteed that <code>preorder</code> and <code>postorder</code> are the preorder traversal and postorder traversal of the same binary tree.</li>
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
    def constructFromPrePost(
        self, preorder: List[int], postorder: List[int]
    ) -> TreeNode:
        n = len(preorder)
        if n == 0:
            return None
        root = TreeNode(preorder[0])
        if n == 1:
            return root
        for i in range(n - 1):
            if postorder[i] == preorder[1]:
                root.left = self.constructFromPrePost(
                    preorder[1 : 1 + i + 1], postorder[: i + 1]
                )
                root.right = self.constructFromPrePost(
                    preorder[1 + i + 1 :], postorder[i + 1 : -1]
                )
                return root
```

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	postMap := make(map[int]int)
	for index, v := range postorder {
		postMap[v] = index
	}
	var dfs func(prel, prer, postl, postr int) *TreeNode
	dfs = func(prel, prer, postl, postr int) *TreeNode {
		if prel > prer {
			return nil
		}
		root := &TreeNode{Val: preorder[prel]}
		if prel == prer {
			return root
		}
		leftRootIndex := postMap[preorder[prel+1]]
		leftLength := leftRootIndex - postl + 1
		root.Left = dfs(prel+1, prel+leftLength, postl, leftRootIndex)
		root.Right = dfs(prel+leftLength+1, prer, leftRootIndex+1, postr-1)
		return root
	}
	return dfs(0, len(preorder)-1, 0, len(postorder)-1)
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
    unordered_map<int, int> postMap;
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        for (int i = 0; i < postorder.size(); i++) {
            postMap[postorder[i]] = i;
        }
        return build(preorder, 0, preorder.size() - 1, postorder, 0, postorder.size() - 1);
    }

    TreeNode* build(vector<int>& preorder, int prel, int prer, vector<int>& postorder, int postl, int postr) {
        if (prel > prer) return nullptr;
        TreeNode* root = new TreeNode(preorder[prel]);
        if (prel == prer) return root;
        int leftRootIndex = postMap[preorder[prel + 1]];
        int leftLength = leftRootIndex - postl + 1;
        root->left = build(preorder, prel + 1, prel + leftLength, postorder, postl, leftRootIndex);
        root->right = build(preorder, prel + leftLength + 1, prer, postorder, leftRootIndex + 1, postr - 1);
        return root;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
