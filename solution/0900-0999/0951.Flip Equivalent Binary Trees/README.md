# [951. 翻转等价二叉树](https://leetcode.cn/problems/flip-equivalent-binary-trees)

[English Version](/solution/0900-0999/0951.Flip%20Equivalent%20Binary%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们可以为二叉树 <strong>T</strong> 定义一个&nbsp;<strong>翻转操作&nbsp;</strong>，如下所示：选择任意节点，然后交换它的左子树和右子树。</p>

<p>只要经过一定次数的翻转操作后，能使 <strong>X</strong> 等于 <strong>Y</strong>，我们就称二叉树 <strong>X</strong> <em>翻转 等价&nbsp;</em>于二叉树 <strong>Y</strong>。</p>

<p>这些树由根节点&nbsp;<code>root1</code> 和 <code>root2</code>&nbsp;给出。如果两个二叉树是否是<em>翻转 等价&nbsp;</em>的函数，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="Flipped Trees Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0951.Flip%20Equivalent%20Binary%20Trees/images/tree_ex.png" /></p>

<pre>
<strong>输入：</strong>root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
<strong>输出：</strong>true
<strong>解释：</strong>我们翻转值为 1，3 以及 5 的三个节点。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> root1 = [], root2 = []
<strong>输出:</strong> true
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> root1 = [], root2 = [1]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树节点数在&nbsp;<code>[0, 100]</code> 范围内</li>
	<li>每棵树中的每个值都是唯一的、在 <code>[0, 99]</code>&nbsp;范围内的整数</li>
</ul>

## 解法

DFS。

<!-- 这里可写通用的实现逻辑 -->

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
    def flipEquiv(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        def dfs(root1, root2):
            if root1 == root2 or (root1 is None and root2 is None):
                return True
            if root1 is None or root2 is None or root1.val != root2.val:
                return False
            return (dfs(root1.left, root2.left) and dfs(root1.right, root2.right)) or (
                dfs(root1.left, root2.right) and dfs(root1.right, root2.left)
            )

        return dfs(root1, root2)
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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == root2 || (root1 == null && root2 == null)) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return (dfs(root1.left, root2.left) && dfs(root1.right, root2.right))
            || (dfs(root1.left, root2.right) && dfs(root1.right, root2.left));
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
    bool flipEquiv(TreeNode* root1, TreeNode* root2) {
        return dfs(root1, root2);
    }

    bool dfs(TreeNode* root1, TreeNode* root2) {
        if (root1 == root2 || (!root1 && !root2)) return true;
        if (!root1 || !root2 || root1->val != root2->val) return false;
        return (dfs(root1->left, root2->left) && dfs(root1->right, root2->right)) || (dfs(root1->left, root2->right) && dfs(root1->right, root2->left));
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
func flipEquiv(root1 *TreeNode, root2 *TreeNode) bool {
	var dfs func(root1, root2 *TreeNode) bool
	dfs = func(root1, root2 *TreeNode) bool {
		if root1 == root2 || (root1 == nil && root2 == nil) {
			return true
		}
		if root1 == nil || root2 == nil || root1.Val != root2.Val {
			return false
		}
		return (dfs(root1.Left, root2.Left) && dfs(root1.Right, root2.Right)) || (dfs(root1.Left, root2.Right) && dfs(root1.Right, root2.Left))
	}
	return dfs(root1, root2)
}
```

### **...**

```

```

<!-- tabs:end -->
