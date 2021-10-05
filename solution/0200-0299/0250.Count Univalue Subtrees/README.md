# [250. 统计同值子树](https://leetcode-cn.com/problems/count-univalue-subtrees)

[English Version](/solution/0200-0299/0250.Count%20Univalue%20Subtrees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，统计该二叉树数值相同的子树个数。</p>

<p>同值子树是指该子树的所有节点都拥有相同的数值。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入: </strong>root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

<strong>输出:</strong> 4
</pre>

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
    def countUnivalSubtrees(self, root: TreeNode) -> int:
        if root is None:
            return 0
        cnt = 0

        def dfs(root):
            nonlocal cnt
            if root.left is None and root.right is None:
                cnt += 1
                return True
            res = True
            if root.left:
                # exec dfs(root.left) first
                res = dfs(root.left) and res and root.val == root.left.val
            if root.right:
                # exec dfs(root.right) first
                res = dfs(root.right) and res and root.val == root.right.val
            cnt += res
            return res

        dfs(root)
        return cnt
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
    private int cnt;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        cnt = 0;
        dfs(root);
        return cnt;
    }

    private boolean dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            ++cnt;
            return true;
        }
        boolean res = true;
        if (root.left != null) {
            // exec dfs(root.left) first
            res = dfs(root.left) && res && root.val == root.left.val;
        }
        if (root.right != null) {
            // exec dfs(root.right) first
            res = dfs(root.right) && res && root.val == root.right.val;
        }
        if (res) {
            ++cnt;
        }
        return res;
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
    int cnt;

    int countUnivalSubtrees(TreeNode* root) {
        if (!root) return 0;
        cnt = 0;
        dfs(root);
        return cnt;
    }

    bool dfs(TreeNode* root) {
        if (!root->left && !root->right)
        {
            ++cnt;
            return true;
        }
        bool res = true;
        if (root->left) res = dfs(root->left) && res && root->val == root->left->val;
        if (root->right) res = dfs(root->right) && res && root->val == root->right->val;
        cnt += res;
        return res;

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
var cnt int

func countUnivalSubtrees(root *TreeNode) int {
	if root == nil {
		return 0
	}
	cnt = 0
	dfs(root)
	return cnt
}

func dfs(root *TreeNode) bool {
	if root.Left == nil && root.Right == nil {
		cnt++
		return true
	}
	res := true
	if root.Left != nil {
		res = dfs(root.Left) && res && root.Val == root.Left.Val
	}
	if root.Right != nil {
		res = dfs(root.Right) && res && root.Val == root.Right.Val
	}
	if res {
		cnt++
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
