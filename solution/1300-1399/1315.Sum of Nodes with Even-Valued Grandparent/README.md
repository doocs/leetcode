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

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索。

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
    def sumEvenGrandparent(self, root: TreeNode) -> int:
        self.res = 0

        def dfs(g, p):
            if p is None:
                return
            if g.val % 2 == 0:
                if p.left:
                    self.res += p.left.val
                if p.right:
                    self.res += p.right.val
            dfs(p, p.left)
            dfs(p, p.right)

        dfs(root, root.left)
        dfs(root, root.right)
        return self.res
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
    private int res;

    public int sumEvenGrandparent(TreeNode root) {
        res = 0;
        dfs(root, root.left);
        dfs(root, root.right);
        return res;
    }

    private void dfs(TreeNode g, TreeNode p) {
        if (p == null) {
            return;
        }
        if (g.val % 2 == 0) {
            if (p.left != null) {
                res += p.left.val;
            }
            if (p.right != null) {
                res += p.right.val;
            }
        }
        dfs(p, p.left);
        dfs(p, p.right);
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
    int res;

    int sumEvenGrandparent(TreeNode* root) {
        res = 0;
        dfs(root, root->left);
        dfs(root, root->right);
        return res;
    }

    void dfs(TreeNode* g, TreeNode* p) {
        if (!p) return;
        if (g->val % 2 == 0) {
            if (p->left) res += p->left->val;
            if (p->right) res += p->right->val;
        }
        dfs(p, p->left);
        dfs(p, p->right);
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

var res int

func sumEvenGrandparent(root *TreeNode) int {
	res = 0
	dfs(root, root.Left)
	dfs(root, root.Right)
	return res
}

func dfs(g, p *TreeNode) {
	if p == nil {
		return
	}
	if g.Val%2 == 0 {
		if p.Left != nil {
			res += p.Left.Val
		}
		if p.Right != nil {
			res += p.Right.Val
		}
	}
	dfs(p, p.Left)
	dfs(p, p.Right)
}
```

### **...**

```

```

<!-- tabs:end -->
