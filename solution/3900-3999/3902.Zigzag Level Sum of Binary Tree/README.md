---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3902.Zigzag%20Level%20Sum%20of%20Binary%20Tree/README.md
tags:
    - 树
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [3902. 二叉树的 Z 字形层级和 🔒](https://leetcode.cn/problems/zigzag-level-sum-of-binary-tree)

[English Version](/solution/3900-3999/3902.Zigzag%20Level%20Sum%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵 <strong>二叉树</strong> 的根节点&nbsp;<code>root</code>。</p>

<p>按 Z 字形模式逐层遍历树：</p>

<ul>
	<li>在 <strong>奇数</strong> 层（下标从 1 开始）中，<strong>从左到右</strong> 遍历节点。</li>
	<li>在 <strong>偶数</strong> 层，<strong>从右到左</strong> 遍历节点。</li>
</ul>

<p>在指定方向遍历某一层时，按顺序处理节点，并立即在第一个违反条件的节点前 <strong>停止</strong>：</p>

<ul>
	<li>在 <strong>奇数</strong> 层：该节点没有 <strong>左</strong> 子节点。</li>
	<li>在 <strong>偶数</strong> 层：该节点没有 <strong>右</strong> 子节点。</li>
</ul>

<p>只有在此停止条件之前的节点对层级和有贡献。</p>

<p>返回一个整数数组 <code>ans</code>，其中 <code>ans[i]</code> 是在第 <code>i + 1</code> 层处理的节点值之 <strong>和</strong>。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>root = [5,2,8,1,null,9,6]</span></p>

<p><span class="example-io"><b>输出：</b>[5,8,0]</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3902.Zigzag%20Level%20Sum%20of%20Binary%20Tree/images/screenshot-2026-04-13-at-22054am.png" style="height: 240px; width: 300px;" />​​​​​​​</p>

<ul>
	<li>在第 1 层，从左往右处理节点。节点 5 被包含，因此&nbsp;<code>ans[0] = 5</code>。</li>
	<li>在第 2&nbsp;层，从右往左处理节点。节点 8 被包含，但节点 2 缺少一个右儿子，所以处理中断，因此&nbsp;<code>ans[1] = 8</code>。</li>
	<li>在第 3&nbsp;层，从左往右处理节点。第一个节点 1 缺少一个左儿子，因此没有节点被包含，因此&nbsp;<code>ans[2] = 0</code>。</li>
	<li>因此，<code>ans = [5, 8, 0]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>root = [1,2,3,4,5,null,7]</span></p>

<p><span class="example-io"><b>输出：</b>[1,5,0]</span></p>

<p><b>解释：</b></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3902.Zigzag%20Level%20Sum%20of%20Binary%20Tree/images/screenshot-2026-04-13-at-22232am.png" style="height: 254px; width: 300px;" /></p>

<ul>
	<li>在第 1 层，从左往右处理节点。节点 1 被包含，因此&nbsp;<code>ans[0] = 1</code>。</li>
	<li>在第 2 层，从右往左处理节点。节点 3 和 2 被包含，因为它们都有右儿子，因此&nbsp;<code>ans[1] = 3 + 2 = 5</code>。</li>
	<li>在第 3 层，从左到右进行处理节点。第一个节点 4 没有左子节点，因此不包含任何节点，因此&nbsp;<code>ans[2] = 0</code>。</li>
	<li>因此，<code>ans = [1, 5, 0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数范围为 <code>[1, 10<sup>5</sup>]</code>。</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们使用一个队列 $q$ 来进行层序遍历，定义一个布尔变量 $\textit{left}$ 来表示当前层的遍历方向。对于每一层，我们先将下一层的节点加入队列 $nq$ 中，然后根据 $\textit{left}$ 的值来计算当前层的节点值之和 $s$，并将 $s$ 添加到答案数组中。最后，更新 $\textit{left}$ 的值，并将 $nq$ 赋值给 $q$ 以继续下一层的遍历。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树中节点的数量。

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
    def zigzagLevelSum(self, root: TreeNode | None) -> list[int]:
        q = [root]
        ans = []
        left = True
        while q:
            nq = []
            for node in q:
                if node.left:
                    nq.append(node.left)
                if node.right:
                    nq.append(node.right)
            m = len(q)
            s = 0
            for i in range(m):
                node = q[i] if left else q[m - i - 1]
                child = node.left if left else node.right
                if not child:
                    break
                s += node.val
            ans.append(s)
            left = not left
            q = nq
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
    public List<Long> zigzagLevelSum(TreeNode root) {
        List<Long> ans = new ArrayList<>();
        List<TreeNode> q = new ArrayList<>();
        q.add(root);
        boolean left = true;
        while (!q.isEmpty()) {
            List<TreeNode> nq = new ArrayList<>();
            for (TreeNode node : q) {
                if (node.left != null) {
                    nq.add(node.left);
                }
                if (node.right != null) {
                    nq.add(node.right);
                }
            }
            int m = q.size();
            long s = 0;
            for (int i = 0; i < m; i++) {
                TreeNode node = left ? q.get(i) : q.get(m - i - 1);
                TreeNode child = left ? node.left : node.right;
                if (child == null) {
                    break;
                }
                s += node.val;
            }
            ans.add(s);
            left = !left;
            q = nq;
        }
        return ans;
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
    vector<long long> zigzagLevelSum(TreeNode* root) {
        vector<long long> ans;
        vector<TreeNode*> q = {root};
        bool left = true;
        while (!q.empty()) {
            vector<TreeNode*> nq;
            for (TreeNode* node : q) {
                if (node->left != nullptr) {
                    nq.push_back(node->left);
                }
                if (node->right != nullptr) {
                    nq.push_back(node->right);
                }
            }
            int m = q.size();
            long long s = 0;
            for (int i = 0; i < m; i++) {
                TreeNode* node = left ? q[i] : q[m - i - 1];
                TreeNode* child = left ? node->left : node->right;
                if (child == nullptr) {
                    break;
                }
                s += node->val;
            }
            ans.push_back(s);
            left = !left;
            q = nq;
        }
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
func zigzagLevelSum(root *TreeNode) []int64 {
	ans := []int64{}
	q := []*TreeNode{root}
	left := true
	for len(q) > 0 {
		nq := []*TreeNode{}
		for _, node := range q {
			if node.Left != nil {
				nq = append(nq, node.Left)
			}
			if node.Right != nil {
				nq = append(nq, node.Right)
			}
		}
		m := len(q)
		var s int64 = 0
		for i := 0; i < m; i++ {
			var node *TreeNode
			if left {
				node = q[i]
			} else {
				node = q[m-i-1]
			}
			var child *TreeNode
			if left {
				child = node.Left
			} else {
				child = node.Right
			}
			if child == nil {
				break
			}
			s += int64(node.Val)
		}
		ans = append(ans, s)
		left = !left
		q = nq
	}
	return ans
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
function zigzagLevelSum(root: TreeNode | null): number[] {
    let q: TreeNode[] = [root];
    const ans: number[] = [];
    let left = true;
    while (q.length > 0) {
        const nq: TreeNode[] = [];
        for (const { left, right } of q) {
            if (left !== null) {
                nq.push(left);
            }
            if (right !== null) {
                nq.push(right);
            }
        }
        const m = q.length;
        let s = 0;
        for (let i = 0; i < m; i++) {
            const node = left ? q[i] : q[m - i - 1];
            const child = left ? node.left : node.right;
            if (child === null) {
                break;
            }
            s += node.val;
        }
        ans.push(s);
        left = !left;
        q = nq;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
