# [337. 打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii)

[English Version](/solution/0300-0399/0337.House%20Robber%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为&ldquo;根&rdquo;。 除了&ldquo;根&rdquo;之外，每栋房子有且只有一个&ldquo;父&ldquo;房子与之相连。一番侦察之后，聪明的小偷意识到&ldquo;这个地方的所有房屋的排列类似于一棵二叉树&rdquo;。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。</p>

<p>计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>[3,2,3,null,3,null,1]

     <strong>3</strong>
    / \
   2   3
    \   \ 
     <strong>3</strong>   <strong>1</strong>

<strong>输出:</strong> 7 
<strong>解释:</strong>&nbsp;小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = <strong>7</strong>.</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>[3,4,5,1,3,null,1]

&nbsp;    3
    / \
   <strong>4</strong>   <strong>5</strong>
  / \   \ 
 1   3   1

<strong>输出:</strong> 9
<strong>解释:</strong>&nbsp;小偷一晚能够盗取的最高金额&nbsp;= <strong>4</strong> + <strong>5</strong> = <strong>9</strong>.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

记忆化搜索。

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
    def rob(self, root: TreeNode) -> int:
        @lru_cache(None)
        def dfs(root):
            if root is None:
                return 0
            if root.left is None and root.right is None:
                return root.val
            a = dfs(root.left) + dfs(root.right)
            b = root.val
            if root.left:
                b += dfs(root.left.left) + dfs(root.left.right)
            if root.right:
                b += dfs(root.right.left) + dfs(root.right.right)
            return max(a, b)

        return dfs(root)
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
    private Map<TreeNode, Integer> memo;

    public int rob(TreeNode root) {
        memo = new HashMap<>();
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int a = dfs(root.left) + dfs(root.right);
        int b = root.val;
        if (root.left != null) {
            b += dfs(root.left.left) + dfs(root.left.right);
        }
        if (root.right != null) {
            b += dfs(root.right.left) + dfs(root.right.right);
        }
        int res = Math.max(a, b);
        memo.put(root, res);
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
    unordered_map<TreeNode*, int> memo;

    int rob(TreeNode* root) {
        return dfs(root);
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        if (memo.count(root)) return memo[root];
        int a = dfs(root->left) + dfs(root->right);
        int b = root-> val;
        if (root->left) b += dfs(root->left->left) + dfs(root->left->right);
        if (root->right) b += dfs(root->right->left) + dfs(root->right->right);
        int res = max(a, b);
        memo[root] = res;
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
func rob(root *TreeNode) int {
	memo := make(map[*TreeNode]int)
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if _, ok := memo[root]; ok {
			return memo[root]
		}
		a := dfs(root.Left) + dfs(root.Right)
		b := root.Val
		if root.Left != nil {
			b += dfs(root.Left.Left) + dfs(root.Left.Right)
		}
		if root.Right != nil {
			b += dfs(root.Right.Left) + dfs(root.Right.Right)
		}
		res := max(a, b)
		memo[root] = res
		return res
	}
	return dfs(root)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
