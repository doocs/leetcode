# [298. 二叉树最长连续序列](https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence)

[English Version](/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵指定的二叉树的根节点 <code>root</code> ，请你计算其中 <strong>最长连续序列路径</strong> 的长度。</p>

<p><strong>最长连续序列路径</strong> 是依次递增 1 的路径。该路径，可以是从某个初始节点到树中任意节点，通过「父 - 子」关系连接而产生的任意路径。且必须从父节点到子节点，反过来是不可以的。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/images/consec1-1-tree.jpg" style="width: 306px; height: 400px;" />
<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,null,null,5]
<strong>输出：</strong>3
<strong>解释：</strong>当中，最长连续序列是 <code>3-4-5 ，所以</code>返回结果为 <code>3 。</code>
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0298.Binary%20Tree%20Longest%20Consecutive%20Sequence/images/consec1-2-tree.jpg" style="width: 249px; height: 400px;" />
<pre>
<strong>输入：</strong>root = [2,null,3,2,null,1]
<strong>输出：</strong>2
<strong>解释：</strong>当中，最长连续序列是 <code>2-3 。注意，不是</code> <code>3-2-1，所以</code>返回 <code>2 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 3 * 10<sup>4</sup>]</code> 内</li>
	<li><code>-3 * 10<sup>4</sup> &lt;= Node.val &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

我们设计一个函数 $dfs(root)$，表示以 $root$ 为连续序列的第一个节点的最长连续序列路径长度。

函数 $dfs(root)$ 的执行过程如下：

如果 $root$ 为空，那么返回 $0$。

否则，我们递归计算 $root$ 的左右子节点，分别得到 $l$ 和 $r$，如果 $root$ 的左子节点和 $root$ 连续，那么 $l$ 的值加 $1$，否则置 $l$ 为 $1$；如果 $root$ 的右子节点和 $root$ 连续，那么 $r$ 的值加 $1$，否则置 $r$ 为 $1$。

然后我们更新答案为 $ans = \max(ans, l, r)$，并返回 $\max(l, r)$。

最后，我们调用 $dfs(root)$，返回答案 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    def longestConsecutive(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            l = dfs(root.left) + 1
            r = dfs(root.right) + 1
            if root.left and root.left.val - root.val != 1:
                l = 1
            if root.right and root.right.val - root.val != 1:
                r = 1
            t = max(l, r)
            nonlocal ans
            ans = max(ans, t)
            return t

        ans = 0
        dfs(root)
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

    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left) + 1;
        int r = dfs(root.right) + 1;
        if (root.left != null && root.left.val - root.val != 1) {
            l = 1;
        }
        if (root.right != null && root.right.val - root.val != 1) {
            r = 1;
        }
        int t = Math.max(l, r);
        ans = Math.max(ans, t);
        return t;
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
    int longestConsecutive(TreeNode* root) {
        int ans = 0;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return 0;
            }
            int l = dfs(root->left) + 1;
            int r = dfs(root->right) + 1;
            if (root->left && root->left->val - root->val != 1) {
                l = 1;
            }
            if (root->right && root->right->val - root->val != 1) {
                r = 1;
            }
            int t = max(l, r);
            ans = max(ans, t);
            return t;
        };
        dfs(root);
        return ans;
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
func longestConsecutive(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l := dfs(root.Left) + 1
		r := dfs(root.Right) + 1
		if root.Left != nil && root.Left.Val-root.Val != 1 {
			l = 1
		}
		if root.Right != nil && root.Right.Val-root.Val != 1 {
			r = 1
		}
		t := max(l, r)
		ans = max(ans, t)
		return t
	}
	dfs(root)
	return
}
```

### **TypeScript**

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

function longestConsecutive(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (root: TreeNode | null): number => {
        if (root === null) {
            return 0;
        }
        let l = dfs(root.left) + 1;
        let r = dfs(root.right) + 1;
        if (root.left && root.left.val - root.val !== 1) {
            l = 1;
        }
        if (root.right && root.right.val - root.val !== 1) {
            r = 1;
        }
        const t = Math.max(l, r);
        ans = Math.max(ans, t);
        return t;
    };
    dfs(root);
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
