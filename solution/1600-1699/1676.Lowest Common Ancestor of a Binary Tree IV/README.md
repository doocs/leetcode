---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [1676. 二叉树的最近公共祖先 IV 🔒](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iv)

[English Version](/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树的根节点 <code>root</code> 和 <code>TreeNode</code> 类对象的数组（列表） <code>nodes</code>，返回<em> </em><code>nodes</code> 中所有节点的最近公共祖先（LCA）。数组（列表）中所有节点都存在于该二叉树中，且二叉树中所有节点的值都是互不相同的。</p>

<p>我们扩展<a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">二叉树的最近公共祖先节点在维基百科上的定义</a>：“对于任意合理的 <code>i</code> 值， <code>n</code> 个节点 <code>p<sub>1</sub></code> 、 <code>p<sub>2</sub></code>、...、 <code>p<sub>n</sub></code> 在二叉树 <code>T</code> 中的最近公共祖先节点是<strong>后代</strong>中包含所有节点 <code>p<sub>i</sub></code> 的最深节点（我们允许一个节点是其自身的后代）”。一个节点 <code>x</code> 的后代节点是节点 <code>x</code> 到某一叶节点间的路径中的节点 <code>y</code>。</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
<strong>输出:</strong> 2
<strong>解释:</strong> 节点 4 和 7 的最近公共祖先是 2。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
<strong>输出:</strong> 1
<strong>解释:</strong> 单个节点的最近公共祖先是该节点本身。

</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
<strong>输出:</strong> 5
<strong>解释:</strong> 节点 7、6、2 和 4 的最近公共祖先节点是 5。
</pre>

<p><strong>示例 4:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8]
<strong>输出:</strong> 3
<strong>解释:</strong> 树中所有节点的最近公共祖先是根节点。
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是 <code>[1, 10<sup>4</sup>]</code> 。</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>所有的 <code>Node.val</code> 都是<strong>互不相同</strong>的。</li>
	<li>所有的 <code>nodes[i]</code> 都存在于该树中。</li>
	<li>所有的 <code>nodes[i]</code> 都是互不相同的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + DFS

<!-- thinking:start -->

> **思考**
>
> 求一组结点的 LCA，结点保证都在树中。把目标放进集合后，标准树 LCA 递归仍然成立：子树返回命中的目标或已算出的祖先。
>
> 当前结点本身是目标则直接返回；左右都非空则当前为祖先，否则传递非空一侧。

<!-- thinking:end -->

我们用一个哈希表 $\textit{s}$ 记录数组 $\textit{nodes}$ 中所有节点的值，然后使用深度优先搜索，当遍历到的节点为空或者节点的值在哈希表 $\textit{s}$ 中时，返回当前节点。否则，递归遍历左右子树，如果左右子树的返回值都不为空，说明当前节点就是最近公共祖先，否则返回不为空的那个子树的返回值。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是二叉树的节点数和数组 $\textit{nodes}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def lowestCommonAncestor(
        self, root: 'TreeNode', nodes: 'List[TreeNode]'
    ) -> 'TreeNode':
        def dfs(root):
            if root is None or root.val in s:
                return root
            left, right = dfs(root.left), dfs(root.right)
            if left and right:
                return root
            return left or right

        s = {node.val for node in nodes}
        return dfs(root)
```

#### Java

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
    private Set<Integer> s = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node : nodes) {
            s.add(node.val);
        }
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null || s.contains(root.val)) {
            return root;
        }
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}
```

#### C++

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
    TreeNode* lowestCommonAncestor(TreeNode* root, vector<TreeNode*>& nodes) {
        unordered_set<int> s;
        for (auto node : nodes) {
            s.insert(node->val);
        }
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> TreeNode* {
            if (!root || s.contains(root->val)) {
                return root;
            }
            auto left = dfs(root->left);
            auto right = dfs(root->right);
            if (!left) {
                return right;
            }
            if (!right) {
                return left;
            }
            return root;
        };
        return dfs(root);
    }
};
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode[]} nodes
 * @return {TreeNode}
 */
var lowestCommonAncestor = function (root, nodes) {
    const s = new Set();
    for (const node of nodes) {
        s.add(node.val);
    }
    function dfs(root) {
        if (!root || s.has(root.val)) {
            return root;
        }
        const [left, right] = [dfs(root.left), dfs(root.right)];
        if (left && right) {
            return root;
        }
        return left || right;
    }
    return dfs(root);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
