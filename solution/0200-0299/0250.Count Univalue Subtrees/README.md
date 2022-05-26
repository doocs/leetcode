# [250. 统计同值子树](https://leetcode.cn/problems/count-univalue-subtrees)

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
    def countUnivalSubtrees(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return True
            left, right = dfs(root.left), dfs(root.right)
            t = True
            if root.left and root.left.val != root.val:
                t = False
            if root.right and root.right.val != root.val:
                t = False
            nonlocal ans
            if left and t and right:
                ans += 1
            return left and t and right

        ans = 0
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
    private int ans;

    public int countUnivalSubtrees(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        boolean t = true;
        if (root.left != null && root.left.val != root.val) {
            t = false;
        }
        if (root.right != null && root.right.val != root.val) {
            t = false;
        }
        if (left && t && right) {
            ++ans;
        }
        return left && t && right;
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
    int ans;

    int countUnivalSubtrees(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    bool dfs(TreeNode* root) {
        if (!root) return 1;
        bool left = dfs(root->left);
        bool right = dfs(root->right);
        bool t = 1;
        if (root->left && root->left->val != root->val) t = 0;
        if (root->right && root->right->val != root->val) t = 0;
        if (left && t && right) ++ans;
        return left && t && right;
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
func countUnivalSubtrees(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		left, right := dfs(root.Left), dfs(root.Right)
		t := true
		if root.Left != nil && root.Left.Val != root.Val {
			t = false
		}
		if root.Right != nil && root.Right.Val != root.Val {
			t = false
		}
		if left && t && right {
			ans++
		}
		return left && t && right
	}
	dfs(root)
	return ans
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var countUnivalSubtrees = function (root) {
    let ans = 0;
    let dfs = function (root) {
        if (!root) {
            return true;
        }
        const left = dfs(root.left),
            right = dfs(root.right);
        let t = true;
        if (root.left && root.left.val != root.val) {
            t = false;
        }
        if (root.right && root.right.val != root.val) {
            t = false;
        }
        if (left && t && right) {
            ++ans;
        }
        return left && t && right;
    };
    dfs(root);
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
