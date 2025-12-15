---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/README.md
rating: 1472
source: 第 292 场周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [2265. 统计值等于子树平均值的节点数](https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree)

[English Version](/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵二叉树的根节点 <code>root</code> ，找出并返回满足要求的节点数，要求节点的值等于其 <strong>子树</strong> 中值的 <strong>平均值</strong> 。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>n</code> 个元素的平均值可以由 <code>n</code> 个元素 <strong>求和</strong> 然后再除以 <code>n</code> ，并 <strong>向下舍入</strong> 到最近的整数。</li>
	<li><code>root</code> 的 <strong>子树</strong> 由 <code>root</code> 和它的所有后代组成。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/images/image-20220315203925-1.png" style="width: 300px; height: 212px;">
<pre><strong>输入：</strong>root = [4,8,5,0,1,null,6]
<strong>输出：</strong>5
<strong>解释：</strong>
对值为 4 的节点：子树的平均值 (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4 。
对值为 5 的节点：子树的平均值 (5 + 6) / 2 = 11 / 2 = 5 。
对值为 0 的节点：子树的平均值 0 / 1 = 0 。
对值为 1 的节点：子树的平均值 1 / 1 = 1 。
对值为 6 的节点：子树的平均值 6 / 1 = 6 。
</pre>

<p><strong>示例 2：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/images/image-20220326133920-1.png" style="width: 80px; height: 76px;">
<pre><strong>输入：</strong>root = [1]
<strong>输出：</strong>1
<strong>解释：</strong>对值为 1 的节点：子树的平均值 1 / 1 = 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 1000]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $\textit{dfs}$，它的作用是计算以当前节点为根的子树的和以及节点个数。

函数 $\textit{dfs}$ 的执行过程如下：

- 如果当前节点为空，返回 $(0, 0)$。
- 否则，我们递归计算左右子树的和以及节点个数，分别记为 $(\textit{ls}, \textit{ln})$ 和 $(\textit{rs}, \textit{rn})$。那么，以当前节点为根的子树的和 $\textit{s}$ 和节点个数 $\textit{n}$ 分别为 $\textit{ls} + \textit{rs} + \textit{root.val}$ 和 $\textit{ln} + \textit{rn} + 1$。如果 $\textit{s} / \textit{n} = \textit{root.val}$，则说明当前节点满足题目要求，我们将答案 $\textit{ans}$ 自增 $1$。
- 最后，函数 $\textit{dfs}$ 返回 $\textit{s}$ 和 $\textit{n}$。

我们初始化答案 $\textit{ans}$ 为 $0$，然后调用 $\textit{dfs}$ 函数，最后返回答案 $\textit{ans}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 表示二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def averageOfSubtree(self, root: TreeNode) -> int:
        def dfs(root) -> tuple:
            if not root:
                return 0, 0
            ls, ln = dfs(root.left)
            rs, rn = dfs(root.right)
            s = ls + rs + root.val
            n = ln + rn + 1
            nonlocal ans
            ans += int(s // n == root.val)
            return s, n

        ans = 0
        dfs(root)
        return ans
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
    private int ans;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int s = l[0] + r[0] + root.val;
        int n = l[1] + r[1] + 1;
        if (s / n == root.val) {
            ++ans;
        }
        return new int[] {s, n};
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
    int averageOfSubtree(TreeNode* root) {
        int ans = 0;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> pair<int, int> {
            if (!root) {
                return {0, 0};
            }
            auto [ls, ln] = dfs(root->left);
            auto [rs, rn] = dfs(root->right);
            int s = ls + rs + root->val;
            int n = ln + rn + 1;
            if (s / n == root->val) {
                ++ans;
            }
            return {s, n};
        };
        dfs(root);
        return ans;
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
func averageOfSubtree(root *TreeNode) (ans int) {
	var dfs func(root *TreeNode) (int, int)
	dfs = func(root *TreeNode) (int, int) {
		if root == nil {
			return 0, 0
		}
		ls, ln := dfs(root.Left)
		rs, rn := dfs(root.Right)
		s, n := ls+rs+root.Val, ln+rn+1
		if s/n == root.Val {
			ans++
		}
		return s, n
	}
	dfs(root)
	return
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

function averageOfSubtree(root: TreeNode | null): number {
    let ans: number = 0;
    const dfs = (root: TreeNode | null): [number, number] => {
        if (!root) {
            return [0, 0];
        }
        const [ls, ln] = dfs(root.left);
        const [rs, rn] = dfs(root.right);
        const s = ls + rs + root.val;
        const n = ln + rn + 1;
        if (Math.floor(s / n) === root.val) {
            ++ans;
        }
        return [s, n];
    };
    dfs(root);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
