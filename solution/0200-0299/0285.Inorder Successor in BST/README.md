# [285. 二叉搜索树中的中序后继](https://leetcode.cn/problems/inorder-successor-in-bst)

[English Version](/solution/0200-0299/0285.Inorder%20Successor%20in%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉搜索树和其中的一个节点 <code>p</code> ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 <code>null</code> 。</p>

<p>节点 <code>p</code> 的后继是值比 <code>p.val</code> 大的节点中键值最小的节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0285.Inorder%20Successor%20in%20BST/images/285_example_1.png" style="height: 117px; width: 122px;" /></p>

<pre>
<strong>输入：</strong>root = [2,1,3], p = 1
<strong>输出：</strong>2
<strong>解释：</strong>这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0285.Inorder%20Successor%20in%20BST/images/285_example_2.png" style="height: 229px; width: 246px;" /></p>

<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,null,1], p = 6
<strong>输出：</strong>null
<strong>解释：</strong>因为给出的节点没有中序后继，所以答案就返回 <code>null 了。</code>
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内。</li>
	<li><code>-10<sup>5</sup> <= Node.val <= 10<sup>5</sup></code></li>
	<li>树中各节点的值均保证唯一。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用二叉搜索树的特性，`p` 的中序后继一定是所有大于 `p` 的节点中最小的那个

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def inorderSuccessor(self, root: 'TreeNode', p: 'TreeNode') -> 'TreeNode':
        cur, ans = root, None
        while cur:
            if cur.val <= p.val:
                cur = cur.right
            else:
                ans = cur
                cur = cur.left
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        TreeNode cur = root, ans = null;
        while (cur != null) {
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                ans = cur;
                cur = cur.left;
            }
        }
        return ans;
    }
}
```

### **Go**

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
	cur := root
	for cur != nil {
		if cur.Val <= p.Val {
			cur = cur.Right
		} else {
			ans = cur
			cur = cur.Left
		}
	}
	return
}
```

### **C++**

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
        TreeNode *cur = root, *ans = nullptr;
        while (cur != nullptr) {
            if (cur->val <= p->val) {
                cur = cur->right;
            } else {
                ans = cur;
                cur = cur->left;
            }
        }
        return ans;
    }
};
```

### **JavaScript**

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
    let cur = root;
    let ans = null;
    while (cur != null) {
        if (cur.val <= p.val) {
            cur = cur.right;
        } else {
            ans = cur;
            cur = cur.left;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
