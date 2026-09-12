---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [3157. 找到具有最小和的树的层数 🔒](https://leetcode.cn/problems/find-the-level-of-tree-with-minimum-sum)

[English Version](/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树的根&nbsp;<code>root</code>，其中每个节点有一个值，返回树中 <strong>层和最小</strong> 的层数（如果相等，返回&nbsp;<strong>最低</strong>&nbsp;的层数）。</p>

<p><strong>注意</strong>&nbsp;树的根节点在第一层，其它任何节点的层数是它到根节点的距离+1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">root = [50,6,2,30,80,7]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/images/image_2024-05-17_16-15-46.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 265px; height: 129px;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">root = [36,17,10,null,null,24]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/images/image_2024-05-17_16-14-18.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 170px; height: 135px;" /></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">root = [5,null,5,null,5]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/images/image_2024-05-19_19-07-20.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 170px; height: 135px;" /></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数量的范围是&nbsp;<code>[1, 10<sup>5</sup>]</code>。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

<!-- thinking:start -->

> **思考**
>
> 求节点值和最小的一层（层号从 $1$ 计，相同时取更浅）。DFS 分层累加需要额外下标，BFS 更贴层序。
>
> 一层的和只在扫完该层后才完整，可同时记下最小和与对应层号。
>
> 队列按层扩展，层和更优则更新答案。每个节点入队一次。

<!-- thinking:end -->

我们可以使用 BFS，逐层遍历二叉树，记录每一层的节点值之和，找到具有最小节点值之和的层，返回该层的层数。

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
    def minimumLevel(self, root: Optional[TreeNode]) -> int:
        q = deque([root])
        ans = 0
        level, s = 1, inf
        while q:
            t = 0
            for _ in range(len(q)):
                node = q.popleft()
                t += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if s > t:
                s = t
                ans = level
            level += 1
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
    public int minimumLevel(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        long s = Long.MAX_VALUE;
        for (int level = 1; !q.isEmpty(); ++level) {
            long t = 0;
            for (int m = q.size(); m > 0; --m) {
                TreeNode node = q.poll();
                t += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (s > t) {
                s = t;
                ans = level;
            }
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
    int minimumLevel(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        int ans = 0;
        long long s = 1LL << 60;
        for (int level = 1; q.size(); ++level) {
            long long t = 0;
            for (int m = q.size(); m; --m) {
                TreeNode* node = q.front();
                q.pop();
                t += node->val;
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            if (s > t) {
                s = t;
                ans = level;
            }
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
func minimumLevel(root *TreeNode) (ans int) {
	q := []*TreeNode{root}
	s := math.MaxInt64
	for level := 1; len(q) > 0; level++ {
		t := 0
		for m := len(q); m > 0; m-- {
			node := q[0]
			q = q[1:]
			t += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if s > t {
			s = t
			ans = level
		}
	}
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

function minimumLevel(root: TreeNode | null): number {
    const q: TreeNode[] = [root];
    let s = Infinity;
    let ans = 0;
    for (let level = 1; q.length; ++level) {
        const qq: TreeNode[] = [];
        let t = 0;
        for (const { val, left, right } of q) {
            t += val;
            left && qq.push(left);
            right && qq.push(right);
        }
        if (s > t) {
            s = t;
            ans = level;
        }
        q.splice(0, q.length, ...qq);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
