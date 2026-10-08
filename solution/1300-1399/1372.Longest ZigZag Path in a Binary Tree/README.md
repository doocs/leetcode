---
comments: true
difficulty: 中等
rating: 1713
source: 第 21 场双周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 动态规划
    - 二叉树
    - 树形 DP
---

<!-- problem:start -->

# [1372. 二叉树中的最长交错路径](https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree)

[English Version](/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵以&nbsp;<code>root</code>&nbsp;为根的二叉树，二叉树中的交错路径定义如下：</p>

<ul>
	<li>选择二叉树中 <strong>任意</strong>&nbsp;节点和一个方向（左或者右）。</li>
	<li>如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。</li>
	<li>改变前进方向：左变右或者右变左。</li>
	<li>重复第二步和第三步，直到你在树中无法继续移动。</li>
</ul>

<p>交错路径的长度定义为：<strong>访问过的节点数目 - 1</strong>（单个节点的路径长度为 0 ）。</p>

<p>请你返回给定树中最长 <strong>交错路径</strong>&nbsp;的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/images/sample_1_1702.png" style="height: 283px; width: 151px;"></strong></p>

<pre><strong>输入：</strong>root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
<strong>输出：</strong>3
<strong>解释：</strong>蓝色节点为树中最长交错路径（右 -&gt; 左 -&gt; 右）。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1372.Longest%20ZigZag%20Path%20in%20a%20Binary%20Tree/images/sample_2_1702.png" style="height: 253px; width: 120px;"></strong></p>

<pre><strong>输入：</strong>root = [1,1,1,null,1,null,null,1,1,null,1]
<strong>输出：</strong>4
<strong>解释：</strong>蓝色节点为树中最长交错路径（左 -&gt; 右 -&gt; 左 -&gt; 右）。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树最多有&nbsp;<code>50000</code>&nbsp;个节点。</li>
	<li>每个节点的值在&nbsp;<code>[1, 100]</code> 之间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 之字形路径须左右交替下降。从每个节点、每种方向重新搜索会重复子树。DFS 时携带「若下一步向左/向右，当前已有的交错长度」： $l$ 表示到达本节点时最后一步向左的长度， $r$ 同理向右。进入左孩子时新的向左长度是 $r+1$，向右长度清零；右孩子对称。全程维护最大值。

<!-- thinking:end -->

时间复杂度 $O(n)$，其中 $n$ 是树中节点的个数。

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
    def longestZigZag(self, root: TreeNode) -> int:
        def dfs(root, l, r):
            if root is None:
                return
            nonlocal ans
            ans = max(ans, l, r)
            dfs(root.left, r + 1, 0)
            dfs(root.right, 0, l + 1)

        ans = 0
        dfs(root, 0, 0)
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

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode root, int l, int r) {
        if (root == null) {
            return;
        }
        ans = Math.max(ans, Math.max(l, r));
        dfs(root.left, r + 1, 0);
        dfs(root.right, 0, l + 1);
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
    int ans = 0;

    int longestZigZag(TreeNode* root) {
        dfs(root, 0, 0);
        return ans;
    }

    void dfs(TreeNode* root, int l, int r) {
        if (!root) return;
        ans = max(ans, max(l, r));
        dfs(root->left, r + 1, 0);
        dfs(root->right, 0, l + 1);
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
func longestZigZag(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode, l, r int)
	dfs = func(root *TreeNode, l, r int) {
		if root == nil {
			return
		}
		ans = max(ans, max(l, r))
		dfs(root.Left, r+1, 0)
		dfs(root.Right, 0, l+1)
	}
	dfs(root, 0, 0)
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 交错路径必须左右交替下降。从每个结点带着到达时最后一步向左的长度 $l$、向右的长度 $r$ 向下递归，左孩子接上 $r+1$ 并把向右长度清零，右孩子对称，可以在较短的树上求出最长交错路径。
>
> 结点个数可达 $5\times 10^4$。一条链会按结点个数递归，调用栈会溢出。
>
> 每个结点的 $l$ 和 $r$ 只由父结点的另一步决定，答案又只取全程最大值，不需要等子树返回。
>
> 因此用显式栈保存结点以及到达它的 $l$、$r$。弹出后更新答案，再把右孩子、左孩子连同新的长度压入栈。左孩子后压入，因而先被处理。

<!-- thinking:end -->

我们用显式栈遍历二叉树。栈中每个元素保存当前结点，以及到达该结点时最后一步向左、向右的交错长度 $l$ 和 $r$。

弹出一个结点后，用 $\max(l, r)$ 更新答案。若存在左孩子，则它的向左长度为 $r+1$、向右长度为 $0$；右孩子的向右长度为 $l+1$、向左长度为 $0$。根结点的 $l$ 和 $r$ 都是 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树中节点的个数。

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
    def longestZigZag(self, root: TreeNode) -> int:
        ans = 0
        stk = [(root, 0, 0)]
        while stk:
            node, l, r = stk.pop()
            if node is None:
                continue
            ans = max(ans, l, r)
            stk.append((node.right, 0, l + 1))
            stk.append((node.left, r + 1, 0))
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
        int l;
        int r;

        Frame(TreeNode node, int l, int r) {
            this.node = node;
            this.l = l;
            this.r = r;
        }
    }

    public int longestZigZag(TreeNode root) {
        int ans = 0;
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, 0, 0));
        }
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            ans = Math.max(ans, Math.max(cur.l, cur.r));
            if (cur.node.right != null) {
                stk.push(new Frame(cur.node.right, 0, cur.l + 1));
            }
            if (cur.node.left != null) {
                stk.push(new Frame(cur.node.left, cur.r + 1, 0));
            }
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
    int longestZigZag(TreeNode* root) {
        int ans = 0;
        vector<tuple<TreeNode*, int, int>> stk{{root, 0, 0}};
        while (!stk.empty()) {
            auto [node, l, r] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            ans = max(ans, max(l, r));
            stk.emplace_back(node->right, 0, l + 1);
            stk.emplace_back(node->left, r + 1, 0);
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
func longestZigZag(root *TreeNode) int {
	ans := 0
	type frame struct {
		node *TreeNode
		l, r int
	}
	stk := []frame{{root, 0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if cur.node == nil {
			continue
		}
		ans = max(ans, max(cur.l, cur.r))
		stk = append(stk, frame{cur.node.Right, 0, cur.l + 1}, frame{cur.node.Left, cur.r + 1, 0})
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
