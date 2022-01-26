# [面试题 04.06. 后继者](https://leetcode-cn.com/problems/successor-lcci)

[English Version](/lcci/04.06.Successor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设计一个算法，找出二叉搜索树中指定节点的&ldquo;下一个&rdquo;节点（也即中序后继）。</p>

<p>如果指定节点没有对应的&ldquo;下一个&rdquo;节点，则返回<code>null</code>。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> root = <code>[2,1,3], p = 1

  2
 / \
1   3
</code>
<strong>输出:</strong> 2</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> root = <code>[5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1
</code>
<strong>输出:</strong> null</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> TreeNode:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans, prev
            if prev == p:
                ans = root
            prev = root
            dfs(root.right)

        ans = prev = None
        dfs(root)
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private TreeNode prev;
    private TreeNode p;
    private TreeNode ans;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        prev = null;
        ans = null;
        this.p = p;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev == p) {
            ans = root;
        }
        prev = root;
        dfs(root.right);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* prev;
    TreeNode* p;
    TreeNode* ans;

    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        this->p = p;
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        if (prev == p) ans = root;
        prev = root;
        dfs(root->right);
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
func inorderSuccessor(root *TreeNode, p *TreeNode) *TreeNode {
	var prev, ans *TreeNode
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if prev == p {
			ans = root
		}
		prev = root
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
