# [993. Cousins in Binary Tree](https://leetcode.com/problems/cousins-in-binary-tree)

[中文文档](/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/README.md)

## Description

<p>In a binary tree, the root node is at depth <code>0</code>, and children of each depth <code>k</code> node are at depth <code>k+1</code>.</p>

<p>Two nodes of a binary tree are <em>cousins</em> if they have the same depth, but have <strong>different parents</strong>.</p>

<p>We are given the <code>root</code> of a binary tree with unique values, and the values <code>x</code>&nbsp;and <code>y</code>&nbsp;of two different nodes in the tree.</p>

<p>Return&nbsp;<code>true</code>&nbsp;if and only if the nodes corresponding to the values <code>x</code> and <code>y</code> are cousins.</p>

<p>&nbsp;</p>

<p><strong>Example 1:<br />
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-01.png" style="width: 180px; height: 160px;" /></strong></p>

<pre>
<strong>Input: </strong>root = <span id="example-input-1-1">[1,2,3,4]</span>, x = <span id="example-input-1-2">4</span>, y = <span id="example-input-1-3">3</span>
<strong>Output: </strong><span id="example-output-1">false</span>
</pre>

<div>
<p><strong>Example 2:<br />
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-02.png" style="width: 201px; height: 160px;" /></strong></p>

<pre>
<strong>Input: </strong>root = <span id="example-input-2-1">[1,2,3,null,4,null,5]</span>, x = <span id="example-input-2-2">5</span>, y = <span id="example-input-2-3">4</span>
<strong>Output: </strong><span id="example-output-2">true</span>
</pre>

<div>
<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-03.png" style="width: 156px; height: 160px;" /></strong></p>

<pre>
<strong>Input: </strong>root = <span id="example-input-3-1">[1,2,3,null,4]</span>, x = 2, y = 3
<strong>Output: </strong><span id="example-output-3">false</span>
</pre>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree will be between <code>2</code> and <code>100</code>.</li>
	<li>Each node has a unique integer value from <code>1</code> to <code>100</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: TreeNode, x: int, y: int) -> bool:
        p = list(range(110))
        d = list(range(110))
        q = collections.deque([root])
        i = 0
        while q:
            n = len(q)
            for _ in range(n):
                node = q.popleft()
                d[node.val] = i
                if node.left:
                    p[node.left.val] = node.val
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
                    p[node.right.val] = node.val
            i += 1
        return p[x] != p[y] and d[x] == d[y]
```

### **Java**

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
    public boolean isCousins(TreeNode root, int x, int y) {
        int[] p = new int[110];
        int[] d = new int[110];
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            while (n-- > 0) {
                TreeNode node = q.poll();
                d[node.val] = i;
                if (node.left != null) {
                    q.offer(node.left);
                    p[node.left.val] = node.val;
                }
                if (node.right != null) {
                    q.offer(node.right);
                    p[node.right.val] = node.val;
                }
            }
            ++i;
        }
        return p[x] != p[y] && d[x] == d[y];
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node->
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
    bool isCousins(TreeNode* root, int x, int y) {
        vector<int> p(110);
        vector<int> d(110);
        queue<TreeNode*> q;
        q.push(root);
        int i = 0;
        while (!q.empty())
        {
            int n = q.size();
            while (n--)
            {
                auto node = q.front();
                d[node->val] = i;
                q.pop();
                if (node->left)
                {
                    q.push(node->left);
                    p[node->left->val] = node->val;
                }
                if (node->right)
                {
                    q.push(node->right);
                    p[node->right->val] = node->val;
                }
            }
            ++i;
        }
        return p[x] != p[y] && d[x] == d[y];
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
func isCousins(root *TreeNode, x int, y int) bool {
	p := make([]int, 110)
	d := make([]int, 110)
	var q []*TreeNode
	q = append(q, root)
	i := 0
	for len(q) > 0 {
		n := len(q)
		for n > 0 {
			node := q[0]
			q = q[1:]
			n--
			d[node.Val] = i
			if node.Left != nil {
				q = append(q, node.Left)
				p[node.Left.Val] = node.Val
			}
			if node.Right != nil {
				q = append(q, node.Right)
				p[node.Right.Val] = node.Val
			}
		}
		i++
	}
	return p[x] != p[y] && d[x] == d[y]
}
```

### **...**

```

```

<!-- tabs:end -->
