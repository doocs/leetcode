# [701. 二叉搜索树中的插入操作](https://leetcode-cn.com/problems/insert-into-a-binary-search-tree)

[English Version](/solution/0700-0799/0701.Insert%20into%20a%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 <strong>保证</strong> ，新值和原始二叉搜索树中的任意节点值都不同。</p>

<p><strong>注意</strong>，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 <strong>任意有效的结果</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0701.Insert%20into%20a%20Binary%20Search%20Tree/images/insertbst.jpg" style="width: 752px; height: 221px;" />
<pre>
<strong>输入：</strong>root = [4,2,7,1,3], val = 5
<strong>输出：</strong>[4,2,7,1,3,5]
<strong>解释：</strong>另一个满足题目要求可以通过的树是：
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0701.Insert%20into%20a%20Binary%20Search%20Tree/images/bst.jpg" style="width: 352px; height: 301px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [40,20,60,10,30,50,70], val = 25
<strong>输出：</strong>[40,20,60,10,30,50,70,null,null,25]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
<strong>输出：</strong>[4,2,7,1,3,5]
</pre>

<p> </p>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定的树上的节点数介于 <code>0</code> 和 <code>10^4</code> 之间</li>
	<li>每个节点都有一个唯一整数值，取值范围从 <code>0</code> 到 <code>10^8</code></li>
	<li><code>-10^8 <= val <= 10^8</code></li>
	<li>新值和原始二叉搜索树中的任意节点值都不同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

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
    def insertIntoBST(self, root: TreeNode, val: int) -> TreeNode:
        def dfs(root):
            if root is None:
                return TreeNode(val)
            if root.val < val:
                root.right = dfs(root.right)
            else:
                root.left = dfs(root.left)
            return root

        return dfs(root)
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

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
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
    TreeNode* insertIntoBST(TreeNode* root, int val) {
        if (!root) return new TreeNode(val);
        if (root->val < val) root->right = insertIntoBST(root->right, val);
        else root->left = insertIntoBST(root->left, val);
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
func insertIntoBST(root *TreeNode, val int) *TreeNode {
	if root == nil {
		return &TreeNode{Val: val}
	}
	if root.Val < val {
		root.Right = insertIntoBST(root.Right, val)
	} else {
		root.Left = insertIntoBST(root.Left, val)
	}
	return root
}
```

### **...**

```

```

<!-- tabs:end -->
