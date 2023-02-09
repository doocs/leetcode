# [993. Cousins in Binary Tree](https://leetcode.com/problems/cousins-in-binary-tree)

[中文文档](/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree with unique values and the values of two different nodes of the tree <code>x</code> and <code>y</code>, return <code>true</code> <em>if the nodes corresponding to the values </em><code>x</code><em> and </em><code>y</code><em> in the tree are <strong>cousins</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>Two nodes of a binary tree are <strong>cousins</strong> if they have the same depth with different parents.</p>

<p>Note that in a binary tree, the root node is at the depth <code>0</code>, and children of each depth <code>k</code> node are at the depth <code>k + 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-01.png" style="width: 304px; height: 270px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4], x = 4, y = 3
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-02.png" style="width: 334px; height: 266px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4,null,5], x = 5, y = 4
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>
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
    def isCousins(self, root: Optional[TreeNode], x: int, y: int) -> bool:
        q = deque([(root, None)])
        d = 0
        p1 = p2 = None
        d1 = d2 = 0
        while q:
            for _ in range(len(q)):
                node, fa = q.popleft()
                if node.val == x:
                    p1, d1 = fa, d
                if node.val == y:
                    p2, d2 = fa, d
                if node.left:
                    q.append((node.left, node))
                if node.right:
                    q.append((node.right, node))
            d += 1
        return p1 != p2 and d1 == d2
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
    def isCousins(self, root: Optional[TreeNode], x: int, y: int) -> bool:
        def dfs(root, fa, d):
            if root is None:
                return
            if root.val == x:
                t[0] = (fa, d)
            if root.val == y:
                t[1] = (fa, d)
            dfs(root.left, root, d + 1)
            dfs(root.right, root, d + 1)

        t = [None, None]
        dfs(root, None, 0)
        return t[0][0] != t[1][0] and t[0][1] == t[1][1]
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
        TreeNode p1 = null, p2 = null;
        int d1 = 0, d2 = 0;
        Deque<TreeNode[]> q = new ArrayDeque<>();
        q.offer(new TreeNode[]{root, null});
        int d = 0;
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                var p = q.poll();
                TreeNode node = p[0], fa = p[1];
                if (node.val == x) {
                    p1 = fa;
                    d1 = d;
                }
                if (node.val == y) {
                    p2 = fa;
                    d2 = d;
                }
                if (node.left != null) {
                    q.offer(new TreeNode[]{node.left, node});
                }
                if (node.right != null) {
                    q.offer(new TreeNode[]{node.right, node});
                }
            }
            ++d;
        }
        return p1 != p2 && d1 == d2;
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
    bool isCousins(TreeNode* root, int x, int y) {
        TreeNode* p1 = nullptr;
        TreeNode* p2 = nullptr;
        int d1 = 0, d2 = 0;
        queue<pair<TreeNode*, TreeNode*>> q;
        q.emplace(root, nullptr);
        int d = 0;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                auto [node, fa] = q.front();
                q.pop();
                if (node->val == x) {
                    p1 = fa;
                    d1 = d;
                }
                if (node->val == y) {
                    p2 = fa;
                    d2 = d;
                }
                if (node->left) {
                    q.emplace(node->left, node);
                }
                if (node->right) {
                    q.emplace(node->right, node);
                }
            }
            ++d;
        }
        return p1 != p2 && d1 == d2;
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
    bool isCousins(TreeNode* root, int x, int y) {
        TreeNode* p1, *p2;
        int d1, d2;
        function<void(TreeNode*, TreeNode*, int)> dfs = [&](TreeNode* root, TreeNode* fa, int d) {
            if (!root) {
                return;
            }
            if (root->val == x) {
                p1 = fa;
                d1 = d;
            }
            if (root->val == y) {
                p2 = fa;
                d2 = d;
            }
            dfs(root->left, root, d + 1);
            dfs(root->right, root, d + 1);
        };
        dfs(root, nullptr, 0);
        return p1 != p2 && d1 == d2;
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
	type pair struct{ node, fa *TreeNode }
	q := []pair{pair{root, nil}}
	var p1, p2 *TreeNode
	var d, d1, d2 int
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			node, fa := p.node, p.fa
			if node.Val == x {
				p1, d1 = fa, d
			}
			if node.Val == y {
				p2, d2 = fa, d
			}
			if node.Left != nil {
				q = append(q, pair{node.Left, node})
			}
			if node.Right != nil {
				q = append(q, pair{node.Right, node})
			}
		}
		d++
	}
	return p1 != p2 && d1 == d2
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
func isCousins(root *TreeNode, x int, y int) bool {
	var p1, p2 *TreeNode
	var d1, d2 int
	var dfs func(*TreeNode, *TreeNode, int)
	dfs = func(root *TreeNode, fa *TreeNode, d int) {
		if root == nil {
			return
		}
		if root.Val == x {
			p1, d1 = fa, d
		}
		if root.Val == y {
			p2, d2 = fa, d
		}
		dfs(root.Left, root, d+1)
		dfs(root.Right, root, d+1)
	}
	dfs(root, nil, 0)
	return p1 != p2 && d1 == d2
}
```

### **...**

```

```

<!-- tabs:end -->
