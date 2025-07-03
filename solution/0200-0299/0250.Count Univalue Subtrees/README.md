---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0250.Count%20Univalue%20Subtrees/README.md
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [250. 统计同值子树 🔒](https://leetcode.cn/problems/count-univalue-subtrees)

[English Version](/solution/0200-0299/0250.Count%20Univalue%20Subtrees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二叉树，统计该二叉树数值相同的 <span data-keyword="subtree">子树</span> 个数。</p>

<p>同值子树是指该子树的所有节点都拥有相同的数值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0250.Count%20Univalue%20Subtrees/images/unival_e1.jpg" style="width: 450px; height: 258px;" />
<pre>
<strong>输入：</strong>root = [5,1,5,5,5,null,5]
<b>输出：</b>4
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>root = []
<b>输出：</b>0
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>root = [5,5,5,5,5,null,5]
<b>输出：</b>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的编号在 <code>[0, 1000]</code>&nbsp;范围内</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们设计一个递归函数 $dfs(root)$，该函数返回以 $root$ 为根的子树中所有节点的值是否相同。

函数 $dfs(root)$ 的递归过程如下：

-   如果 $root$ 为空，则返回 `true`；
-   否则，我们递归地计算 $root$ 的左右子树，记为 $l$ 和 $r$；如果 $l$ 为 `false` 或者 $r$ 为 `false`，则返回 `false`；如果 $root$ 的左子树不为空且 $root$ 的左子树的值不等于 $root$ 的值，或者 $root$ 的右子树不为空且 $root$ 的右子树的值不等于 $root$ 的值，则返回 `false`；否则，我们将答案加一，并返回 `true`。

递归结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

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
    def countUnivalSubtrees(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return True
            l, r = dfs(root.left), dfs(root.right)
            if not l or not r:
                return False
            a = root.val if root.left is None else root.left.val
            b = root.val if root.right is None else root.right.val
            if a == b == root.val:
                nonlocal ans
                ans += 1
                return True
            return False

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

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean l = dfs(root.left);
        boolean r = dfs(root.right);
        if (!l || !r) {
            return false;
        }
        int a = root.left == null ? root.val : root.left.val;
        int b = root.right == null ? root.val : root.right.val;
        if (a == b && b == root.val) {
            ++ans;
            return true;
        }
        return false;
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
    int countUnivalSubtrees(TreeNode* root) {
        int ans = 0;
        function<bool(TreeNode*)> dfs = [&](TreeNode* root) -> bool {
            if (!root) {
                return true;
            }
            bool l = dfs(root->left);
            bool r = dfs(root->right);
            if (!l || !r) {
                return false;
            }
            int a = root->left ? root->left->val : root->val;
            int b = root->right ? root->right->val : root->val;
            if (a == b && b == root->val) {
                ++ans;
                return true;
            }
            return false;
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
func countUnivalSubtrees(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if !l || !r {
			return false
		}
		if root.Left != nil && root.Left.Val != root.Val {
			return false
		}
		if root.Right != nil && root.Right.Val != root.Val {
			return false
		}
		ans++
		return true
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

function countUnivalSubtrees(root: TreeNode | null): number {
    let ans: number = 0;
    const dfs = (root: TreeNode | null): boolean => {
        if (root == null) {
            return true;
        }
        const l: boolean = dfs(root.left);
        const r: boolean = dfs(root.right);
        if (!l || !r) {
            return false;
        }
        if (root.left != null && root.left.val != root.val) {
            return false;
        }
        if (root.right != null && root.right.val != root.val) {
            return false;
        }
        ++ans;
        return true;
    };
    dfs(root);
    return ans;
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
 * @return {number}
 */
var countUnivalSubtrees = function (root) {
    let ans = 0;
    const dfs = root => {
        if (!root) {
            return true;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (!l || !r) {
            return false;
        }
        if (root.left && root.left.val !== root.val) {
            return false;
        }
        if (root.right && root.right.val !== root.val) {
            return false;
        }
        ++ans;
        return true;
    };
    dfs(root);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
