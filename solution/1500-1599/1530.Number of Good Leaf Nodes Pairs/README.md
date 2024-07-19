---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/README.md
rating: 1745
source: 第 199 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1530. 好叶子节点对的数量](https://leetcode.cn/problems/number-of-good-leaf-nodes-pairs)

[English Version](/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你二叉树的根节点 <code>root</code> 和一个整数 <code>distance</code> 。</p>

<p>如果二叉树中两个 <strong>叶</strong> 节点之间的 <strong>最短路径长度</strong> 小于或者等于 <code>distance</code> ，那它们就可以构成一组 <strong>好叶子节点对</strong> 。</p>

<p>返回树中 <strong>好叶子节点对的数量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/images/e1.jpg" style="height: 321px; width: 321px;"></p>

<pre><strong>输入：</strong>root = [1,2,3,null,4], distance = 3
<strong>输出：</strong>1
<strong>解释：</strong>树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/images/e2.jpg" style="height: 321px; width: 441px;"></p>

<pre><strong>输入：</strong>root = [1,2,3,4,5,6,7], distance = 3
<strong>输出：</strong>2
<strong>解释：</strong>好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。但是叶子节点对 [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
<strong>输出：</strong>1
<strong>解释：</strong>唯一的好叶子节点对是 [2,5] 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root = [100], distance = 1
<strong>输出：</strong>0
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>root = [1,1,1], distance = 2
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>tree</code> 的节点数在 <code>[1, 2^10]</code> 范围内。</li>
	<li>每个节点的值都在 <code>[1, 100]</code> 之间。</li>
	<li><code>1 &lt;= distance &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

题目求一个二叉树好叶子节点的对数，答案可以拆分为三部分之和：左子树好叶子节点的对数、右子树好叶子节点的对数，以及左子树叶子节点与右子树叶子节点组成的好叶子节点的对数。

递归求解即可。

时间复杂度 $O(n \times distance^2 \times h)$，其中 $n$ 是二叉树的节点数，而 $h$ 是二叉树的高度。

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
    def countPairs(self, root: TreeNode, distance: int) -> int:
        def dfs(root, cnt, i):
            if root is None or i >= distance:
                return
            if root.left is None and root.right is None:
                cnt[i] += 1
                return
            dfs(root.left, cnt, i + 1)
            dfs(root.right, cnt, i + 1)

        if root is None:
            return 0
        ans = self.countPairs(root.left, distance) + self.countPairs(
            root.right, distance
        )
        cnt1 = Counter()
        cnt2 = Counter()
        dfs(root.left, cnt1, 1)
        dfs(root.right, cnt2, 1)

        for k1, v1 in cnt1.items():
            for k2, v2 in cnt2.items():
                if k1 + k2 <= distance:
                    ans += v1 * v2
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
    public int countPairs(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        int ans = countPairs(root.left, distance) + countPairs(root.right, distance);
        int[] cnt1 = new int[distance];
        int[] cnt2 = new int[distance];
        dfs(root.left, cnt1, 1);
        dfs(root.right, cnt2, 1);
        for (int i = 0; i < distance; ++i) {
            for (int j = 0; j < distance; ++j) {
                if (i + j <= distance) {
                    ans += cnt1[i] * cnt2[j];
                }
            }
        }
        return ans;
    }

    void dfs(TreeNode root, int[] cnt, int i) {
        if (root == null || i >= cnt.length) {
            return;
        }
        if (root.left == null && root.right == null) {
            ++cnt[i];
            return;
        }
        dfs(root.left, cnt, i + 1);
        dfs(root.right, cnt, i + 1);
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
    int countPairs(TreeNode* root, int distance) {
        if (!root) return 0;
        int ans = countPairs(root->left, distance) + countPairs(root->right, distance);
        vector<int> cnt1(distance);
        vector<int> cnt2(distance);
        dfs(root->left, cnt1, 1);
        dfs(root->right, cnt2, 1);
        for (int i = 0; i < distance; ++i) {
            for (int j = 0; j < distance; ++j) {
                if (i + j <= distance) {
                    ans += cnt1[i] * cnt2[j];
                }
            }
        }
        return ans;
    }

    void dfs(TreeNode* root, vector<int>& cnt, int i) {
        if (!root || i >= cnt.size()) return;
        if (!root->left && !root->right) {
            ++cnt[i];
            return;
        }
        dfs(root->left, cnt, i + 1);
        dfs(root->right, cnt, i + 1);
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
func countPairs(root *TreeNode, distance int) int {
	if root == nil {
		return 0
	}
	ans := countPairs(root.Left, distance) + countPairs(root.Right, distance)
	cnt1 := make([]int, distance)
	cnt2 := make([]int, distance)
	dfs(root.Left, cnt1, 1)
	dfs(root.Right, cnt2, 1)
	for i, v1 := range cnt1 {
		for j, v2 := range cnt2 {
			if i+j <= distance {
				ans += v1 * v2
			}
		}
	}
	return ans
}

func dfs(root *TreeNode, cnt []int, i int) {
	if root == nil || i >= len(cnt) {
		return
	}
	if root.Left == nil && root.Right == nil {
		cnt[i]++
		return
	}
	dfs(root.Left, cnt, i+1)
	dfs(root.Right, cnt, i+1)
}
```

#### TypeScript

```ts
function countPairs(root: TreeNode | null, distance: number): number {
    const pairs: number[][] = [];

    const dfs = (node: TreeNode | null): number[][] => {
        if (!node) return [];
        if (!node.left && !node.right) return [[node.val, 1]];

        const left = dfs(node.left);
        const right = dfs(node.right);

        for (const [x, dx] of left) {
            for (const [y, dy] of right) {
                if (dx + dy <= distance) {
                    pairs.push([x, y]);
                }
            }
        }

        const res: number[][] = [];
        for (const arr of [left, right]) {
            for (const x of arr) {
                if (++x[1] <= distance) res.push(x);
            }
        }

        return res;
    };

    dfs(root);

    return pairs.length;
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} distance
 * @return {number}
 */
var countPairs = function (root, distance) {
    const pairs = [];

    const dfs = node => {
        if (!node) return [];
        if (!node.left && !node.right) return [[node.val, 1]];

        const left = dfs(node.left);
        const right = dfs(node.right);

        for (const [x, dx] of left) {
            for (const [y, dy] of right) {
                if (dx + dy <= distance) {
                    pairs.push([x, y]);
                }
            }
        }

        const res = [];
        for (const arr of [left, right]) {
            for (const x of arr) {
                if (++x[1] <= distance) res.push(x);
            }
        }

        return res;
    };

    dfs(root);

    return pairs.length;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
