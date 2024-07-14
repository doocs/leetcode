---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [979. 在二叉树中分配硬币](https://leetcode.cn/problems/distribute-coins-in-binary-tree)

[English Version](/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个有 <code>n</code> 个结点的二叉树的根结点 <code>root</code> ，其中树中每个结点 <code>node</code> 都对应有 <code>node.val</code> 枚硬币。整棵树上一共有 <code>n</code> 枚硬币。</p>

<p>在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。移动可以是从父结点到子结点，或者从子结点移动到父结点。</p>

<p>返回使每个结点上 <strong>只有</strong> 一枚硬币所需的 <strong>最少</strong> 移动次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/images/tree1.png" style="width: 250px; height: 236px;" />
<pre>
<strong>输入：</strong>root = [3,0,0]
<strong>输出：</strong>2
<strong>解释：</strong>一枚硬币从根结点移动到左子结点，一枚硬币从根结点移动到右子结点。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/images/tree2.png" style="width: 250px; height: 236px;" />
<pre>
<strong>输入：</strong>root = [0,3,0]
<strong>输出：</strong>3
<strong>解释：</strong>将两枚硬币从根结点的左子结点移动到根结点（两次移动）。然后，将一枚硬币从根结点移动到右子结点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目为 <code>n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= Node.val &lt;= n</code></li>
	<li>所有 <code>Node.val</code> 的值之和是 <code>n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们定义一个函数 $\text{dfs(\text{node})}$，表示以 $\text{node}$ 为根节点的子树中，金币的超载量，即金币的数量减去节点数。如果 $\text{dfs(\text{node})}$ 为正数，表示该子树中金币的数量多于节点数，需要将多余的金币移出该子树；如果 $\text{dfs(\text{node})}$ 为负数，表示该子树中金币的数量少于节点数，需要将不足的金币移入该子树。

在函数 $\text{dfs(\text{node})}$ 中，我们首先遍历左右子树，获得左右子树的金币超载量 $\text{left}$ 和 $\text{right}$。那么当前移动的次数需要加上 $|\text{left}| + |\text{right}|$，即将左右子树中的金币移动到当前节点。然后，我们返回整个子树的金币超载量，即 $\text{left} + \text{right} + \text{node.val} - 1$。

最后返回移动的次数即可。

时间复杂度 $O(n)$，空间复杂度 $O(h)$。其中 $n$ 和 $h$ 分别是二叉树的节点数和高度。

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
    def distributeCoins(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            nonlocal ans
            ans += abs(left) + abs(right)
            return left + right + root.val - 1

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

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
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
    int distributeCoins(TreeNode* root) {
        int ans = 0;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) -> int {
            if (!root) {
                return 0;
            }
            int left = dfs(root->left);
            int right = dfs(root->right);
            ans += abs(left) + abs(right);
            return left + right + root->val - 1;
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
func distributeCoins(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		left, right := dfs(root.Left), dfs(root.Right)
		ans += abs(left) + abs(right)
		return left + right + root.Val - 1
	}
	dfs(root)
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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

function distributeCoins(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return 0;
        }
        const left = dfs(root.left);
        const right = dfs(root.right);
        ans += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
    };
    dfs(root);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
