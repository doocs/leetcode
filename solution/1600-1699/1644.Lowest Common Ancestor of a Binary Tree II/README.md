# [1644. 二叉树的最近公共祖先 II](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-ii)

[English Version](/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉树的根节点 <code>root</code>，返回给定节点 <code>p</code> 和 <code>q</code> 的最近公共祖先（LCA）节点。如果 <code>p</code> 或 <code>q</code> 之一<strong> 不存在</strong> 于该二叉树中，返回 <code>null</code>。树中的每个节点值都是互不相同的。</p>

<p>根据<a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">维基百科中对最近公共祖先节点的定义</a>：“两个节点 <code>p</code> 和 <code>q</code> 在二叉树 <code>T</code> 中的最近公共祖先节点是<strong> 后代节点 </strong>中既包括 <code>p</code>&nbsp;又包括&nbsp;<code>q</code>&nbsp;的最深节点（我们允许<strong> 一个节点为自身的一个后代节点 </strong>）”。一个节点 <code>x</code>&nbsp;的<strong> 后代节点 </strong>是节点&nbsp;<code>x</code> 到某一叶节点间的路径中的节点 <code>y</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" />
<pre>
<b>输入：</b> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<b>输出：</b> 3
<b>解释：</b> 节点 5 和 1 的共同祖先节点是 3。</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" /></p>

<pre>
<b>输入：</b> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<b>输出：</b> 5
<b>解释：</b> 节点 5 和 4 的共同祖先节点是 5。根据共同祖先节点的定义，一个节点可以是自身的后代节点。</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" /></p>

<pre>
<strong>输入：</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
<b>输出：</b> null
<b>解释：</b> 节点 10 不存在于树中，所以返回 null。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是&nbsp;<code>[1, 10<sup>4</sup>]</code></li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>所有节点的值&nbsp;<code>Node.val</code> <strong>互不相同</strong></li>
	<li><code>p != q</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong> 在不检查节点是否存在的情况下，你可以遍历树找出最近公共祖先节点吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归（后序遍历）**

我们设计一个函数 $dfs(root, p, q)$，该函数返回以 $root$ 为根节点的二叉树中是否包含节点 $p$ 或节点 $q$，如果包含，则返回 `true`，否则返回 `false`。

函数 $dfs(root, p, q)$ 的递归过程如下：

如果当前节点 $root$ 为空，则返回 `false`。

否则，我们递归地遍历左子树和右子树，得到 $l$ 和 $r$，分别表示左子树和右子树中是否包含节点 $p$ 或节点 $q$。

如果 $l$ 和 $r$ 都为 `true`，说明当前节点 $root$ 就是我们要找的最近公共祖先节点，将其赋值给变量 $ans$。

如果 $l$ 或 $r$ 为 `true`，并且当前节点 $root$ 的值等于节点 $p$ 或节点 $q$ 的值，说明当前节点 $root$ 就是我们要找的最近公共祖先节点，将其赋值给变量 $ans$。

最后，我们判断 $l$ 或 $r$ 是否为 `true`，或者当前节点 $root$ 的值是否等于节点 $p$ 或节点 $q$ 的值，如果是，则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

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
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        def dfs(root, p, q):
            if root is None:
                return False
            l = dfs(root.left, p, q)
            r = dfs(root.right, p, q)
            nonlocal ans
            if l and r:
                ans = root
            if (l or r) and (root.val == p.val or root.val == q.val):
                ans = root
            return l or r or root.val == p.val or root.val == q.val

        ans = None
        dfs(root, p, q)
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
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean l = dfs(root.left, p, q);
        boolean r = dfs(root.right, p, q);
        if (l && r) {
            ans = root;
        }
        if ((l || r) && (root.val == p.val || root.val == q.val)) {
            ans = root;
        }
        return l || r || root.val == p.val || root.val == q.val;
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
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        dfs(root, p, q);
        return ans;
    }

private:
    TreeNode* ans = nullptr;

    bool dfs(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root) {
            return false;
        }
        bool l = dfs(root->left, p, q);
        bool r = dfs(root->right, p, q);
        if (l && r) {
            ans = root;
        }
        if ((l || r) && (root->val == p->val || root->val == q->val)) {
            ans = root;
        }
        return l || r || root->val == p->val || root->val == q->val;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
