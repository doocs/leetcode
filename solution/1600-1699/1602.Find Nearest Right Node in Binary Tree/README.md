---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1602.Find%20Nearest%20Right%20Node%20in%20Binary%20Tree/README.md
tags:
    - 树
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1602. 找到二叉树中最近的右侧节点 🔒](https://leetcode.cn/problems/find-nearest-right-node-in-binary-tree)

[English Version](/solution/1600-1699/1602.Find%20Nearest%20Right%20Node%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树的根节点 <code>root</code> 和树中的一个节点 <code>u</code> ，返回与 <code>u</code> <strong>所在层</strong>中<strong>距离最近</strong>的<strong>右侧</strong>节点，当 <code>u</code> 是所在层中最右侧的节点，返回 <code>null</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1602.Find%20Nearest%20Right%20Node%20in%20Binary%20Tree/images/p3.png" style="width: 241px; height: 161px;" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3,null,4,5,6], u = 4
<strong>输出：</strong>5
<strong>解释：</strong>节点 4 所在层中，最近的右侧节点是节点 5。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1602.Find%20Nearest%20Right%20Node%20in%20Binary%20Tree/images/p2.png" style="width: 101px; height: 161px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [3,null,4,2], u = 2
<strong>输出：</strong>null
<strong>解释：</strong>2 的右侧没有节点。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1], u = 1
<strong>输出：</strong>null
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>root = [3,4,2,null,null,null,1], u = 4
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是 <code>[1, 10<sup>5</sup>]</code> 。</li>
	<li><code>1 <= Node.val <= 10<sup>5</sup></code></li>
	<li>树中所有节点的值是<strong>唯一</strong>的。</li>
	<li><code>u</code> 是以 <code>root</code> 为根的二叉树的一个节点。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

<!-- thinking:start -->

> **思考**
>
> 所求是与 $u$ 同层且紧邻其右侧的结点。若先求出 $u$ 的深度再扫描整层，需要两次遍历或额外存储各层结点。
>
> 层序遍历本身按从左到右给出同一层的相邻关系：当队列中弹出 $u$ 时，若本层尚未结束，队首即为右邻。
>
> 为此用 BFS，按层弹出；发现 $u$ 后根据本层剩余结点数决定返回队首或 $\textit{null}$。

<!-- thinking:end -->

我们可以使用广度优先搜索，从根节点开始搜索，当搜索到节点 $u$ 时，返回队列中的下一个节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findNearestRightNode(self, root: TreeNode, u: TreeNode) -> Optional[TreeNode]:
        q = deque([root])
        while q:
            for i in range(len(q) - 1, -1, -1):
                root = q.popleft()
                if root == u:
                    return q[0] if i else None
                if root.left:
                    q.append(root.left)
                if root.right:
                    q.append(root.right)
```

#### Java

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
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                root = q.pollFirst();
                if (root == u) {
                    return i > 1 ? q.peekFirst() : null;
                }
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
        }
        return null;
    }
}
```

#### C++

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
    TreeNode* findNearestRightNode(TreeNode* root, TreeNode* u) {
        queue<TreeNode*> q{{root}};
        while (q.size()) {
            for (int i = q.size(); i; --i) {
                root = q.front();
                q.pop();
                if (root == u) {
                    return i > 1 ? q.front() : nullptr;
                }
                if (root->left) {
                    q.push(root->left);
                }
                if (root->right) {
                    q.push(root->right);
                }
            }
        }
        return nullptr;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findNearestRightNode(root *TreeNode, u *TreeNode) *TreeNode {
	q := []*TreeNode{root}
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			root = q[0]
			q = q[1:]
			if root == u {
				if i > 1 {
					return q[0]
				}
				return nil
			}
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
	}
	return nil
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} u
 * @return {TreeNode}
 */
var findNearestRightNode = function (root, u) {
    const q = [root];
    while (q.length) {
        for (let i = q.length; i; --i) {
            root = q.shift();
            if (root == u) {
                return i > 1 ? q[0] : null;
            }
            if (root.left) {
                q.push(root.left);
            }
            if (root.right) {
                q.push(root.right);
            }
        }
    }
    return null;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：DFS

<!-- thinking:start -->

> **思考**
>
> 方法一依赖队列保存整层，空间随宽度增长。先序 DFS 按「左子再右子」访问同层结点，顺序与从左到右一致。
>
> 因此首次遇到 $u$ 时记下深度 $d$，之后第一个同深度结点即为右邻，可用递归实现且不必显式分层。

<!-- thinking:end -->

DFS 先序遍历二叉树，首次搜索到 $u$ 时，标记目前层数 $d$，下次遇到同一层的节点时，即为目标节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findNearestRightNode(self, root: TreeNode, u: TreeNode) -> Optional[TreeNode]:
        def dfs(root, i):
            nonlocal d, ans
            if root is None or ans:
                return
            if d == i:
                ans = root
                return
            if root == u:
                d = i
                return
            dfs(root.left, i + 1)
            dfs(root.right, i + 1)

        d = 0
        ans = None
        dfs(root, 1)
        return ans
```

#### Java

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
    private TreeNode u;
    private TreeNode ans;
    private int d;

    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        this.u = u;
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null || ans != null) {
            return;
        }
        if (d == i) {
            ans = root;
            return;
        }
        if (root == u) {
            d = i;
            return;
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
}
```

#### C++

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
    TreeNode* findNearestRightNode(TreeNode* root, TreeNode* u) {
        TreeNode* ans;
        int d = 0;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int i) {
            if (!root || ans) {
                return;
            }
            if (d == i) {
                ans = root;
                return;
            }
            if (root == u) {
                d = i;
                return;
            }
            dfs(root->left, i + 1);
            dfs(root->right, i + 1);
        };
        dfs(root, 1);
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findNearestRightNode(root *TreeNode, u *TreeNode) *TreeNode {
	d := 0
	var ans *TreeNode
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, i int) {
		if root == nil || ans != nil {
			return
		}
		if d == i {
			ans = root
			return
		}
		if root == u {
			d = i
			return
		}
		dfs(root.Left, i+1)
		dfs(root.Right, i+1)
	}
	dfs(root, 1)
	return ans
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} u
 * @return {TreeNode}
 */
var findNearestRightNode = function (root, u) {
    let d = 0;
    let ans = null;
    function dfs(root, i) {
        if (!root || ans) {
            return;
        }
        if (d == i) {
            ans = root;
            return;
        }
        if (root == u) {
            d = i;
            return;
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
    dfs(root, 1);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
