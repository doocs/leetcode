# [513. 找树左下角的值](https://leetcode-cn.com/problems/find-bottom-left-tree-value)

[English Version](/solution/0500-0599/0513.Find%20Bottom%20Left%20Tree%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，在树的最后一行找到最左边的值。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>

    2
   / \
  1   3

<strong>输出:</strong>
1
</pre>

<p>&nbsp;</p>

<p><strong>示例 2: </strong></p>

<pre>
<strong>输入:</strong>

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

<strong>输出:</strong>
7
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong> 您可以假设树（即给定的根节点）不为 <strong>NULL</strong>。</p>

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

### **TypeScript**

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function findBottomLeftValue(root: TreeNode | null): number {
    let stack: Array<TreeNode> = [root];
    let ans = root.val;
    while (stack.length) {
        let next = [];
        for (let node of stack) {
            if (node.left) {
                next.push(node.left);
            }
            if (node.right) {
                next.push(node.right);
            }
        }
        if (next.length) {
            ans = next[0].val;
        }
        stack = next;
    }
    return ans;
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
        while (!q.empty())
        {
            for (int i = 0, n = q.size(); i < n; ++i)
            {
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
