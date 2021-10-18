# [865. 具有所有最深节点的最小子树](https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes)

[English Version](/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个根为 <code>root</code> 的二叉树，每个节点的深度是 <strong>该节点到根的最短距离</strong> 。</p>

<p>如果一个节点在 <strong>整个树 </strong>的任意节点之间具有最大的深度，则该节点是 <strong>最深的</strong> 。</p>

<p>一个节点的 <strong>子树</strong> 是该节点加上它的所有后代的集合。</p>

<p>返回能满足 <strong>以该节点为根的子树中包含所有最深的节点</strong> 这一条件的具有最大深度的节点。</p>

<p> </p>

<p><strong>注意：</strong>本题与力扣 1123 重复：<a href="https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/" target="_blank">https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/</a></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" height="475" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/images/sketch1.png" width="560" /></p>

<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4]
<strong>输出：</strong>[2,7,4]
<strong>解释：</strong>
我们返回值为 2 的节点，在图中用黄色标记。
在图中用蓝色标记的是树的最深的节点。
注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
<strong>解释：</strong>根节点是树中最深的节点。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0,1,3,null,2]
<strong>输出：</strong>[2]
<strong>解释：</strong>树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量介于 1 和 500 之间。</li>
	<li><code>0 <= Node.val <= 500</code></li>
	<li>每个节点的值都是独一无二的。</li>
</ul>

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
    def subtreeWithAllDeepest(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            if not root:
                return None, 0
            l, d1 = dfs(root.left)
            r, d2 = dfs(root.right)
            if d1 > d2:
                return l, d1 + 1
            if d1 < d2:
                return r, d2 + 1
            return root, d1 + 1

        return dfs(root)[0]
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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root)[0];
    }

    private TreeNode[] dfs(TreeNode root) {
        if (root == null) {
            return new TreeNode[]{null, new TreeNode(0)};
        }
        TreeNode[] left = dfs(root.left);
        TreeNode[] right = dfs(root.right);
        int d1 = left[1].val, d2 = right[1].val;
        if (d1 > d2) {
            return new TreeNode[]{left[0], new TreeNode(d1 + 1)};
        }
        if (d1 < d2) {
            return new TreeNode[]{right[0], new TreeNode(d2 + 1)};
        }
        return new TreeNode[]{root, new TreeNode(d1 + 1)};
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
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        return dfs(root).first;
    }

    pair<TreeNode*, int> dfs(TreeNode* root) {
        if (!root) return {nullptr, 0};
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        int d1 = left.second, d2 = right.second;
        if (d1 > d2) return {left.first, d1 + 1};
        if (d1 < d2) return {right.first, d2 + 1};
        return {root, d1 + 1};
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
type Result struct {
	Node  *TreeNode
	Depth int
}

func subtreeWithAllDeepest(root *TreeNode) *TreeNode {
	return dfs(root).Node
}

func dfs(root *TreeNode) Result {
	if root == nil {
		return Result{
			nil, 0,
		}
	}
	left, right := dfs(root.Left), dfs(root.Right)
	d1, d2 := left.Depth, right.Depth
	if d1 > d2 {
		return Result{left.Node, d1 + 1}
	}
	if d1 < d2 {
		return Result{right.Node, d2 + 1}
	}
	return Result{root, d1 + 1}
}
```

### **...**

```

```

<!-- tabs:end -->
