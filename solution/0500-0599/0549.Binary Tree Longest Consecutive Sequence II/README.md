# [549. 二叉树最长连续序列 II 🔒](https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence-ii)

[English Version](/solution/0500-0599/0549.Binary%20Tree%20Longest%20Consecutive%20Sequence%20II/README_EN.md)

<!-- tags:树,深度优先搜索,二叉树 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定二叉树的根&nbsp;<code>root</code>&nbsp;，返回树中<strong>最长连续路径</strong>的长度。<br />
<strong>连续路径</strong>是路径中相邻节点的值相差 <code>1</code> 的路径。此路径可以是增加或减少。</p>

<ul>
	<li>例如，&nbsp;<code>[1,2,3,4]</code> 和 <code>[4,3,2,1]</code> 都被认为有效，但路径 <code>[1,2,4,3]</code> 无效。</li>
</ul>

<p>另一方面，路径可以是子-父-子顺序，不一定是父子顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0549.Binary%20Tree%20Longest%20Consecutive%20Sequence%20II/images/consec2-1-tree.jpg" /></p>

<pre>
<strong>输入: </strong>root = [1,2,3]
<strong>输出:</strong> 2
<strong>解释:</strong> 最长的连续路径是 [1, 2] 或者 [2, 1]。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0549.Binary%20Tree%20Longest%20Consecutive%20Sequence%20II/images/consec2-2-tree.jpg" /></p>

<pre>
<strong>输入: </strong>root = [2,1,3]
<strong>输出:</strong> 3
<strong>解释:</strong> 最长的连续路径是 [1, 2, 3] 或者 [3, 2, 1]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树上所有节点的值都在&nbsp;<code>[1, 3 * 10<sup>4</sup>]</code>&nbsp;范围内。</li>
	<li><code>-3 * 10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestConsecutive(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return [0, 0]
            nonlocal ans
            incr = decr = 1
            i1, d1 = dfs(root.left)
            i2, d2 = dfs(root.right)
            if root.left:
                if root.left.val + 1 == root.val:
                    incr = i1 + 1
                if root.left.val - 1 == root.val:
                    decr = d1 + 1
            if root.right:
                if root.right.val + 1 == root.val:
                    incr = max(incr, i2 + 1)
                if root.right.val - 1 == root.val:
                    decr = max(decr, d2 + 1)
            ans = max(ans, incr + decr - 1)
            return [incr, decr]

        ans = 0
        dfs(root)
        return ans
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
    private int ans;

    public int longestConsecutive(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int incr = 1, decr = 1;
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        if (root.left != null) {
            if (root.left.val + 1 == root.val) {
                incr = left[0] + 1;
            }
            if (root.left.val - 1 == root.val) {
                decr = left[1] + 1;
            }
        }
        if (root.right != null) {
            if (root.right.val + 1 == root.val) {
                incr = Math.max(incr, right[0] + 1);
            }
            if (root.right.val - 1 == root.val) {
                decr = Math.max(decr, right[1] + 1);
            }
        }
        ans = Math.max(ans, incr + decr - 1);
        return new int[] {incr, decr};
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
    int ans;

    int longestConsecutive(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    vector<int> dfs(TreeNode* root) {
        if (!root) return {0, 0};
        int incr = 1, decr = 1;
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        if (root->left) {
            if (root->left->val + 1 == root->val) incr = left[0] + 1;
            if (root->left->val - 1 == root->val) decr = left[1] + 1;
        }
        if (root->right) {
            if (root->right->val + 1 == root->val) incr = max(incr, right[0] + 1);
            if (root->right->val - 1 == root->val) decr = max(decr, right[1] + 1);
        }
        ans = max(ans, incr + decr - 1);
        return {incr, decr};
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
func longestConsecutive(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{0, 0}
		}
		incr, decr := 1, 1
		left := dfs(root.Left)
		right := dfs(root.Right)
		if root.Left != nil {
			if root.Left.Val+1 == root.Val {
				incr = left[0] + 1
			}
			if root.Left.Val-1 == root.Val {
				decr = left[1] + 1
			}
		}
		if root.Right != nil {
			if root.Right.Val+1 == root.Val {
				incr = max(incr, right[0]+1)
			}
			if root.Right.Val-1 == root.Val {
				decr = max(decr, right[1]+1)
			}
		}
		ans = max(ans, incr+decr-1)
		return []int{incr, decr}
	}
	dfs(root)
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
