# [979. 在二叉树中分配硬币](https://leetcode.cn/problems/distribute-coins-in-binary-tree)

[English Version](/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个有 <code>N</code> 个结点的二叉树的根结点 <code>root</code>，树中的每个结点上都对应有 <code>node.val</code> 枚硬币，并且总共有 <code>N</code> 枚硬币。</p>

<p>在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。</p>

<p>返回使每个结点上只有一枚硬币所需的移动次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/images/tree1.png" style="height: 142px; width: 150px;"></strong></p>

<pre><strong>输入：</strong>[3,0,0]
<strong>输出：</strong>2
<strong>解释：</strong>从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/images/tree2.png" style="height: 142px; width: 150px;"></strong></p>

<pre><strong>输入：</strong>[0,3,0]
<strong>输出：</strong>3
<strong>解释：</strong>从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/images/tree3.png" style="height: 142px; width: 150px;"></strong></p>

<pre><strong>输入：</strong>[1,0,2]
<strong>输出：</strong>2
</pre>

<p><strong>示例 4：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0979.Distribute%20Coins%20in%20Binary%20Tree/images/tree4.png" style="height: 156px; width: 155px;"></strong></p>

<pre><strong>输入：</strong>[1,0,0,null,3]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1&lt;= N &lt;= 100</code></li>
	<li><code>0 &lt;= node.val &lt;= N</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先遍历左右子树，获得硬币的余额。比如，我们从左子树得到 `left = "+3"`，说明左子树有 3 个额外的硬币需要移出。如果我们从右子树得到 `right = "-1"`，说明右子树需要额外移入 1 个硬币。累加移动的次数 `abs(+3) + abs(-1)`，然后返回整个二叉树的余额 `left + right + root.val - 1`。

返回最终的移动次数即可。

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
    def distributeCoins(self, root: TreeNode) -> int:
        def dfs(root):
            nonlocal ans
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            ans += abs(left) + abs(right)
            return left + right + root.val - 1

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

    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
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

    int distributeCoins(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        int left = dfs(root->left), right = dfs(root->right);
        ans += abs(left) + abs(right);
        return left + right + root->val - 1;
    }
};
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func distributeCoins(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		left, right := dfs(root.Left), dfs(root.Right)
		ans += abs(left) + abs(right)
		return left + right + root.Val - 1
	}
	dfs(root)
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
