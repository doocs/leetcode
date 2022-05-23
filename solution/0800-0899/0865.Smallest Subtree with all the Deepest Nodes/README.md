# [865. 具有所有最深节点的最小子树](https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes)

[English Version](/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个根为&nbsp;<code>root</code>&nbsp;的二叉树，每个节点的深度是 <strong>该节点到根的最短距离</strong> 。</p>

<p>返回包含原始树中所有 <strong>最深节点</strong> 的 <em>最小子树</em> 。</p>

<p>如果一个节点在 <strong>整个树 </strong>的任意节点之间具有最大的深度，则该节点是 <strong>最深的</strong> 。</p>

<p>一个节点的 <strong>子树</strong> 是该节点加上它的所有后代的集合。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/images/sketch1.png" style="width: 300px;" /></p>

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量在<meta charset="UTF-8" />&nbsp;<code>[1, 500]</code>&nbsp;范围内。</li>
	<li><code>0 &lt;= Node.val &lt;= 500</code></li>
	<li>每个节点的值都是 <strong>独一无二</strong> 的。</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与力扣 1123 重复：<a href="https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/" target="_blank">https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves</a></p>

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
            if root is None:
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
        return dfs(root).getKey();
    }

    private Pair<TreeNode, Integer> dfs(TreeNode root) {
        if (root == null) {
            return new Pair<>(null, 0);
        }
        Pair<TreeNode, Integer> l = dfs(root.left);
        Pair<TreeNode, Integer> r = dfs(root.right);
        int d1 = l.getValue(), d2 = r.getValue();
        if (d1 > d2) {
            return new Pair<>(l.getKey(), d1 + 1);
        }
        if (d1 < d2) {
            return new Pair<>(r.getKey(), d2 + 1);
        }
        return new Pair<>(root, d1 + 1);
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
using pti = pair<TreeNode*, int>;
class Solution {
public:
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        return dfs(root).first;
    }

    pti dfs(TreeNode* root) {
        if (!root) return {nullptr, 0};
        pti l = dfs(root->left);
        pti r = dfs(root->right);
        int d1 = l.second, d2 = r.second;
        if (d1 > d2) return {l.first, d1 + 1};
        if (d1 < d2) return {r.first, d2 + 1};
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
type pair struct {
	first  *TreeNode
	second int
}

func subtreeWithAllDeepest(root *TreeNode) *TreeNode {
	var dfs func(root *TreeNode) pair
	dfs = func(root *TreeNode) pair {
		if root == nil {
			return pair{nil, 0}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		d1, d2 := l.second, r.second
		if d1 > d2 {
			return pair{l.first, d1 + 1}
		}
		if d1 < d2 {
			return pair{r.first, d2 + 1}
		}
		return pair{root, d1 + 1}
	}
	return dfs(root).first
}
```

### **...**

```

```

<!-- tabs:end -->
