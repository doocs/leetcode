---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.06.Successor/README.md
---

# [面试题 04.06. 后继者](https://leetcode.cn/problems/successor-lcci)

[English Version](/lcci/04.06.Successor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设计一个算法，找出二叉搜索树中指定节点的&ldquo;下一个&rdquo;节点（也即中序后继）。</p>

<p>如果指定节点没有对应的&ldquo;下一个&rdquo;节点，则返回<code>null</code>。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> root = <code>[2,1,3], p = 1

  2
 / \
1   3
</code>
<strong>输出:</strong> 2</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> root = <code>[5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1
</code>
<strong>输出:</strong> null</pre>

## 解法

### 方法一：二分搜索

二叉搜索树的中序遍历是一个升序序列，因此可以使用二分搜索的方法。

二叉搜索树节点 $p$ 的中序后继节点满足：

1. 中序后继的节点值大于 $p$ 的节点值
2. 中序后继是所有大于 $p$ 的节点中值最小的节点

因此，对于当前节点 $root$，如果 $root.val \gt p.val$，则 $root$ 可能是 $p$ 的中序后继节点，将 $root$ 记为 $ans$，然后搜索左子树，即 $root = root.left$；如果 $root.val \leq p.val$，则 $root$ 不能是 $p$ 的中序后继节点，搜索右子树，即 $root = root.right$。

时间复杂度 $O(h)$，其中 $h$ 为二叉搜索树的高度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> Optional[TreeNode]:
        ans = None
        while root:
            if root.val > p.val:
                ans = root
                root = root.left
            else:
                root = root.right
        return ans
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        while (root != null) {
            if (root.val > p.val) {
                ans = root;
                root = root.left;
            } else {
                root = root.right;
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        TreeNode* ans = nullptr;
        while (root) {
            if (root->val > p->val) {
                ans = root;
                root = root->left;
            } else {
                root = root->right;
            }
        }
        return ans;
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
func inorderSuccessor(root *TreeNode, p *TreeNode) (ans *TreeNode) {
	for root != nil {
		if root.Val > p.Val {
			ans = root
			root = root.Left
		} else {
			root = root.Right
		}
	}
	return
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

function inorderSuccessor(root: TreeNode | null, p: TreeNode | null): TreeNode | null {
    let ans: TreeNode | null = null;
    while (root) {
        if (root.val > p.val) {
            ans = root;
            root = root.left;
        } else {
            root = root.right;
        }
    }
    return ans;
}
```

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
 * @return {TreeNode}
 */
var inorderSuccessor = function (root, p) {
    let ans = null;
    while (root) {
        if (root.val > p.val) {
            ans = root;
            root = root.left;
        } else {
            root = root.right;
        }
    }
    return ans;
};
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
* }
*/

class Solution {
    func inorderSuccessor(_ root: TreeNode?, _ p: TreeNode?) -> TreeNode? {
        var current = root
        var successor: TreeNode? = nil

        while let node = current {
            if node.val > p!.val {
                successor = node
                current = node.left
            } else {
                current = node.right
            }
        }
        return successor
    }
}
```

<!-- tabs:end -->

<!-- end -->
