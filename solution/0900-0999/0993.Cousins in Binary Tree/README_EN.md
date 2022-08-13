# [993. Cousins in Binary Tree](https://leetcode.com/problems/cousins-in-binary-tree)

[中文文档](/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree with unique values and the values of two different nodes of the tree <code>x</code> and <code>y</code>, return <code>true</code> <em>if the nodes corresponding to the values </em><code>x</code><em> and </em><code>y</code><em> in the tree are <strong>cousins</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>Two nodes of a binary tree are <strong>cousins</strong> if they have the same depth with different parents.</p>

<p>Note that in a binary tree, the root node is at the depth <code>0</code>, and children of each depth <code>k</code> node are at the depth <code>k + 1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-01.png" style="width: 304px; height: 270px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4], x = 4, y = 3
<strong>Output:</strong> false
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-02.png" style="width: 334px; height: 266px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4,null,5], x = 5, y = 4
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-03.png" style="width: 267px; height: 258px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4], x = 2, y = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 100]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li>Each node has a <strong>unique</strong> value.</li>
	<li><code>x != y</code></li>
	<li><code>x</code> and <code>y</code> are exist in the tree.</li>
</ul>

## Solutions

BFS or DFS.

<!-- tabs:start -->

### **Python3**

BFS:

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
        q = deque([root])
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

DFS:

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: TreeNode, x: int, y: int) -> bool:
        p1 = p2 = d1 = d2 = None

        def dfs(root, p, d):
            if root is None:
                return
            nonlocal p1, p2, d1, d2, x, y
            if root.val == x:
                p1, d1 = p, d
            if root.val == y:
                p2, d2 = p, d
            dfs(root.left, root, d + 1)
            dfs(root.right, root, d + 1)

        dfs(root, None, 0)
        return p1 != p2 and d1 == d2
```

### **Java**

BFS:

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

DFS:

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
    private int x, y;
    private TreeNode p1, p2;
    private int d1, d2;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, null, 0);
        return p1 != p2 && d1 == d2;
    }

    private void dfs(TreeNode root, TreeNode p, int d) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            p1 = p;
            d1 = d;
        }
        if (root.val == y) {
            p2 = p;
            d2 = d;
        }
        dfs(root.left, root, d + 1);
        dfs(root.right, root, d + 1);
    }
}
```

### **C++**

BFS:

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
        while (!q.empty()) {
            int n = q.size();
            while (n--) {
                auto node = q.front();
                d[node->val] = i;
                q.pop();
                if (node->left) {
                    q.push(node->left);
                    p[node->left->val] = node->val;
                }
                if (node->right) {
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

DFS:

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
    TreeNode* p1;
    TreeNode* p2;
    int d1, d2;
    int x, y;

    bool isCousins(TreeNode* root, int x, int y) {
        this->x = x;
        this->y = y;
        dfs(root, nullptr, 0);
        return p1 != p2 && d1 == d2;
    }

    void dfs(TreeNode* root, TreeNode* p, int d) {
        if (!root) return;
        if (root->val == x)
        {
            p1 = p;
            d1 = d;
        }
        if (root->val == y)
        {
            p2 = p;
            d2 = d;
        }
        dfs(root->left, root, d + 1);
        dfs(root->right, root, d + 1);
    }
};
```

### **Go**

BFS:

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

DFS:

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var p1 *TreeNode
var p2 *TreeNode
var d1 int
var d2 int

func isCousins(root *TreeNode, x int, y int) bool {
	dfs(root, nil, x, y, 0)
	return p1 != p2 && d1 == d2
}

func dfs(root, p *TreeNode, x, y, d int) {
	if root == nil {
		return
	}
	if root.Val == x {
		p1 = p
		d1 = d
	}
	if root.Val == y {
		p2 = p
		d2 = d
	}
	dfs(root.Left, root, x, y, d+1)
	dfs(root.Right, root, x, y, d+1)
}
```

### **...**

```

```

<!-- tabs:end -->
