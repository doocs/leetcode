# [993. 二叉树的堂兄弟节点](https://leetcode.cn/problems/cousins-in-binary-tree)

[English Version](/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在二叉树中，根节点位于深度 <code>0</code> 处，每个深度为 <code>k</code> 的节点的子节点位于深度 <code>k+1</code> 处。</p>

<p>如果二叉树的两个节点深度相同，但<strong> 父节点不同</strong> ，则它们是一对<em>堂兄弟节点</em>。</p>

<p>我们给出了具有唯一值的二叉树的根节点 <code>root</code> ，以及树中两个不同节点的值 <code>x</code> 和 <code>y</code> 。</p>

<p>只有与值 <code>x</code> 和 <code>y</code> 对应的节点是堂兄弟节点时，才返回 <code>true</code> 。否则，返回 <code>false</code>。</p>

<p> </p>

<p><strong>示例 1：<br />
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-01.png" style="height: 160px; width: 180px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4], x = 4, y = 3
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：<br />
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-02.png" style="height: 160px; width: 201px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,null,4,null,5], x = 5, y = 4
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-03.png" style="height: 160px; width: 156px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,null,4], x = 2, y = 3
<strong>输出：</strong>false</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的节点数介于 <code>2</code> 到 <code>100</code> 之间。</li>
	<li>每个节点的值都是唯一的、范围为 <code>1</code> 到 <code>100</code> 的整数。</li>
</ul>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**1. BFS 实现**

可以利用数组 p, d 记录每个节点对应的父节点以及深度。

**2. DFS 实现**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

BFS：

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

DFS：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

BFS：

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

DFS：

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

BFS：

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

DFS：

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

BFS：

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

DFS：

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
