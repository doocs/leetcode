# [889. 根据前序和后序遍历构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal)

[English Version](/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数数组，<code>preorder</code>&nbsp;和 <code>postorder</code> ，其中 <code>preorder</code> 是一个具有 <strong>无重复</strong> 值的二叉树的前序遍历，<code>postorder</code> 是同一棵树的后序遍历，重构并返回二叉树。</p>

<p>如果存在多个答案，您可以返回其中 <strong>任何</strong> 一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/images/lc-prepost.jpg" style="height: 265px; width: 304px;" /></p>

<pre>
<strong>输入：</strong>preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
<strong>输出：</strong>[1,2,3,4,5,6,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> preorder = [1], postorder = [1]
<strong>输出:</strong> [1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 30</code></li>
	<li><code>1 &lt;= preorder[i] &lt;= preorder.length</code></li>
	<li><code>preorder</code>&nbsp;中所有值都 <strong>不同</strong></li>
	<li><code>postorder.length == preorder.length</code></li>
	<li><code>1 &lt;= postorder[i] &lt;= postorder.length</code></li>
	<li><code>postorder</code>&nbsp;中所有值都 <strong>不同</strong></li>
	<li>保证 <code>preorder</code>&nbsp;和 <code>postorder</code>&nbsp;是同一棵二叉树的前序遍历和后序遍历</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

1. 以 preorder 的第一个元素或 postorder 的最后一个元素为根节点的值。
2. 以 preorder 的第二个元素作为左子树的根节点，在 postorder 中找到该元素的索引 i，然后基于索引 i 可以计算出左右子树的长度。
3. 最后基于左右子树的长度，分别划分出前序和后序遍历序列中的左右子树，递归构造左右子树即可。

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
