---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1973. 值等于子节点值之和的节点数量 🔒](https://leetcode.cn/problems/count-nodes-equal-to-sum-of-descendants)

[English Version](/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一颗二叉树的根节点&nbsp;<code>root</code>&nbsp;，返回满足条件：节点的值等于该节点所有子节点的值之和&nbsp;<em>的节点的数量。</em></p>

<p>一个节点&nbsp;<code>x</code>&nbsp;的&nbsp;<strong>子节点</strong>&nbsp;是指从节点&nbsp;<code>x</code>&nbsp;出发，到所有叶子节点路径上的节点。没有子节点的节点的子节点和视为&nbsp;<code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-16-50-diagram-drawio-diagrams-net.png" style="width: 250px; height: 207px;" />
<pre>
<strong>输入:</strong> root = [10,3,4,2,1]
<strong>输出:</strong> 2
<strong>解释:</strong>
对于值为10的节点: 其子节点之和为： 3+4+2+1 = 10。
对于值为3的节点：其子节点之和为： 2+1 = 3。
</pre>

<p><strong>示例&nbsp;2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-25-21-diagram-drawio-diagrams-net.png" style="height: 196px; width: 200px;" />
<pre>
<strong>输入:</strong> root = [2,3,null,2,null]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有节点满足其值等于子节点之和。
</pre>

<p><strong>示例&nbsp;3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-23-53-diagram-drawio-diagrams-net.png" style="width: 50px; height: 50px;" />
<pre>
<strong>输入:</strong> root = [0]
<strong>输出:</strong> 1
<strong>解释:</strong>
对于值为0的节点：因为它没有子节点，所以自己点之和为0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量范围：&nbsp;<code>[1, 10<sup>5</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

<!-- thinking:start -->

> **思考**
>
> 每个结点要与其全部子孙之和比较。若对每个结点再遍历子树，总时间为平方级。
>
> 一次后序 DFS 返回子树和：左右之和等于结点值则计数加一，并向上返回「自身加左右」。整树一遍即可。

<!-- thinking:end -->

我们设计一个函数 $dfs(root)$，该函数返回以 $root$ 为根节点的子树的所有节点值之和。函数 $dfs(root)$ 的执行过程如下：

- 如果 $root$ 为空，返回 $0$；
- 否则，我们递归地计算 $root$ 的左子树和右子树的节点值之和，记为 $l$ 和 $r$；如果 $l + r = root.val$，说明以 $root$ 为根节点的子树满足条件，我们将答案加 $1$；最后，返回 $root.val + l + r$。

然后我们调用函数 $dfs(root)$，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    def equalToDescendants(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            if l + r == root.val:
                nonlocal ans
                ans += 1
            return root.val + l + r

        ans = 0
        dfs(root)
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
    private int ans;

    public int equalToDescendants(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (l + r == root.val) {
            ++ans;
        }
        return root.val + l + r;
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
    int equalToDescendants(TreeNode* root) {
        int ans = 0;
        function<long long(TreeNode*)> dfs = [&](TreeNode* root) -> long long {
            if (!root) {
                return 0;
            }
            auto l = dfs(root->left);
            auto r = dfs(root->right);
            ans += l + r == root->val;
            return root->val + l + r;
        };
        dfs(root);
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
func equalToDescendants(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l+r == root.Val {
			ans++
		}
		return root.Val + l + r
	}
	dfs(root)
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 每个结点要和它全部子孙的值之和比较。若对每个结点再单独遍历子树，总时间是平方级。后序遍历在离开结点时已经知道左右子树的和，一次遍历就能判断并向上汇总。
>
> $n$ 可以到 $10^5$。后序先走进左孩子，一条左链会让递归在子树和算完之前溢出。结点值最大 $10^5$，整棵子树的和可以到 $10^{10}$，32 位整数装不下。
>
> 子孙和只在左右子树都处理完之后才知道。显式栈用状态把“先展开孩子”和“孩子都已返回”分开，弹出完成标记时读取左右子树和。和数用 64 位整数保存，左右之和等于结点值就计数，再把自身加进去交给父结点。
>
> 每个结点入栈一次。空孩子的和视为 $0$。

<!-- thinking:end -->

我们用显式栈做后序遍历。栈中每个元素是结点和状态：状态 $0$ 先压入完成标记，再压入右孩子和左孩子；状态 $1$ 表示左右子树都已求出子树和。弹出完成标记后，取出左子树和 $l$ 与右子树和 $r$。若 $l + r = \textit{val}$，答案加一。该结点的子树和为 $\textit{val} + l + r$，存下来给父结点使用。空孩子的和为 $0$。子树和用 64 位整数，$n$ 个不超过 $10^5$ 的结点相加不会溢出。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    def equalToDescendants(self, root: Optional[TreeNode]) -> int:
        ans = 0
        stk = [(root, 0)]
        sub = {}
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                if node.right is not None:
                    stk.append((node.right, 0))
                if node.left is not None:
                    stk.append((node.left, 0))
                continue
            l = sub[id(node.left)] if node.left is not None else 0
            r = sub[id(node.right)] if node.right is not None else 0
            if l + r == node.val:
                ans += 1
            sub[id(node)] = node.val + l + r
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
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int equalToDescendants(TreeNode root) {
        int ans = 0;
        Map<TreeNode, Long> sub = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, 0));
        }
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, 1));
                if (node.right != null) {
                    stk.push(new Frame(node.right, 0));
                }
                if (node.left != null) {
                    stk.push(new Frame(node.left, 0));
                }
                continue;
            }
            long l = node.left == null ? 0 : sub.get(node.left);
            long r = node.right == null ? 0 : sub.get(node.right);
            if (l + r == node.val) {
                ++ans;
            }
            sub.put(node, node.val + l + r);
        }
        return ans;
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
    int equalToDescendants(TreeNode* root) {
        int ans = 0;
        unordered_map<TreeNode*, long long> sub;
        vector<pair<TreeNode*, int>> stk;
        if (root) {
            stk.emplace_back(root, 0);
        }
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.emplace_back(node, 1);
                if (node->right) {
                    stk.emplace_back(node->right, 0);
                }
                if (node->left) {
                    stk.emplace_back(node->left, 0);
                }
                continue;
            }
            long long l = node->left ? sub[node->left] : 0;
            long long r = node->right ? sub[node->right] : 0;
            ans += l + r == node->val;
            sub[node] = node->val + l + r;
        }
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
func equalToDescendants(root *TreeNode) (ans int) {
	sub := map[*TreeNode]int{}
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{}
	if root != nil {
		stk = append(stk, frame{root, 0})
	}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node := cur.node
		if cur.state == 0 {
			stk = append(stk, frame{node, 1})
			if node.Right != nil {
				stk = append(stk, frame{node.Right, 0})
			}
			if node.Left != nil {
				stk = append(stk, frame{node.Left, 0})
			}
			continue
		}
		l, r := 0, 0
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		if l+r == node.Val {
			ans++
		}
		sub[node] = node.Val + l + r
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
