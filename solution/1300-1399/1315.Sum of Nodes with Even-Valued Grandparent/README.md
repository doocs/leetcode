---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/README.md
rating: 1426
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

# [1315. 祖父节点值为偶数的节点和](https://leetcode.cn/problems/sum-of-nodes-with-even-valued-grandparent)

[English Version](/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：</p>

<ul>
	<li>该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）</li>
</ul>

<p>如果不存在祖父节点值为偶数的节点，那么返回&nbsp;<code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1315.Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/images/1473_ex1.png" style="height: 214px; width: 350px;"></strong></p>

<pre><strong>输入：</strong>root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
<strong>输出：</strong>18
<strong>解释：</strong>图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在&nbsp;<code>1</code> 到&nbsp;<code>10^4</code>&nbsp;之间。</li>
	<li>每个节点的值在&nbsp;<code>1</code> 到&nbsp;<code>100</code> 之间。</li>
</ul>

## 解法

### 方法一：DFS

我们设计一个函数 $dfs(root, x)$，表示以 $root$ 为根节点，并且 $root$ 的父节点的值为 $x$ 的子树中，满足条件的节点的值之和。那么答案就是 $dfs(root, 1)$。

函数 $dfs(root, x)$ 的执行过程如下：

-   如果 $root$ 为空，返回 $0$。
-   否则，我们递归计算 $root$ 的左子树和右子树的答案，即 $dfs(root.left, root.val)$ 和 $dfs(root.right, root.val)$，累加到答案中。如果 $x$ 为偶数，此时我们判断 $root$ 的左孩子和右孩子是否存在，如果存在，我们将它们的值累加到答案中。
-   最后返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumEvenGrandparent(self, root: TreeNode) -> int:
        def dfs(root: TreeNode, x: int) -> int:
            if root is None:
                return 0
            ans = dfs(root.left, root.val) + dfs(root.right, root.val)
            if x % 2 == 0:
                if root.left:
                    ans += root.left.val
                if root.right:
                    ans += root.right.val
            return ans

        return dfs(root, 1)
```

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
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, 1);
    }

    private int dfs(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
        int ans = dfs(root.left, root.val) + dfs(root.right, root.val);
        if (x % 2 == 0) {
            if (root.left != null) {
                ans += root.left.val;
            }
            if (root.right != null) {
                ans += root.right.val;
            }
        }
        return ans;
    }
}
```

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
    int sumEvenGrandparent(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int x) {
            if (!root) {
                return 0;
            }
            int ans = dfs(root->left, root->val) + dfs(root->right, root->val);
            if (x % 2 == 0) {
                if (root->left) {
                    ans += root->left->val;
                }
                if (root->right) {
                    ans += root->right->val;
                }
            }
            return ans;
        };
        return dfs(root, 1);
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumEvenGrandparent(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, x int) int {
		if root == nil {
			return 0
		}
		ans := dfs(root.Left, root.Val) + dfs(root.Right, root.Val)
		if x%2 == 0 {
			if root.Left != nil {
				ans += root.Left.Val
			}
			if root.Right != nil {
				ans += root.Right.Val
			}
		}
		return ans
	}
	return dfs(root, 1)
}
```

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

function sumEvenGrandparent(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, x: number): number => {
        if (!root) {
            return 0;
        }
        const { val, left, right } = root;
        let ans = dfs(left, val) + dfs(right, val);
        if (x % 2 === 0) {
            ans += left?.val ?? 0;
            ans += right?.val ?? 0;
        }
        return ans;
    };
    return dfs(root, 1);
}
```

<!-- tabs:end -->

<!-- end -->
