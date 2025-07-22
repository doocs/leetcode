---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.08.First%20Common%20Ancestor/README.md
---

<!-- problem:start -->

# [面试题 04.08. 首个共同祖先](https://leetcode.cn/problems/first-common-ancestor-lcci)

[English Version](/lcci/04.08.First%20Common%20Ancestor/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。</p><p>例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]</p><pre>    3<br>   / &#92<br>  5   1<br> / &#92 / &#92<br>6  2 0  8<br>  / &#92<br> 7   4<br></pre><strong>示例 1:</strong><pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1<br><strong>输入:</strong> 3<br><strong>解释:</strong> 节点 5 和节点 1 的最近公共祖先是节点 3。</pre><strong>示例 2:</strong><pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4<br><strong>输出:</strong> 5<br><strong>解释:</strong> 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。</pre><strong>说明:</strong><pre>所有节点的值都是唯一的。<br>p、q 为不同节点且均存在于给定的二叉树中。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们首先判断根节点是否为空，或者根节点是否等于 $\textit{p}$ 或 $\textit{q}$，如果是的话，直接返回根节点。

然后递归地对左右子树进行查找，分别得到 $\textit{left}$ 和 $\textit{right}$。如果 $\textit{left}$ 和 $\textit{right}$ 都不为空，说明 $\textit{p}$ 和 $\textit{q}$ 分别在左右子树中，那么根节点就是最近公共祖先。否则，如果 $\textit{left}$ 和 $\textit{right}$ 中有一个为空，说明 $\textit{p}$ 和 $\textit{q}$ 都在非空的子树中，那么非空的子树的根节点就是最近公共祖先。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树中节点的数目。

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
        self, root: TreeNode, p: TreeNode, q: TreeNode
    ) -> TreeNode:
        if root is None or root in [p, q]:
            return root
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        return root if left and right else left or right
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : (right == null ? left : root);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root || root == p || root == q) {
            return root;
        }
        TreeNode* left = lowestCommonAncestor(root->left, p, q);
        TreeNode* right = lowestCommonAncestor(root->right, p, q);
        return left && right ? root : (left ? left : right);
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
func lowestCommonAncestor(root *TreeNode, p *TreeNode, q *TreeNode) *TreeNode {
	if root == nil || root == p || root == q {
		return root
	}
	left := lowestCommonAncestor(root.Left, p, q)
	right := lowestCommonAncestor(root.Right, p, q)
	if left == nil {
		return right
	}
	if right == nil {
		return left
	}
	return root
}
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
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {TreeNode}
 */
var lowestCommonAncestor = function (root, p, q) {
    if (!root || root === p || root === q) {
        return root;
    }
    const left = lowestCommonAncestor(root.left, p, q);
    const right = lowestCommonAncestor(root.right, p, q);
    return left && right ? root : left || right;
};
```

#### Swift

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
* }
*/

class Solution {
    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
        if root == nil || root === p || root === q {
            return root
        }
        let left = lowestCommonAncestor(root?.left, p, q)
        let right = lowestCommonAncestor(root?.right, p, q)
        if left == nil {
            return right
        } else if right == nil {
            return left
        } else {
            return root
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
