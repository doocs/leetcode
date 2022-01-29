# [1161. 最大层内元素和](https://leetcode-cn.com/problems/maximum-level-sum-of-a-binary-tree)

[English Version](/solution/1100-1199/1161.Maximum%20Level%20Sum%20of%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉树的根节点&nbsp;<code>root</code>。设根节点位于二叉树的第 <code>1</code> 层，而根节点的子节点位于第 <code>2</code> 层，依此类推。</p>

<p>请你找出层内元素之和 <strong>最大</strong> 的那几层（可能只有一层）的层号，并返回其中&nbsp;<strong>最小</strong> 的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1161.Maximum%20Level%20Sum%20of%20a%20Binary%20Tree/images/capture.jpeg" style="height: 175px; width: 200px;"></strong></p>

<pre><strong>输入：</strong>root = [1,7,0,7,-8,null,null]
<strong>输出：</strong>2
<strong>解释：</strong>
第 1 层各元素之和为 1，
第 2 层各元素之和为 7 + 0 = 7，
第 3 层各元素之和为 7 + -8 = -1，
所以我们返回第 2 层的层号，它的层内元素之和最大。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root = [989,null,10250,98693,-89388,null,null,null,-32127]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数介于&nbsp;<code>1</code>&nbsp;和&nbsp;<code>10^4</code>&nbsp;之间</li>
	<li><code>-10^5 &lt;= node.val &lt;= 10^5</code></li>
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
    def maxLevelSum(self, root: TreeNode) -> int:
        ans = (float('-inf'), 0)
        q = deque([root])
        l = 0
        while q:
            l += 1
            n = len(q)
            s = 0
            for _ in range(n):
                node = q.popleft()
                s += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if s > ans[0]:
                ans = (s, l)
        return ans[1]
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

    public int maxLevelSum(TreeNode root) {
        int[] ans = new int[] { Integer.MIN_VALUE, 0 };
        int l = 0;
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            ++l;
            int s = 0;
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.pollFirst();
                s += node.val;
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            if (s > ans[0]) {
                ans[0] = s;
                ans[1] = l;
            }
        }
        return ans[1];
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
    int maxLevelSum(TreeNode* root) {
        vector<int> ans(2);
        ans[0] = INT_MIN;
        queue<TreeNode*> q{{root}};
        int l = 0;
        while (!q.empty())
        {
            ++l;
            int s = 0;
            for (int i = q.size(); i > 0; --i)
            {
                TreeNode* node = q.front();
                q.pop();
                s += node->val;
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            if (s > ans[0])
            {
                ans[0] = s;
                ans[1] = l;
            }
        }
        return ans[1];
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
func maxLevelSum(root *TreeNode) int {
	ans := [2]int{math.MinInt32, 0}
	q := []*TreeNode{root}
	l := 0
	for len(q) > 0 {
		l++
		s := 0
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			s += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if s > ans[0] {
			ans = [2]int{s, l}
		}
	}
	return ans[1]
}
```

### **...**

```

```

<!-- tabs:end -->
