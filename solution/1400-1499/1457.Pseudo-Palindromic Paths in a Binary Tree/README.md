# [1457. 二叉树中的伪回文路径](https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree)

[English Version](/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「<strong>伪回文</strong>」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。</p>

<p>请你返回从根到叶子节点的所有路径中&nbsp;<strong>伪回文&nbsp;</strong>路径的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/images/palindromic_paths_1.png" style="height: 201px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [2,3,1,3,1,null,1]
<strong>输出：</strong>2 
<strong>解释：</strong>上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
     在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1457.Pseudo-Palindromic%20Paths%20in%20a%20Binary%20Tree/images/palindromic_paths_2.png" style="height: 314px; width: 300px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [2,1,1,1,3,null,null,null,null,null,1]
<strong>输出：</strong>1 
<strong>解释：</strong>上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径 [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。
     这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [9]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定二叉树的节点数目在范围&nbsp;<code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先序遍历，统计每条路径上数字出现的次数，要满足伪回文路径，当且仅当路径上最多有一个数字的出现次数为奇数。

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
    def pseudoPalindromicPaths(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return
            nonlocal ans, counter
            counter[root.val] += 1
            if root.left is None and root.right is None:
                if sum(1 for i in range(1, 10) if counter[i] % 2 == 1) < 2:
                    ans += 1
            else:
                dfs(root.left)
                dfs(root.right)
            counter[root.val] -= 1

        ans = 0
        counter = [0] * 10
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
    private int[] counter;

    public int pseudoPalindromicPaths(TreeNode root) {
        ans = 0;
        counter = new int[10];
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        ++counter[root.val];
        if (root.left == null && root.right == null) {
            if (check(counter)) {
                ++ans;
            }
        } else {
            dfs(root.left);
            dfs(root.right);
        }
        --counter[root.val];
    }

    private boolean check(int[] counter) {
        int n = 0;
        for (int i = 1; i < 10; ++i) {
            if (counter[i] % 2 == 1) {
                ++n;
            }
        }
        return n < 2;
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
    vector<int> counter;

    int pseudoPalindromicPaths(TreeNode* root) {
        ans = 0;
        counter.resize(10);
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        ++counter[root->val];
        if (!root->left && !root->right) {
            int n = 0;
            for (int i = 1; i < 10; ++i)
                if (counter[i] % 2 == 1)
                    ++n;
            if (n < 2) ++ans;
        } else {
            dfs(root->left);
            dfs(root->right);
        }
        --counter[root->val];
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
func pseudoPalindromicPaths(root *TreeNode) int {
	ans := 0
	counter := make([]int, 10)
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		counter[root.Val]++
		if root.Left == nil && root.Right == nil {
			n := 0
			for i := 1; i < 10; i++ {
				if counter[i]%2 == 1 {
					n++
				}
			}
			if n < 2 {
				ans++
			}
		} else {
			dfs(root.Left)
			dfs(root.Right)
		}
		counter[root.Val]--
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
