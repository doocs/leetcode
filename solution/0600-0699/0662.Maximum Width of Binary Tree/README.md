# [662. 二叉树最大宽度](https://leetcode-cn.com/problems/maximum-width-of-binary-tree)

[English Version](/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与<strong>满二叉树（full binary tree）</strong>结构相同，但一些节点为空。</p>

<p>每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的<code>null</code>节点也计入长度）之间的长度。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

<strong>输出:</strong> 4
<strong>解释:</strong> 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 

          1
         /  
        3    
       / \       
      5   3     

<strong>输出:</strong> 2
<strong>解释:</strong> 最大值出现在树的第 3 层，宽度为 2 (5,3)。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong> 

          1
         / \
        3   2 
       /        
      5      

<strong>输出:</strong> 2
<strong>解释:</strong> 最大值出现在树的第 2 层，宽度为 2 (3,2)。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
<strong>输出:</strong> 8
<strong>解释:</strong> 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
</pre>

<p><strong>注意:</strong> 答案在32位有符号整数的表示范围内。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 层序遍历。

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
    def widthOfBinaryTree(self, root: TreeNode) -> int:
        q = deque([(root, 1)])
        ans = 0
        while q:
            n = len(q)
            ans = max(ans, q[-1][1] - q[0][1] + 1)
            for _ in range(n):
                node, j = q.popleft()
                if node.left:
                    q.append((node.left, 2 * j))
                if node.right:
                    q.append((node.right, 2 * j + 1))
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
    public int widthOfBinaryTree(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> q = new ArrayDeque<>();
        q.offerLast(new Pair<>(root, 1));
        int ans = 0;
        while (!q.isEmpty()) {
            ans = Math.max(ans, q.peekLast().getValue() - q.peekFirst().getValue() + 1);
            for (int i = 0, n = q.size(); i < n; ++i) {
                Pair<TreeNode, Integer> node = q.pollFirst();
                if (node.getKey().left != null) {
                    q.offerLast(new Pair<>(node.getKey().left, node.getValue() * 2));
                }
                if (node.getKey().right != null) {
                    q.offerLast(new Pair<>(node.getKey().right, node.getValue() * 2 + 1));
                }
            }
        }
        return ans;
    }
}
```

### **C++**

`start * 2` 表示下一层的起点。计算下一层左右子树索引时，减去 `start * 2`，可以防止溢出。

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
    int widthOfBinaryTree(TreeNode* root) {
        queue<pair<TreeNode*, int>> q;
        q.emplace(root, 1);
        int ans = 0;
        while (!q.empty())
        {
            ans = max(ans, q.back().second - q.front().second + 1);
            int start = q.front().second;
            for (int i = 0, n = q.size(); i < n; ++i)
            {
                auto node = q.front();
                q.pop();
                if (node.first->left != nullptr) q.emplace(node.first->left, node.second * 2 - start * 2);
                if (node.first->right != nullptr) q.emplace(node.first->right, node.second * 2 + 1 - start * 2);
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
type Node struct {
	node *TreeNode
	idx  int
}

func widthOfBinaryTree(root *TreeNode) int {
	q := []Node{{root, 1}}
	ans := 0
	for len(q) > 0 {
		ans = max(ans, q[len(q)-1].idx-q[0].idx+1)
		n := len(q)
		for i := 0; i < n; i++ {
			node := q[0]
			q = q[1:]
			if node.node.Left != nil {
				q = append(q, Node{node.node.Left, node.idx * 2})
			}
			if node.node.Right != nil {
				q = append(q, Node{node.node.Right, node.idx*2 + 1})
			}
		}
	}
	return ans
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
