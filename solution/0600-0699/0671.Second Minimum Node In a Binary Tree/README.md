# [671. 二叉树中第二小的节点](https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree)

[English Version](/solution/0600-0699/0671.Second%20Minimum%20Node%20In%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为&nbsp;<code>2</code>&nbsp;或&nbsp;<code>0</code>。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。</p>

<p>更正式地说，即&nbsp;<code>root.val = min(root.left.val, root.right.val)</code> 总成立。</p>

<p>给出这样的一个二叉树，你需要输出所有节点中的&nbsp;<strong>第二小的值 </strong>。</p>

<p>如果第二小的值不存在的话，输出 -1 <strong>。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0671.Second%20Minimum%20Node%20In%20a%20Binary%20Tree/images/smbt1.jpg" style="height: 210px; width: 300px;" />
<pre>
<strong>输入：</strong>root = [2,2,5,null,null,5,7]
<strong>输出：</strong>5
<strong>解释：</strong>最小的值是 2 ，第二小的值是 5 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0671.Second%20Minimum%20Node%20In%20a%20Binary%20Tree/images/smbt2.jpg" style="height: 113px; width: 200px;" />
<pre>
<strong>输入：</strong>root = [2,2,2]
<strong>输出：</strong>-1
<strong>解释：</strong>最小的值是 2, 但是不存在第二小的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 25]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
	<li>对于树中每个节点 <code>root.val == min(root.left.val, root.right.val)</code></li>
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
    def findSecondMinimumValue(self, root: TreeNode) -> int:
        def dfs(root, val):
            if root:
                dfs(root.left, val)
                dfs(root.right, val)
                nonlocal ans
                if root.val > val:
                    ans = root.val if ans == -1 else min(ans, root.val)

        ans = -1
        dfs(root, root.val)
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

    public int findSecondMinimumValue(TreeNode root) {
        ans = -1;
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int val) {
        if (root != null) {
            dfs(root.left, val);
            dfs(root.right, val);
            if (root.val > val) {
                ans = ans == -1 ? root.val : Math.min(ans, root.val);
            }
        }
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

    int findSecondMinimumValue(TreeNode* root) {
        ans = -1;
        dfs(root, root->val);
        return ans;
    }

    void dfs(TreeNode* root, int val) {
        if (!root) return;
        dfs(root->left, val);
        dfs(root->right, val);
        if (root->val > val) ans = ans == -1 ? root->val : min(ans, root->val);
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
func findSecondMinimumValue(root *TreeNode) int {
	ans := -1

	var dfs func(root *TreeNode, val int)
	dfs = func(root *TreeNode, val int) {
		if root == nil {
			return
		}
		dfs(root.Left, val)
		dfs(root.Right, val)
		if root.Val > val {
			if ans == -1 {
				ans = root.Val
			} else {
				ans = min(ans, root.Val)
			}
		}
	}

	dfs(root, root.Val)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
