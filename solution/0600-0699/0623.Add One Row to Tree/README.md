# [623. 在二叉树中增加一行](https://leetcode-cn.com/problems/add-one-row-to-tree)

[English Version](/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，根节点为第1层，深度为 1。在其第&nbsp;<code>d</code>&nbsp;层追加一行值为&nbsp;<code>v</code>&nbsp;的节点。</p>

<p>添加规则：给定一个深度值 <code>d</code> （正整数），针对深度为 <code>d-1</code> 层的每一<strong>非空</strong>节点 <code>N</code>，为 <code>N</code> 创建两个值为&nbsp;<code>v</code>&nbsp;的左子树和右子树。</p>

<p>将&nbsp;<code>N</code> 原先的左子树，连接为新节点&nbsp;<code>v</code> 的左子树；将&nbsp;<code>N</code> 原先的右子树，连接为新节点&nbsp;<code>v</code> 的右子树。</p>

<p>如果 <code>d</code> 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 <code>v</code>，原先的整棵树将作为 <code>v</code> 的左子树。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
二叉树如下所示:
       4
     /   \
    2     6
   / \   / 
  3   1 5   

<strong>v = 1</strong>

<strong>d = 2</strong>

<strong>输出:</strong> 
       4
      / \
     1   1
    /     \
   2       6
  / \     / 
 3   1   5   

</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
二叉树如下所示:
      4
     /   
    2    
   / \   
  3   1    

<strong>v = 1</strong>

<strong>d = 3</strong>

<strong>输出:</strong> 
      4
     /   
    2
   / \    
  1   1
 /     \  
3       1
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。</li>
	<li>输入的二叉树至少有一个节点。</li>
</ol>

## 解法

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
    def addOneRow(self, root: TreeNode, val: int, depth: int) -> TreeNode:
        def dfs(root, d):
            if root is None:
                return
            if d == depth - 1:
                l = TreeNode(val=val, left=root.left)
                r = TreeNode(val=val, right=root.right)
                root.left, root.right = l, r
                return
            dfs(root.left, d + 1)
            dfs(root.right, d + 1)

        if depth == 1:
            return TreeNode(val=val, left=root)
        dfs(root, 1)
        return root
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
    private int val;
    private int depth;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        this.val = val;
        this.depth = depth;
        dfs(root, 1);
        return root;
    }

    private void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (d == depth - 1) {
            TreeNode l = new TreeNode(val, root.left, null);
            TreeNode r = new TreeNode(val, null, root.right);
            root.left = l;
            root.right = r;
            return;
        }
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
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
    int val;
    int depth;

    TreeNode* addOneRow(TreeNode* root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, nullptr);
        this->val = val;
        this->depth = depth;
        dfs(root, 1);
        return root;
    }

    void dfs(TreeNode* root, int d) {
        if (!root) return;
        if (d == depth - 1)
        {
            auto l = new TreeNode(val, root->left, nullptr);
            auto r = new TreeNode(val, nullptr, root->right);
            root->left = l;
            root->right = r;
            return;
        }
        dfs(root->left, d + 1);
        dfs(root->right, d + 1);
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
func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		return &TreeNode{Val: val, Left: root}
	}
	var dfs func(root *TreeNode, d int)
	dfs = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if d == depth-1 {
			l, r := &TreeNode{Val: val, Left: root.Left}, &TreeNode{Val: val, Right: root.Right}
			root.Left, root.Right = l, r
			return
		}
		dfs(root.Left, d+1)
		dfs(root.Right, d+1)
	}
	dfs(root, 1)
	return root
}
```

### **...**

```

```

<!-- tabs:end -->
