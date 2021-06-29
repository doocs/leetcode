# [222. 完全二叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes)

[English Version](/solution/0200-0299/0222.Count%20Complete%20Tree%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵<strong> 完全二叉树</strong> 的根节点 <code>root</code> ，求出该树的节点个数。</p>

<p><a href="https://baike.baidu.com/item/%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91/7773232?fr=aladdin">完全二叉树</a> 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 <code>h</code> 层，则该层包含 <code>1~ 2<sup>h</sup></code> 个节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0222.Count%20Complete%20Tree%20Nodes/images/complete.jpg" style="width: 372px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,5,6]
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目范围是<code>[0, 5 * 10<sup>4</sup>]</code></li>
	<li><code>0 <= Node.val <= 5 * 10<sup>4</sup></code></li>
	<li>题目数据保证输入的树是 <strong>完全二叉树</strong></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>遍历树来统计节点是一种时间复杂度为 <code>O(n)</code> 的简单解决方案。你可以设计一个更快的算法吗？</p>


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
    def countNodes(self, root: TreeNode) -> int:
        def depth(root):
            res = 0
            while root:
                res += 1
                root = root.left
            return res

        if root is None:
            return 0
        left_depth = depth(root.left)
        right_depth = depth(root.right)
        if left_depth > right_depth:
            return (1 << right_depth) + self.countNodes(root.left)
        return (1 << left_depth) + self.countNodes(root.right)
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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth > rightDepth) {
            return (1 << rightDepth) + countNodes(root.left);
        }
        return (1 << leftDepth) + countNodes(root.right);
    }

    private int depth(TreeNode root) {
        int res = 0;
        while (root != null) {
            ++res;
            root = root.left;
        }
        return res;
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
    int countNodes(TreeNode* root) {
        if (!root) {
            return 0;
        }
        int leftDepth = depth(root->left);
        int rightDepth = depth(root->right);
        if (leftDepth > rightDepth) {
            return (1 << rightDepth) + countNodes(root->left);
        }
        return (1 << leftDepth) + countNodes(root->right);
    }

private:
    int depth(TreeNode* root) {
        int res = 0;
        while (root) {
            ++res;
            root = root->left;
        }
        return res;
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
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	leftDepth := depth(root.Left)
	rightDepth := depth(root.Right)
	if leftDepth > rightDepth {
		return (1 << rightDepth) + countNodes(root.Left)
	}
	return (1 << leftDepth) + countNodes(root.Right)
}

func depth(root *TreeNode) int {
	res := 0
	for root != nil {
		res++
		root = root.Left
	}
	return res
}
```

### **C#**

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public int CountNodes(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth > rightDepth)
        {
            return (1 << rightDepth) + CountNodes(root.left);
        }
        return (1 << leftDepth) + CountNodes(root.right);
    }

    private int depth(TreeNode root) {
        int res = 0;
        while (root != null)
        {
            ++res;
            root = root.left;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
