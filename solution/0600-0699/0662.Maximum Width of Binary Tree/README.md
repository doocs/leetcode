# [662. 二叉树最大宽度](https://leetcode.cn/problems/maximum-width-of-binary-tree)

[English Version](/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> ，返回树的 <strong>最大宽度</strong> 。</p>

<p>树的 <strong>最大宽度</strong> 是所有层中最大的 <strong>宽度</strong> 。</p>

<div class="original__bRMd">
<div>
<p>每一层的 <strong>宽度</strong> 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 <code>null</code> 节点，这些 <code>null</code> 节点也计入长度。</p>

<p>题目数据保证答案将会在&nbsp; <strong>32 位</strong> 带符号整数范围内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/images/width1-tree.jpg" style="width: 359px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [1,3,2,5,3,null,9]
<strong>输出：</strong>4
<strong>解释：</strong>最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/images/maximum-width-of-binary-tree-v3.jpg" style="width: 442px; height: 422px;" />
<pre>
<strong>输入：</strong>root = [1,3,2,5,null,null,9,6,null,7]
<strong>输出：</strong>7
<strong>解释：</strong>最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0662.Maximum%20Width%20of%20Binary%20Tree/images/width3-tree.jpg" style="width: 289px; height: 299px;" />
<pre>
<strong>输入：</strong>root = [1,3,2,5]
<strong>输出：</strong>2
<strong>解释：</strong>最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目范围是 <code>[1, 3000]</code></li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>
</div>
</div>

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
        while (!q.empty()) {
            ans = max(ans, q.back().second - q.front().second + 1);
            int start = q.front().second;
            for (int i = 0, n = q.size(); i < n; ++i) {
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
