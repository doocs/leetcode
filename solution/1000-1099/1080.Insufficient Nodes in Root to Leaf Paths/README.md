# [1080. 根到叶路径上的不足节点](https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths)

[English Version](/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉树的根 <code>root</code>，请你考虑它所有&nbsp;<strong>从根到叶的路径</strong>：从根到任何叶的路径。（所谓一个叶子节点，就是一个没有子节点的节点）</p>

<p>假如通过节点 <code>node</code> 的每种可能的 &ldquo;根-叶&rdquo; 路径上值的总和全都小于给定的 <code>limit</code>，则该节点被称之为「不足节点」，需要被删除。</p>

<p>请你删除所有不足节点，并返回生成的二叉树的根。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-1.png" style="height: 200px; width: 482px;">
输入：</strong>root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-2.png" style="height: 200px; width: 258px;">
输出：</strong>[1,2,3,4,null,null,7,8,9,null,14]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-3.png" style="height: 200px; width: 292px;">
输入：</strong>root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-4.png" style="height: 200px; width: 264px;">
输出：</strong>[5,4,8,11,null,17,4,7,null,null,null,5]</pre>

<p><strong>示例 3：</strong></p>

<pre><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1080.Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/images/insufficient-5.png" style="height: 100px; width: 140px;">
输入：</strong>root = [5,-6,-6], limit = 0<strong>
输出：</strong>[]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>给定的树有&nbsp;<code>1</code>&nbsp;到&nbsp;<code>5000</code>&nbsp;个节点</li>
	<li><code>-10^5&nbsp;&lt;= node.val &lt;= 10^5</code></li>
	<li><code>-10^9 &lt;= limit&nbsp;&lt;= 10^9</code></li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

递归遍历整棵树，如果到达叶子结点且路径和小于 $limit$，直接返回 `null` 表示删除。如果左右子树都被删除，说明经过当前结点的路径和也一定小于 $limit$，同样需要删除。

时间复杂度 $O(n)$，其中 $n$ 是二叉树节点的个数。

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
    def sufficientSubset(
        self, root: Optional[TreeNode], limit: int
    ) -> Optional[TreeNode]:
        if root is None:
            return None
        limit -= root.val
        if root.left is None and root.right is None:
            return None if limit > 0 else root
        root.left = self.sufficientSubset(root.left, limit)
        root.right = self.sufficientSubset(root.right, limit)
        return None if root.left is None and root.right is None else root
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
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return null;
        }
        limit -= root.val;
        if (root.left == null && root.right == null) {
            return limit > 0 ? null : root;
        }
        root.left = sufficientSubset(root.left, limit);
        root.right = sufficientSubset(root.right, limit);
        return root.left == null && root.right == null ? null : root;
    }
}
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
func sufficientSubset(root *TreeNode, limit int) *TreeNode {
	if root == nil {
		return nil
	}

	limit -= root.Val
	if root.Left == nil && root.Right == nil {
		if limit > 0 {
			return nil
		}
		return root
	}

	root.Left = sufficientSubset(root.Left, limit)
	root.Right = sufficientSubset(root.Right, limit)

	if root.Left == nil && root.Right == nil {
		return nil
	}
	return root
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
    TreeNode* sufficientSubset(TreeNode* root, int limit) {
        if (!root) return nullptr;
        limit -= root->val;
        if (!root->left && !root->right) return limit > 0 ? nullptr : root;
        root->left = sufficientSubset(root->left, limit);
        root->right = sufficientSubset(root->right, limit);
        return !root->left && !root->right ? nullptr : root;
    }
};
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
 * @param {number} limit
 * @return {TreeNode}
 */
var sufficientSubset = function (root, limit) {
    if (!root) {
        return null;
    }
    limit -= root.val;
    if (!root.left && !root.right) {
        return limit > 0 ? null : root;
    }
    root.left = sufficientSubset(root.left, limit);
    root.right = sufficientSubset(root.right, limit);
    return !root.left && !root.right ? null : root;
};
```

### **...**

```

```

<!-- tabs:end -->
