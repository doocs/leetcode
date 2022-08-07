# [剑指 Offer II 051. 节点之和最大的路径](https://leetcode.cn/problems/jC7MId)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>路径</strong> 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 <strong>至多出现一次</strong> 。该路径<strong> 至少包含一个 </strong>节点，且不一定经过根节点。</p>

<p><strong>路径和</strong> 是路径中各节点值的总和。</p>

<p>给定一个二叉树的根节点 <code>root</code> ，返回其 <strong>最大路径和</strong>，即所有路径上节点值之和的最大值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20051.%20%E8%8A%82%E7%82%B9%E4%B9%8B%E5%92%8C%E6%9C%80%E5%A4%A7%E7%9A%84%E8%B7%AF%E5%BE%84/images/exx1.jpg" style="width: 322px; height: 182px;" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3]
<strong>输出：</strong>6
<strong>解释：</strong>最优路径是 2 -&gt; 1 -&gt; 3 ，路径和为 2 + 1 + 3 = 6</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20051.%20%E8%8A%82%E7%82%B9%E4%B9%8B%E5%92%8C%E6%9C%80%E5%A4%A7%E7%9A%84%E8%B7%AF%E5%BE%84/images/exx2.jpg" /></p>

<pre>
<strong>输入：</strong>root = [-10,9,20,null,null,15,7]
<strong>输出：</strong>42
<strong>解释：</strong>最优路径是 15 -&gt; 20 -&gt; 7 ，路径和为 15 + 20 + 7 = 42
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目范围是 <code>[1, 3 * 10<sup>4</sup>]</code></li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 124&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/">https://leetcode.cn/problems/binary-tree-maximum-path-sum/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

思考二叉树递归问题的经典套路：

1. 终止条件（何时终止递归）
2. 递归处理左右子树
3. 合并左右子树的计算结果

对于本题，由于要满足题目对“路径”的定义，在返回当前子树对外贡献的最大路径和时，需要取 `left`，`right` 的最大值

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
    def maxPathSum(self, root: TreeNode) -> int:
        ans = -inf

        def dfs(node: TreeNode) -> int:
            if not node:
                return 0
            left = max(0, dfs(node.left))
            right = max(0, dfs(node.right))
            nonlocal ans
            ans = max(ans, node.val + left + right)
            return node.val + max(left, right)

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
    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        ans = Math.max(ans, node.val + left + right);
        return node.val + Math.max(left, right);
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
func maxPathSum(root *TreeNode) int {
	ans := math.MinInt32

	var dfs func(*TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		left := max(0, dfs(node.Left))
		right := max(0, dfs(node.Right))
		ans = max(ans, node.Val+left+right)
		return node.Val + max(left, right)
	}

	dfs(root)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
    int maxPathSum(TreeNode* root) {
        int ans = INT_MIN;

        function<int(TreeNode*)> dfs = [&](TreeNode* node) {
            if (node == nullptr) {
                return 0;
            }
            int left = max(0, dfs(node->left));
            int right = max(0, dfs(node->right));
            ans = max(ans, node->val + left + right);
            return node->val + max(left, right);
        };

        dfs(root);
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
