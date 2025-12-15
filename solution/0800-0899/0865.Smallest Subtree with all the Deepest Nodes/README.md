---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [865. 具有所有最深节点的最小子树](https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes)

[English Version](/solution/0800-0899/0865.Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们设计一个函数 $\textit{dfs}(\textit{root})$，返回以 $\textit{root}$ 为根的子树中，包含所有最深节点的最小子树，以及以 $\textit{root}$ 为根的子树的深度。

函数 $\textit{dfs}(\textit{root})$ 的执行过程如下：

- 如果 $\textit{root}$ 为空，返回 $\text{null}$ 和 $0$。
- 否则，递归计算 $\textit{root}$ 的左子树和右子树的最小子树以及深度，分别为 $l$ 和 $l_d$ 以及 $r$ 和 $r_d$。如果 $l_d > r_d$，则以 $\textit{root}$ 的左孩子为根的子树中包含所有最深节点的最小子树就是 $l$，深度为 $l_d + 1$；如果 $l_d < r_d$，则以 $\textit{root}$ 的右孩子为根的子树中包含所有最深节点的最小子树就是 $r$，深度为 $r_d + 1$；如果 $l_d = r_d$，则 $\textit{root}$ 就是包含所有最深节点的最小子树，深度为 $l_d + 1$。

最后，返回 $\textit{dfs}(\textit{root})$ 的结果的第一个元素即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def subtreeWithAllDeepest(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs(root: Optional[TreeNode]) -> Tuple[Optional[TreeNode], int]:
            if root is None:
                return None, 0
            l, ld = dfs(root.left)
            r, rd = dfs(root.right)
            if ld > rd:
                return l, ld + 1
            if ld < rd:
                return r, rd + 1
            return root, ld + 1

        return dfs(root)[0]
```

#### Java

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
        var l = dfs(root.left);
        var r = dfs(root.right);
        int ld = l.getValue(), rd = r.getValue();
        if (ld > rd) {
            return new Pair<>(l.getKey(), ld + 1);
        }
        if (ld < rd) {
            return new Pair<>(r.getKey(), rd + 1);
        }
        return new Pair<>(root, ld + 1);
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
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        using pti = pair<TreeNode*, int>;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> pti {
            if (!root) {
                return {nullptr, 0};
            }
            auto [l, ld] = dfs(root->left);
            auto [r, rd] = dfs(root->right);
            if (ld > rd) {
                return {l, ld + 1};
            }
            if (ld < rd) {
                return {r, rd + 1};
            }
            return {root, ld + 1};
        };
        return dfs(root).first;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func subtreeWithAllDeepest(root *TreeNode) *TreeNode {
	type pair struct {
		node  *TreeNode
		depth int
	}
	var dfs func(*TreeNode) pair
	dfs = func(root *TreeNode) pair {
		if root == nil {
			return pair{nil, 0}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		ld, rd := l.depth, r.depth
		if ld > rd {
			return pair{l.node, ld + 1}
		}
		if ld < rd {
			return pair{r.node, rd + 1}
		}
		return pair{root, ld + 1}
	}
	return dfs(root).node
}
```

#### TypeScript

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function subtreeWithAllDeepest(root: TreeNode | null): TreeNode | null {
    const dfs = (root: TreeNode | null): [TreeNode, number] => {
        if (!root) {
            return [null, 0];
        }
        const [l, ld] = dfs(root.left);
        const [r, rd] = dfs(root.right);
        if (ld > rd) {
            return [l, ld + 1];
        }
        if (ld < rd) {
            return [r, rd + 1];
        }
        return [root, ld + 1];
    };
    return dfs(root)[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
