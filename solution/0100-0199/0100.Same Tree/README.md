# [100. 相同的树](https://leetcode-cn.com/problems/same-tree)

[English Version](/solution/0100-0199/0100.Same%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两棵二叉树的根节点 <code>p</code> 和 <code>q</code> ，编写一个函数来检验这两棵树是否相同。</p>

<p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex1.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2,3], q = [1,2,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex2.jpg" style="width: 382px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2], q = [1,null,2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex3.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2,1], q = [1,1,2]
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>两棵树上的节点数目都在范围 <code>[0, 100]</code> 内</li>
	<li><code>-10<sup>4</sup> <= Node.val <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if p == q:
            return True
        if p is None or q is None or p.val != q.val:
            return False
        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if (p == q) return true;
        if (!p || !q || p->val != q->val) return false;
        return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
    }
};
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
func isSameTree(p *TreeNode, q *TreeNode) bool {
    if p == q {
        return true
    }
    if p == nil || q == nil || p.Val != q.Val {
        return false
    }
    return isSameTree(p.Left, q.Left) && isSameTree(p.Right, q.Right)
}
```

### **JavaScript**

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
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {boolean}
 */
var isSameTree = function (p, q) {
    if (!p && !q) return true;
    if (p && q) {
        return (
            p.val === q.val &&
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right)
        );
    }
    return false;
};
```

### **...**

```

```

<!-- tabs:end -->
