# [1469. 寻找所有的独生节点](https://leetcode.cn/problems/find-all-the-lonely-nodes)

[English Version](/solution/1400-1499/1469.Find%20All%20The%20Lonely%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>二叉树中，如果一个节点是其父节点的唯一子节点，则称这样的节点为 &ldquo;<strong>独生节点</strong>&rdquo; 。二叉树的根节点不会是独生节点，因为它没有父节点。</p>

<p>给定一棵二叉树的根节点&nbsp;<code>root</code> ，返回树中<strong> 所有的独生节点的值所构成的数组</strong> 。数组的顺序<strong> 不限 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1469.Find%20All%20The%20Lonely%20Nodes/images/e1.png" style="height:202px; width:203px" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3,null,4]
<strong>输出：</strong>[4]
<strong>解释：</strong>浅蓝色的节点是唯一的独生节点。
节点 1 是根节点，不是独生的。
节点 2 和 3 有共同的父节点，所以它们都不是独生的。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1469.Find%20All%20The%20Lonely%20Nodes/images/e2.png" style="height:282px; width:442px" /></p>

<pre>
<strong>输入：</strong>root = [7,1,4,6,null,5,3,null,null,null,null,null,2]
<strong>输出：</strong>[6,2]
<strong>输出：</strong>浅蓝色的节点是独生节点。
请谨记，顺序是不限的。 [2,6] 也是一种可接受的答案。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1469.Find%20All%20The%20Lonely%20Nodes/images/tree.png" style="height:202px; width:363px" /> </strong></p>

<pre>
<strong>输入：</strong>root = [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
<strong>输出：</strong>[77,55,33,66,44,22]
<strong>解释：</strong>节点 99 和 88 有共同的父节点，节点 11 是根节点。
其他所有节点都是独生节点。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>root = [197]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>root = [31,null,78,null,28]
<strong>输出：</strong>[78,28]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>&nbsp;<code>tree</code>&nbsp;中节点个数的取值范围是&nbsp;<code>[1, 1000]</code>。</li>
	<li>每个节点的值的取值范围是&nbsp;<code>[1, 10^6]</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

递归搜索二叉树，如果当前节点的左右子节点都不为空，则继续递归搜索左右子树；如果当前节点的左右子节点有一个为空，则将不为空的子节点的值加入结果数组中，然后继续递归搜索左右子树。

时间复杂度 $O(n)$，其中 $n$ 为二叉树的节点个数。需要对二叉树进行一次遍历。

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
    def getLonelyNodes(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root):
            if root is None or (root.left is None and root.right is None):
                return
            if root.left is None:
                ans.append(root.right.val)
            if root.right is None:
                ans.append(root.left.val)
            dfs(root.left)
            dfs(root.right)

        ans = []
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
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> getLonelyNodes(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.left == null) {
            ans.add(root.right.val);
        }
        if (root.right == null) {
            ans.add(root.left.val);
        }
        dfs(root.left);
        dfs(root.right);
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
    vector<int> getLonelyNodes(TreeNode* root) {
        vector<int> ans;
        function<void(TreeNode* root)> dfs;
        dfs = [&](TreeNode* root) {
            if (!root || (!root->left && !root->right)) return;
            if (!root->left) ans.push_back(root->right->val);
            if (!root->right) ans.push_back(root->left->val);
            dfs(root->left);
            dfs(root->right);
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
func getLonelyNodes(root *TreeNode) []int {
	ans := []int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil || (root.Left == nil && root.Right == nil) {
			return
		}
		if root.Left == nil {
			ans = append(ans, root.Right.Val)
		}
		if root.Right == nil {
			ans = append(ans, root.Left.Val)
		}
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
