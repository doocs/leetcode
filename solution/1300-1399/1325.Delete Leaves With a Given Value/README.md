# [1325. 删除给定值的叶子节点](https://leetcode.cn/problems/delete-leaves-with-a-given-value)

[English Version](/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵以&nbsp;<code>root</code>&nbsp;为根的二叉树和一个整数&nbsp;<code>target</code>&nbsp;，请你删除所有值为&nbsp;<code>target</code> 的&nbsp;<strong>叶子节点</strong> 。</p>

<p>注意，一旦删除值为&nbsp;<code>target</code>&nbsp;的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是&nbsp;<code>target</code> ，那么这个节点也应该被删除。</p>

<p>也就是说，你需要重复此过程直到不能继续删除。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_1_1684.png" style="height: 120px; width: 550px;"></strong></p>

<pre><strong>输入：</strong>root = [1,2,3,2,null,2,4], target = 2
<strong>输出：</strong>[1,null,3,null,4]
<strong>解释：
</strong>上面左边的图中，绿色节点为叶子节点，且它们的值与 target 相同（同为 2 ），它们会被删除，得到中间的图。
有一个新的节点变成了叶子节点且它的值与 target 相同，所以将再次进行删除，从而得到最右边的图。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_2_1684.png" style="height: 120px; width: 300px;"></strong></p>

<pre><strong>输入：</strong>root = [1,3,3,3,2], target = 3
<strong>输出：</strong>[1,3,null,null,2]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_3_1684.png" style="width: 450px;"></strong></p>

<pre><strong>输入：</strong>root = [1,2,null,2,null,2], target = 2
<strong>输出：</strong>[1]
<strong>解释：</strong>每一步都删除一个绿色的叶子节点（值为 2）。</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root = [1,1,1], target = 1
<strong>输出：</strong>[]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>root = [1,2,3], target = 1
<strong>输出：</strong>[1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target&nbsp;&lt;= 1000</code></li>
	<li>每一棵树最多有 <code>3000</code> 个节点。</li>
	<li>每一个节点值的范围是&nbsp;<code>[1, 1000]</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

后序遍历，遇到叶子节点值为 target 的时候，将叶子节点置为 null。此过程使用一个父节点来完成。

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
    def removeLeafNodes(
        self, root: Optional[TreeNode], target: int
    ) -> Optional[TreeNode]:
        def dfs(root, prev):
            if root is None:
                return
            dfs(root.left, root)
            dfs(root.right, root)
            if root.left is None and root.right is None and root.val == target:
                if prev.left == root:
                    prev.left = None
                else:
                    prev.right = None

        p = TreeNode(val=0, left=root)
        dfs(root, p)
        return p.left
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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode p = new TreeNode(0, root, null);
        dfs(root, p, target);
        return p.left;
    }

    private void dfs(TreeNode root, TreeNode prev, int target) {
        if (root == null) {
            return;
        }
        dfs(root.left, root, target);
        dfs(root.right, root, target);
        if (root.left == null && root.right == null && root.val == target) {
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
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
    TreeNode* removeLeafNodes(TreeNode* root, int target) {
        TreeNode* p = new TreeNode(0, root, nullptr);
        dfs(root, p, target);
        return p->left;
    }

    void dfs(TreeNode* root, TreeNode* prev, int target) {
        if (!root) return;
        dfs(root->left, root, target);
        dfs(root->right, root, target);
        if (!root->left && !root->right && root->val == target) {
            if (prev->left == root)
                prev->left = nullptr;
            else
                prev->right = nullptr;
        }
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
func removeLeafNodes(root *TreeNode, target int) *TreeNode {
	p := &TreeNode{0, root, nil}
	var dfs func(root, prev *TreeNode)
	dfs = func(root, prev *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left, root)
		dfs(root.Right, root)
		if root.Left == nil && root.Right == nil && root.Val == target {
			if prev.Left == root {
				prev.Left = nil
			} else {
				prev.Right = nil
			}
		}
	}
	dfs(root, p)
	return p.Left
}
```

### **...**

```

```

<!-- tabs:end -->
