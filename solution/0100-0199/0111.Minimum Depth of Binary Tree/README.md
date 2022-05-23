# [111. 二叉树的最小深度](https://leetcode.cn/problems/minimum-depth-of-binary-tree)

[English Version](/solution/0100-0199/0111.Minimum%20Depth%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，找出其最小深度。</p>

<p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。</p>

<p><strong>说明：</strong>叶子节点是指没有子节点的节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0111.Minimum%20Depth%20of%20Binary%20Tree/images/ex_depth.jpg" style="width: 432px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [2,null,3,null,4,null,5,null,6]
<strong>输出：</strong>5
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数的范围在 <code>[0, 10<sup>5</sup>]</code> 内</li>
	<li><code>-1000 <= Node.val <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

若左子树和右子树其中一个为空，那么需要返回比较大的那个子树的深度加 1；左右子树都不为空，返回最小深度加 1 即可。

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
    def minDepth(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return 0
            if root.left is None:
                return 1 + dfs(root.right)
            if root.right is None:
                return 1 + dfs(root.left)
            return 1 + min(dfs(root.left), dfs(root.right))

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
    public int minDepth(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return 1 + dfs(root.right);
        }
        if (root.right == null) {
            return 1 + dfs(root.left);
        }
        return 1 + Math.min(dfs(root.left), dfs(root.right));
    }
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
var minDepth = function (root) {
    function dfs(root) {
        if (!root) return 0;
        if (!root.left) return 1 + dfs(root.right);
        if (!root.right) return 1 + dfs(root.left);
        return 1 + Math.min(dfs(root.left), dfs(root.right));
    }
    return dfs(root);
};
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
    int minDepth(TreeNode* root) {
        return dfs(root);
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        if (!root->left) return 1 + dfs(root->right);
        if (!root->right) return 1 + dfs(root->left);
        return 1 + min(dfs(root->left), dfs(root->right));
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
func minDepth(root *TreeNode) int {
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if root.Left == nil {
			return 1 + dfs(root.Right)
		}
		if root.Right == nil {
			return 1 + dfs(root.Left)
		}
		return 1 + min(dfs(root.Left), dfs(root.Right))
	}
	return dfs(root)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
