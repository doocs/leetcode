# [1145. 二叉树着色游戏](https://leetcode.cn/problems/binary-tree-coloring-game)

[English Version](/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点&nbsp;<code>root</code>，树上总共有 <code>n</code> 个节点，且 <code>n</code> 为奇数，其中每个节点上的值从&nbsp;<code>1</code> 到&nbsp;<code>n</code>&nbsp;各不相同。</p>

<p>&nbsp;</p>

<p>游戏从「一号」玩家开始（「一号」玩家为红色，「二号」玩家为蓝色），最开始时，</p>

<p>「一号」玩家从 <code>[1, n]</code>&nbsp;中取一个值&nbsp;<code>x</code>（<code>1 &lt;= x &lt;= n</code>）；</p>

<p>「二号」玩家也从&nbsp;<code>[1, n]</code>&nbsp;中取一个值&nbsp;<code>y</code>（<code>1 &lt;= y &lt;= n</code>）且&nbsp;<code>y != x</code>。</p>

<p>「一号」玩家给值为&nbsp;<code>x</code>&nbsp;的节点染上红色，而「二号」玩家给值为&nbsp;<code>y</code>&nbsp;的节点染上蓝色。</p>

<p>&nbsp;</p>

<p>之后两位玩家轮流进行操作，每一回合，玩家选择一个他之前涂好颜色的节点，将所选节点一个 <strong>未着色 </strong>的邻节点（即左右子节点、或父节点）进行染色。</p>

<p>如果当前玩家无法找到这样的节点来染色时，他的回合就会被跳过。</p>

<p>若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。</p>

<p>&nbsp;</p>

<p>现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个&nbsp;<code>y</code>&nbsp;值可以确保你赢得这场游戏，则返回&nbsp;<code>true</code>；若无法获胜，就请返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/images/1480-binary-tree-coloring-game.png" style="height: 186px; width: 300px;"></strong></p>

<pre><strong>输入：</strong>root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
<strong>输出：</strong>True
<strong>解释：</strong>第二个玩家可以选择值为 2 的节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的根节点为&nbsp;<code>root</code>，树上由 <code>n</code> 个节点，节点上的值从 <code>1</code> 到 <code>n</code> 各不相同。</li>
	<li><code>n</code> 为奇数。</li>
	<li><code>1 &lt;= x &lt;= n&nbsp;&lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

先通过 $DFS$，找到 $x$ 所在的节点，我们记为 $node$。然后统计 $node$ 的左子树、右子树、父节点方向上的节点个数。如果这三个方向上有任何一个节点个数超过了节点总数的一半，则存在一个必胜策略。

这里 $node$ 父节点方向上的节点个数，可以由节点总数 $n$ 减去 $node$ 及其 $node$ 的左右子树节点数之和得到。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点总数。

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
    def btreeGameWinningMove(self, root: Optional[TreeNode], n: int, x: int) -> bool:
        def dfs(root):
            if root is None or root.val == x:
                return root
            return dfs(root.left) or dfs(root.right)

        def count(root):
            if root is None:
                return 0
            return 1 + count(root.left) + count(root.right)

        node = dfs(root)
        l, r = count(node.left), count(node.right)
        t = n - l - r - 1
        m = max(l, r, t)
        return m > n - m
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
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = dfs(root, x);
        int l = count(node.left);
        int r = count(node.right);
        int m = Math.max(Math.max(l, r), n - l - r - 1);
        return m > n - m;
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }

    private TreeNode dfs(TreeNode root, int x) {
        if (root == null || root.val == x) {
            return root;
        }
        TreeNode l = dfs(root.left, x);
        if (l != null) {
            return l;
        }
        return dfs(root.right, x);
    }
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
    bool btreeGameWinningMove(TreeNode* root, int n, int x) {
        TreeNode* node = dfs(root, x);
        int l = count(node->left);
        int r = count(node->right);
        int m = max(max(l, r), n - l - r - 1);
        return m > n - m;
    }

    int count(TreeNode* root) {
        if (!root) return 0;
        return 1 + count(root->left) + count(root->right);
    }

    TreeNode* dfs(TreeNode* root, int x) {
        if (!root || root->val == x) return root;
        auto l = dfs(root->left, x);
        return l ? l : dfs(root->right, x);
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
func btreeGameWinningMove(root *TreeNode, n int, x int) bool {
	var dfs func(*TreeNode) *TreeNode
	dfs = func(root *TreeNode) *TreeNode {
		if root == nil || root.Val == x {
			return root
		}
		l := dfs(root.Left)
		if l != nil {
			return l
		}
		return dfs(root.Right)
	}
	var count func(*TreeNode) int
	count = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		return 1 + count(root.Left) + count(root.Right)
	}
	node := dfs(root)
	l, r := count(node.Left), count(node.Right)
	m := max(max(l, r), n-l-r-1)
	return m > n-m
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
