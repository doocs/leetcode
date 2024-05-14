# [2313. 二叉树中得到结果所需的最少翻转次数 🔒](https://leetcode.cn/problems/minimum-flips-in-binary-tree-to-get-result)

[English Version](/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/README_EN.md)

<!-- tags:树,深度优先搜索,动态规划,二叉树 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定<strong>二叉树</strong>的根 <code>root</code>，具有以下属性:</p>

<ul>
	<li><strong>叶节点&nbsp;</strong>的值为 <code>0</code> 或 <code>1</code>，分别表示 <code>false</code> 和 <code>true</code>。</li>
	<li><strong>非叶节点</strong>的值为 <code>2</code>、<code>3</code>、<code>4</code>、<code>5</code>，分别表示布尔运算&nbsp;<code>OR</code>,&nbsp;<code>AND</code>,&nbsp;<code>XOR</code>,&nbsp;<code>NOT</code>。</li>
</ul>

<p>您还将得到一个布尔型&nbsp;<code>result</code>，这是 <code>root</code>&nbsp;节点的期望&nbsp;<strong>评价</strong><strong>&nbsp;</strong>结果。</p>

<p data-group="1-1">对节点的评价计算如下:</p>

<ul>
	<li>如果节点是叶节点，则评价是节点的&nbsp;<strong>值</strong>，即 <code>true</code> 或&nbsp;<code>false</code>.</li>
	<li>否则, 将其值的布尔运算应用于子节点的&nbsp;<strong>评价</strong>，该节点的&nbsp;<strong>评价&nbsp;</strong>即为布尔运算后的结果。</li>
</ul>

<p>在一个操作中，您可以&nbsp;<strong>翻转&nbsp;</strong>一个叶节点，这将导致一个 <code>false</code>&nbsp;节点变为 <code>true</code>&nbsp;节点，一个 <code>true</code>&nbsp;节点变为 <code>false</code>&nbsp;节点。</p>

<p>返回<em>需要执行的最小操作数，以使 </em><code>root</code><em>&nbsp;的</em><em>评价得到&nbsp;</em><code>result</code>。可以证明，总有办法达到 <code>result</code>。</p>

<p data-group="1-1"><strong>叶节点&nbsp;</strong>是没有子节点的节点。</p>

<p>注意: <code>NOT</code> 节点只有左孩子或只有右孩子，但其他非叶节点同时拥有左孩子和右孩子。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/images/operationstree.png" style="width: 500px; height: 179px;" />
<pre>
<strong>输入:</strong> root = [3,5,4,2,null,1,1,1,0], result = true
<strong>输出:</strong> 2
<strong>解释:</strong>
可以证明，至少需要翻转 2 个节点才能使树的 root 评价为 true。上面的图显示了实现这一目标的一种方法。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [0], result = false
<strong>输出:</strong> 0
<strong>解释:</strong>
树的 root 的评价已经为 false，所以 0 个节点必须翻转。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中的节点数在 <code>[1, 10<sup>5</sup>]</code>&nbsp;范围内。</li>
	<li><code>0 &lt;= Node.val &lt;= 5</code></li>
	<li><code>OR</code>, <code>AND</code>, <code>XOR</code>&nbsp;节点有&nbsp;<code>2</code> 个子节点。</li>
	<li><code>NOT</code> 只有一个&nbsp;<code>1</code> 子节点。</li>
	<li>叶节点的值为 <code>0</code> 或&nbsp;<code>1</code>.</li>
	<li>非叶节点的值为<code>2</code>, <code>3</code>, <code>4</code>,&nbsp;<code>5</code>.</li>
</ul>

## 解法

### 方法一：树形 DP + 分情况讨论

我们定义一个函数 $dfs(root)$，它的返回值是一个长度为 $2$ 的数组，其中第一个表示将 $root$ 节点的值变成 `false` 所需要的最少翻转次数，第二个表示将 $root$ 节点的值变成 `true` 所需要的最少翻转次数。那么答案为 $dfs(root)[result]$。

函数 $dfs(root)$ 的实现如下：

如果 $root$ 为空，那么返回 $[+\infty, +\infty]$。

否则，我们记 $root$ 的值为 $x$，左子树的返回值为 $l$，右子树的返回值为 $r$，然后分情况讨论：

-   如果 $x \in \{0, 1\}$，那么返回 $[x, x \oplus 1]$。
-   如果 $x = 2$，即布尔运算符是 `OR`，为了使 $root$ 的值为 `false`，我们需要将左右子树的值都变成 `false`，因此返回值的第一个元素为 $l[0] + r[0]$；为了使 $root$ 的值为 `true`，我们需要将左右子树的值中至少有一个变成 `true`，因此返回值的第二个元素为 $\min(l[0] + r[1], l[1] + r[0], l[1] + r[1])$。
-   如果 $x = 3$，即布尔运算符是 `AND`，为了使 $root$ 的值为 `false`，我们需要将左右子树的值中至少有一个变成 `false`，因此返回值的第一个元素为 $\min(l[0] + r[0], l[0] + r[1], l[1] + r[0])$；为了使 $root$ 的值为 `true`，我们需要将左右子树的值都变成 `true`，因此返回值的第二个元素为 $l[1] + r[1]$。
-   如果 $x = 4$，即布尔运算符是 `XOR`，为了使 $root$ 的值为 `false`，我们需要将左右子树的值同为 `false` 或同为 `true`，因此返回值的第一个元素为 $\min(l[0] + r[0], l[1] + r[1])$；为了使 $root$ 的值为 `true`，我们需要将左右子树的值不同，因此返回值的第二个元素为 $\min(l[0] + r[1], l[1] + r[0])$。
-   如果 $x = 5$，即布尔运算符是 `NOT`，为了使 $root$ 的值为 `false`，我们需要将左右子树的值中至少有一个变成 `true`，因此返回值的第一个元素为 $\min(l[1], r[1])$；为了使 $root$ 的值为 `true`，我们需要将左右子树的值中至少有一个变成 `false`，因此返回值的第二个元素为 $\min(l[0], r[0])$。

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
    def minimumFlips(self, root: Optional[TreeNode], result: bool) -> int:
        def dfs(root: Optional[TreeNode]) -> (int, int):
            if root is None:
                return inf, inf
            x = root.val
            if x in (0, 1):
                return x, x ^ 1
            l, r = dfs(root.left), dfs(root.right)
            if x == 2:
                return l[0] + r[0], min(l[0] + r[1], l[1] + r[0], l[1] + r[1])
            if x == 3:
                return min(l[0] + r[0], l[0] + r[1], l[1] + r[0]), l[1] + r[1]
            if x == 4:
                return min(l[0] + r[0], l[1] + r[1]), min(l[0] + r[1], l[1] + r[0])
            return min(l[1], r[1]), min(l[0], r[0])

        return dfs(root)[int(result)]
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
    public int minimumFlips(TreeNode root, boolean result) {
        return dfs(root)[result ? 1 : 0];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1 << 30, 1 << 30};
        }
        int x = root.val;
        if (x < 2) {
            return new int[] {x, x ^ 1};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int a = 0, b = 0;
        if (x == 2) {
            a = l[0] + r[0];
            b = Math.min(l[0] + r[1], Math.min(l[1] + r[0], l[1] + r[1]));
        } else if (x == 3) {
            a = Math.min(l[0] + r[0], Math.min(l[0] + r[1], l[1] + r[0]));
            b = l[1] + r[1];
        } else if (x == 4) {
            a = Math.min(l[0] + r[0], l[1] + r[1]);
            b = Math.min(l[0] + r[1], l[1] + r[0]);
        } else {
            a = Math.min(l[1], r[1]);
            b = Math.min(l[0], r[0]);
        }
        return new int[] {a, b};
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
    int minimumFlips(TreeNode* root, bool result) {
        function<pair<int, int>(TreeNode*)> dfs = [&](TreeNode* root) -> pair<int, int> {
            if (!root) {
                return {1 << 30, 1 << 30};
            }
            int x = root->val;
            if (x < 2) {
                return {x, x ^ 1};
            }
            auto [l0, l1] = dfs(root->left);
            auto [r0, r1] = dfs(root->right);
            int a = 0, b = 0;
            if (x == 2) {
                a = l0 + r0;
                b = min({l0 + r1, l1 + r0, l1 + r1});
            } else if (x == 3) {
                a = min({l0 + r0, l0 + r1, l1 + r0});
                b = l1 + r1;
            } else if (x == 4) {
                a = min(l0 + r0, l1 + r1);
                b = min(l0 + r1, l1 + r0);
            } else {
                a = min(l1, r1);
                b = min(l0, r0);
            }
            return {a, b};
        };
        auto [a, b] = dfs(root);
        return result ? b : a;
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
func minimumFlips(root *TreeNode, result bool) int {
	var dfs func(*TreeNode) (int, int)
	dfs = func(root *TreeNode) (int, int) {
		if root == nil {
			return 1 << 30, 1 << 30
		}
		x := root.Val
		if x < 2 {
			return x, x ^ 1
		}
		l0, l1 := dfs(root.Left)
		r0, r1 := dfs(root.Right)
		var a, b int
		if x == 2 {
			a = l0 + r0
			b = min(l0+r1, min(l1+r0, l1+r1))
		} else if x == 3 {
			a = min(l0+r0, min(l0+r1, l1+r0))
			b = l1 + r1
		} else if x == 4 {
			a = min(l0+r0, l1+r1)
			b = min(l0+r1, l1+r0)
		} else {
			a = min(l1, r1)
			b = min(l0, r0)
		}
		return a, b
	}
	a, b := dfs(root)
	if result {
		return b
	}
	return a
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

function minimumFlips(root: TreeNode | null, result: boolean): number {
    const dfs = (root: TreeNode | null): [number, number] => {
        if (!root) {
            return [1 << 30, 1 << 30];
        }
        const x = root.val;
        if (x < 2) {
            return [x, x ^ 1];
        }
        const [l0, l1] = dfs(root.left);
        const [r0, r1] = dfs(root.right);
        if (x === 2) {
            return [l0 + r0, Math.min(l0 + r1, l1 + r0, l1 + r1)];
        }
        if (x === 3) {
            return [Math.min(l0 + r0, l0 + r1, l1 + r0), l1 + r1];
        }
        if (x === 4) {
            return [Math.min(l0 + r0, l1 + r1), Math.min(l0 + r1, l1 + r0)];
        }
        return [Math.min(l1, r1), Math.min(l0, r0)];
    };
    return dfs(root)[result ? 1 : 0];
}
```

<!-- tabs:end -->

<!-- end -->
