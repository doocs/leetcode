---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0272.Closest%20Binary%20Search%20Tree%20Value%20II/README.md
tags:
    - 栈
    - 树
    - 深度优先搜索
    - 二叉搜索树
    - 双指针
    - 二叉树
    - 堆（优先队列）
---

# [272. 最接近的二叉搜索树值 II 🔒](https://leetcode.cn/problems/closest-binary-search-tree-value-ii)

[English Version](/solution/0200-0299/0272.Closest%20Binary%20Search%20Tree%20Value%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定二叉搜索树的根&nbsp;<code>root</code>&nbsp;、一个目标值&nbsp;<code>target</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，返回BST中最接近目标的 <code>k</code> 个值。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>题目&nbsp;<strong>保证</strong>&nbsp;该二叉搜索树中只会存在一种&nbsp;k 个值集合最接近&nbsp;<code>target</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0272.Closest%20Binary%20Search%20Tree%20Value%20II/images/closest1-1-tree.jpg" /></p>

<pre>
<strong>输入:</strong> root = [4,2,5,1,3]，目标值 = 3.714286，且 <em>k</em> = 2
<strong>输出:</strong> [4,3]</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [1], target = 0.000000, k = 1
<strong>输出:</strong> [1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的节点总数为&nbsp;<code>n</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>假设该二叉搜索树是平衡的，请问您是否能在小于&nbsp;<code>O(n)</code>（&nbsp;<code>n = total nodes</code>&nbsp;）的时间复杂度内解决该问题呢？</p>

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
    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            if len(q) < k:
                q.append(root.val)
            else:
                if abs(root.val - target) >= abs(q[0] - target):
                    return
                q.popleft()
                q.append(root.val)
            dfs(root.right)

        q = deque()
        dfs(root)
        return list(q)
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

    private List<Integer> ans;
    private double target;
    private int k;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        ans = new LinkedList<>();
        this.target = target;
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (ans.size() < k) {
            ans.add(root.val);
        } else {
            if (Math.abs(root.val - target) >= Math.abs(ans.get(0) - target)) {
                return;
            }
            ans.remove(0);
            ans.add(root.val);
        }
        dfs(root.right);
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
    queue<int> q;
    double target;
    int k;

    vector<int> closestKValues(TreeNode* root, double target, int k) {
        this->target = target;
        this->k = k;
        dfs(root);
        vector<int> ans;
        while (!q.empty()) {
            ans.push_back(q.front());
            q.pop();
        }
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        if (q.size() < k)
            q.push(root->val);
        else {
            if (abs(root->val - target) >= abs(q.front() - target)) return;
            q.pop();
            q.push(root->val);
        }
        dfs(root->right);
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
func closestKValues(root *TreeNode, target float64, k int) []int {
	var ans []int
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if len(ans) < k {
			ans = append(ans, root.Val)
		} else {
			if math.Abs(float64(root.Val)-target) >= math.Abs(float64(ans[0])-target) {
				return
			}
			ans = ans[1:]
			ans = append(ans, root.Val)
		}
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
