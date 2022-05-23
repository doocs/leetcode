# [2265. 统计值等于子树平均值的节点数](https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree)

[English Version](/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> ，找出并返回满足要求的节点数，要求节点的值等于其 <strong>子树</strong> 中值的 <strong>平均值</strong> 。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>n</code> 个元素的平均值可以由 <code>n</code> 个元素 <strong>求和</strong> 然后再除以 <code>n</code> ，并 <strong>向下舍入</strong> 到最近的整数。</li>
	<li><code>root</code> 的 <strong>子树</strong> 由 <code>root</code> 和它的所有后代组成。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/images/image-20220315203925-1.png" style="width: 300px; height: 212px;">
<pre><strong>输入：</strong>root = [4,8,5,0,1,null,6]
<strong>输出：</strong>5
<strong>解释：</strong>
对值为 4 的节点：子树的平均值 (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4 。
对值为 5 的节点：子树的平均值 (5 + 6) / 2 = 11 / 2 = 5 。
对值为 0 的节点：子树的平均值 0 / 1 = 0 。
对值为 1 的节点：子树的平均值 1 / 1 = 1 。
对值为 6 的节点：子树的平均值 6 / 1 = 6 。
</pre>

<p><strong>示例 2：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2265.Count%20Nodes%20Equal%20to%20Average%20of%20Subtree/images/image-20220326133920-1.png" style="width: 80px; height: 76px;">
<pre><strong>输入：</strong>root = [1]
<strong>输出：</strong>1
<strong>解释：</strong>对值为 1 的节点：子树的平均值 1 / 1 = 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 1000]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
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
    def averageOfSubtree(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return 0, 0
            ls, ln = dfs(root.left)
            rs, rn = dfs(root.right)
            s = ls + rs + root.val
            n = ln + rn + 1
            if s // n == root.val:
                nonlocal ans
                ans += 1
            return s, n

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

    public int averageOfSubtree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int s = l[0] + r[0] + root.val;
        int n = l[1] + r[1] + 1;
        if (s / n == root.val) {
            ++ans;
        }
        return new int[]{s, n};
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
    int ans;
    int averageOfSubtree(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    vector<int> dfs(TreeNode* root) {
        if (!root) return {0, 0};
        auto l = dfs(root->left);
        auto r = dfs(root->right);
        int s = l[0] + r[0] + root->val;
        int n = l[1] + r[1] + 1;
        if (s / n == root->val) ++ans;
        return {s, n};
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
func averageOfSubtree(root *TreeNode) int {
	ans := 0
	var dfs func(*TreeNode) (int, int)
	dfs = func(root *TreeNode) (int, int) {
		if root == nil {
			return 0, 0
		}
		ls, ln := dfs(root.Left)
		rs, rn := dfs(root.Right)
		s := ls + rs + root.Val
		n := ln + rn + 1
		if s/n == root.Val {
			ans++
		}
		return s, n
	}
	dfs(root)
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
