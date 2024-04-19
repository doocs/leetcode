# [2773. 特殊二叉树的高度 🔒](https://leetcode.cn/problems/height-of-special-binary-tree)

[English Version](/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/README_EN.md)

<!-- tags:树,深度优先搜索,广度优先搜索,二叉树 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵具有 <code>n</code> 个节点的 <strong>特殊</strong> 二叉树的根节点 <code>root</code> 。特殊二叉树的节点编号从 <code>1</code> 到 <code>n</code> 。假设这棵树有 <code>k</code> 个叶子，顺序如下：<code>b<sub>1</sub> &lt; b<sub>2</sub> &lt; ... &lt; b<sub>k</sub></code> 。</p>

<p>这棵树的叶子节点有一个 <strong>特殊</strong> 属性 ！对于每个叶子节点 <code>b<sub>i</sub></code> ，满足以下条件：</p>

<ul>
	<li>如果 <code>i &lt; k</code> ，则 <code>b<sub>i</sub></code> 的右子节点为 <code>b<sub>i&nbsp;</sub><sub>+ 1</sub></code> ；否则为 <code>b<sub>1</sub></code> 。</li>
	<li>如果 <code>i &gt; 1</code> ，则 <code>b<sub>i</sub></code> 的左子节点为 <code>b<sub>i&nbsp;</sub><sub>- 1</sub></code> ；否则为 <code>b<sub>k</sub></code> 。</li>
</ul>

<p>返回给定树的高度。</p>

<p><strong>注意</strong>：二叉树的高度是指从根节点到任何其他节点的 <strong>最长路径</strong> 的长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1;</strong></p>

<pre>
<b>输入：</b>root = [1,2,3,null,null,4,5]
<b>输出：</b>2
<strong>解释：给</strong>定树如下图所示。每个叶子节点的左子节点是它左边的叶子节点（用蓝色边表示）。每个叶子节点的右子节点是它右边的叶子节点（用红色边表示）。我们可以看出，该图的高度为2。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/images/1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 200px; height: 200px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>root = [1,2]
<b>输出：</b>1
<b>解释：</b>给定树如下图所示。只有一个叶子节点，所以它没有左子节点或右子节点。我们可以看出，该图的高度为 1。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/images/2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 95px; height: 122px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>root = [1,2,3,null,null,4,null,5,6]
<b>输出：</b>3
<strong>解释：</strong>给定树如下图所示。每个叶子节点的左子节点是它左边的叶子节点（用蓝色边表示）。每个叶子节点的右子节点是它右边的叶子节点（用红色边表示）。我们可以看出，该图的高度为3。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/images/3.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 200px; height: 280px;" /></p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n 为树中节点的数量</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= node.val &lt;= n</code></li>
	<li>输入保证每个 <code>node.val</code> 的值是唯一的。</li>
</ul>

## 解法

### 方法一：DFS

题目的关键在于如何判断一个节点是叶子节点，我们设计一个函数 $dfs(root, d)$，其中 $root$ 表示当前节点，而 $d$ 表示当前节点的深度，我们每次搜索时，更新答案 $ans = \max(ans, d)$，然后判断当前节点是否为叶子节点，如果当前节点有左子节点，且左子节点的右子节点不是当前节点，那么我们递归调用 $dfs(root.left, d + 1)$，如果当前节点有右子节点，且右子节点的左子节点不是当前节点，那么我们递归调用 $dfs(root.right, d + 1)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def heightOfTree(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode], d: int):
            nonlocal ans
            ans = max(ans, d)
            if root.left and root.left.right != root:
                dfs(root.left, d + 1)
            if root.right and root.right.left != root:
                dfs(root.right, d + 1)

        ans = 0
        dfs(root, 0)
        return ans
```

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

    public int heightOfTree(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int d) {
        ans = Math.max(ans, d++);
        if (root.left != null && root.left.right != root) {
            dfs(root.left, d);
        }
        if (root.right != null && root.right.left != root) {
            dfs(root.right, d);
        }
    }
}
```

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
    int heightOfTree(TreeNode* root) {
        int ans = 0;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int d) {
            ans = max(ans, d++);
            if (root->left && root->left->right != root) {
                dfs(root->left, d);
            }
            if (root->right && root->right->left != root) {
                dfs(root->right, d);
            }
        };
        dfs(root, 0);
        return ans;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func heightOfTree(root *TreeNode) (ans int) {
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, d int) {
		if ans < d {
			ans = d
		}
		d++
		if root.Left != nil && root.Left.Right != root {
			dfs(root.Left, d)
		}
		if root.Right != nil && root.Right.Left != root {
			dfs(root.Right, d)
		}
	}
	dfs(root, 0)
	return
}
```

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function heightOfTree(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (root: TreeNode | null, d: number) => {
        ans = Math.max(ans, d++);
        if (root.left && root.left.right !== root) {
            dfs(root.left, d);
        }
        if (root.right && root.right.left !== root) {
            dfs(root.right, d);
        }
    };
    dfs(root, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
