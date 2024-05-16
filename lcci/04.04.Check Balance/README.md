---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.04.Check%20Balance/README.md
---

<!-- problem:start -->

# [面试题 04.04. 检查平衡性](https://leetcode.cn/problems/check-balance-lcci)

[English Version](/lcci/04.04.Check%20Balance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。</p><br><strong>示例 1:</strong><pre>给定二叉树 [3,9,20,null,null,15,7]<br>    3<br>   / &#92<br>  9  20<br>    /  &#92<br>   15   7<br>返回 true 。</pre><strong>示例 2:</strong><br><pre>给定二叉树 [1,2,2,3,3,null,null,4,4]<br>      1<br>     / &#92<br>    2   2<br>   / &#92<br>  3   3<br> / &#92<br>4   4<br>返回 false 。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归（后序遍历）

我们设计一个函数 $dfs(root)$，它的作用是返回以 $root$ 为根节点的树的高度，如果以 $root$ 为根节点的树是平衡树，则返回树的高度，否则返回 $-1$。

函数 $dfs(root)$ 的执行逻辑如下：

-   如果 $root$ 为空，则返回 $0$；
-   否则，我们递归调用 $dfs(root.left)$ 和 $dfs(root.right)$，并判断 $dfs(root.left)$ 和 $dfs(root.right)$ 的返回值是否为 $-1$，如果不为 $-1$，则判断 $abs(dfs(root.left) - dfs(root.right)) \leq 1$ 是否成立，如果成立，则返回 $max(dfs(root.left), dfs(root.right)) + 1$，否则返回 $-1$。

在主函数中，我们只需要调用 $dfs(root)$，并判断其返回值是否为 $-1$，如果不为 $-1$，则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        def dfs(root: TreeNode):
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            if l == -1 or r == -1 or abs(l - r) > 1:
                return -1
            return max(l, r) + 1

        return dfs(root) >= 0
```

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
    public boolean isBalanced(TreeNode root) {
        return dfs(root) >= 0;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (l < 0 || r < 0 || Math.abs(l - r) > 1) {
            return -1;
        }
        return Math.max(l, r) + 1;
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isBalanced(TreeNode* root) {
        function<int(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return 0;
            }
            int l = dfs(root->left);
            int r = dfs(root->right);
            if (l == -1 || r == -1 || abs(l - r) > 1) {
                return -1;
            }
            return max(l, r) + 1;
        };
        return dfs(root) >= 0;
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
func isBalanced(root *TreeNode) bool {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l == -1 || r == -1 || abs(l-r) > 1 {
			return -1
		}
		return max(l, r) + 1
	}
	return dfs(root) >= 0
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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

function isBalanced(root: TreeNode | null): boolean {
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (l === -1 || r === -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return Math.max(l, r) + 1;
    };
    return dfs(root) >= 0;
}
```

```swift
/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*
*    init(_ val: Int) {
*        self.val = val
*        self.left = nil
*        self.right = nil
*    }
*  }
*/

class Solution {
    func isBalanced(_ root: TreeNode?) -> Bool {
        return dfs(root) >= 0
    }

    private func dfs(_ root: TreeNode?) -> Int {
        guard let root = root else {
            return 0
        }

        let leftHeight = dfs(root.left)
        let rightHeight = dfs(root.right)
        if leftHeight < 0 || rightHeight < 0 || abs(leftHeight - rightHeight) > 1 {
            return -1
        }
        return max(leftHeight, rightHeight) + 1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
