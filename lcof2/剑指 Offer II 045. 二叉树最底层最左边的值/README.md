# [剑指 Offer II 045. 二叉树最底层最左边的值](https://leetcode.cn/problems/LwUNpT)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，请找出该二叉树的&nbsp;<strong>最底层&nbsp;最左边&nbsp;</strong>节点的值。</p>

<p>假设二叉树中至少有一个节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20045.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E6%9C%80%E5%BA%95%E5%B1%82%E6%9C%80%E5%B7%A6%E8%BE%B9%E7%9A%84%E5%80%BC/images/tree1.jpg" style="width: 182px; " /></p>

<pre>
<strong>输入: </strong>root = [2,1,3]
<strong>输出: </strong>1
</pre>

<p><strong>示例 2: </strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20045.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E6%9C%80%E5%BA%95%E5%B1%82%E6%9C%80%E5%B7%A6%E8%BE%B9%E7%9A%84%E5%80%BC/images/tree2.jpg" style="width: 242px; " /><strong> </strong></p>

<pre>
<strong>输入: </strong>[1,2,3,4,null,5,6,null,null,7]
<strong>输出: </strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[1,10<sup>4</sup>]</code></li>
	<li><meta charset="UTF-8" /><code>-2<sup>31</sup>&nbsp;&lt;= Node.val &lt;= 2<sup>31</sup>&nbsp;- 1</code>&nbsp;</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 513&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/find-bottom-left-tree-value/">https://leetcode.cn/problems/find-bottom-left-tree-value/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“BFS 层次遍历”实现。

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
    def findBottomLeftValue(self, root: TreeNode) -> int:
        q = deque([root])
        ans = -1
        while q:
            n = len(q)
            for i in range(n):
                node = q.popleft()
                if i == 0:
                    ans = node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = -1;
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                if (i == 0) {
                    ans = node.val;
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return ans;
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
    int findBottomLeftValue(TreeNode* root) {
        queue<TreeNode*> q;
        q.push(root);
        int ans = -1;
        while (!q.empty()) {
            for (int i = 0, n = q.size(); i < n; ++i) {
                TreeNode* node = q.front();
                if (i == 0) ans = node->val;
                q.pop();
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
        }
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
func findBottomLeftValue(root *TreeNode) int {
	q := []*TreeNode{root}
	ans := -1
	for n := len(q); n > 0; n = len(q) {
		for i := 0; i < n; i++ {
			node := q[0]
			q = q[1:]
			if i == 0 {
				ans = node.Val
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
