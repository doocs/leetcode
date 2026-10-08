---
comments: true
difficulty: 困难
tags:
    - 树
    - 深度优先搜索
    - 动态规划
    - 二叉树
    - 树形 DP
---

<!-- problem:start -->

# [968. 监控二叉树](https://leetcode.cn/problems/binary-tree-cameras)

[English Version](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二叉树，我们在树的节点上安装摄像头。</p>

<p>节点上的每个摄影头都可以监视<strong>其父对象、自身及其直接子对象。</strong></p>

<p>计算监控树的所有节点所需的最小摄像头数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_01.png" style="height: 163px; width: 138px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>如图所示，一台摄像头足以监控所有节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_02.png" style="height: 312px; width: 139px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,null,0,null,null,0]
<strong>输出：</strong>2
<strong>解释：</strong>需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
</pre>

<p><br>
<strong>提示：</strong></p>

<ol>
	<li>给定树的节点数的范围是&nbsp;<code>[1, 1000]</code>。</li>
	<li>每个节点的值都是 0。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划（树形 DP）

<!-- thinking:start -->

> **思考**
>
> 摄像头覆盖自身、父与子，求最少个数。每个结点的最优依赖于子树状态，需区分“本结点有摄像头 / 被子覆盖 / 未被覆盖”。树形 DP 自底向上返回三种最小值，根不能处于未被覆盖状态。

<!-- thinking:end -->

对于每个节点，我们定义三种状态：

- `a`：当前节点有摄像头
- `b`：当前节点无摄像头，但被子节点监控
- `c`：当前节点无摄像头，也没被子节点监控

接下来，我们设计一个函数 $dfs(root)$，它将返回一个长度为 3 的数组，表示以 `root` 为根的子树中，三种状态下的最小摄像头数量。那么答案就是 $\min(dfs(root)[0], dfs(root)[1])$。

函数 $dfs(root)$ 的计算过程如下：

如果 `root` 为空，则返回 $[inf, 0, 0]$，其中 `inf` 表示一个很大的数，它用于表示不可能的情况。

否则，我们递归计算 `root` 的左右子树，分别得到 $[la, lb, lc]$ 和 $[ra, rb, rc]$。

- 如果当前节点有摄像头，那么它的左右节点必须都是被监控的状态，即 $a = \min(la, lb, lc) + \min(ra, rb, rc) + 1$。
- 如果当前节点无摄像头，但被子节点监控，那么子节点可以是其中之一或者两个都有摄像头，即 $b = \min(la + rb, lb + ra, la + ra)$。
- 如果当前节点无摄像头，也没被子节点监控，那么子节点必须被其子节点监控，即 $c = lb + rb$。

最后，我们返回 $[a, b, c]$。

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
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return inf, 0, 0
            la, lb, lc = dfs(root.left)
            ra, rb, rc = dfs(root.right)
            a = min(la, lb, lc) + min(ra, rb, rc) + 1
            b = min(la + rb, lb + ra, la + ra)
            c = lb + rb
            return a, b, c

        a, b, _ = dfs(root)
        return min(a, b)
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
    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1 << 29, 0, 0};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
        int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
        int c = l[1] + r[1];
        return new int[] {a, b, c};
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
struct Status {
    int a, b, c;
};

class Solution {
public:
    int minCameraCover(TreeNode* root) {
        auto [a, b, _] = dfs(root);
        return min(a, b);
    }

    Status dfs(TreeNode* root) {
        if (!root) {
            return {1 << 29, 0, 0};
        }
        auto [la, lb, lc] = dfs(root->left);
        auto [ra, rb, rc] = dfs(root->right);
        int a = 1 + min({la, lb, lc}) + min({ra, rb, rc});
        int b = min({la + ra, la + rb, lb + ra});
        int c = lb + rb;
        return {a, b, c};
    };
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
func minCameraCover(root *TreeNode) int {
	var dfs func(*TreeNode) (int, int, int)
	dfs = func(root *TreeNode) (int, int, int) {
		if root == nil {
			return 1 << 29, 0, 0
		}
		la, lb, lc := dfs(root.Left)
		ra, rb, rc := dfs(root.Right)
		a := 1 + min(la, min(lb, lc)) + min(ra, min(rb, rc))
		b := min(la+ra, min(la+rb, lb+ra))
		c := lb + rb
		return a, b, c
	}
	a, b, _ := dfs(root)
	return min(a, b)
}
```

#### TypeScript

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

function minCameraCover(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null): number[] => {
        if (!root) {
            return [1 << 29, 0, 0];
        }
        const [la, lb, lc] = dfs(root.left);
        const [ra, rb, rc] = dfs(root.right);
        const a = 1 + Math.min(la, lb, lc) + Math.min(ra, rb, rc);
        const b = Math.min(la + ra, la + rb, lb + ra);
        const c = lb + rb;
        return [a, b, c];
    };
    const [a, b, _] = dfs(root);
    return Math.min(a, b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈上的树形 DP

<!-- thinking:start -->

> **思考**
>
> 摄像头覆盖自身、父结点与子结点，最少个数取决于每个结点怎么放置。自底向上区分三种状态：本结点有摄像头、被子结点覆盖、未被覆盖。在较短的树上，递归返回这三种最小值，根不能停在未被覆盖的状态。
>
> 结点个数可达 $1000$。左链使这次后序遍历按结点个数递归，调用栈会溢出。
>
> 每个结点的三种状态只由左右子树的三种状态决定，所以必须先处理孩子，再处理父亲。
>
> 因此用显式栈做后序遍历。进入结点时压入退出标记和左右孩子，退出时按原来的三个式子写入该结点的状态。根的答案取前两个状态的较小值。

<!-- thinking:end -->

对于每个节点，我们定义三种状态：

- `a`：当前节点有摄像头
- `b`：当前节点无摄像头，但被子节点监控
- `c`：当前节点无摄像头，也没被子节点监控

空结点对应 $(inf, 0, 0)$，其中 $inf$ 是一个很大的数，表示不可能的情况。

我们用显式栈对二叉树做后序遍历。进入结点时压入退出标记，再压入右孩子和左孩子；退出时，左右子树的状态已经记下，记为 $[la, lb, lc]$ 和 $[ra, rb, rc]$。

- 如果当前节点有摄像头，那么它的左右节点必须都是被监控的状态，即 $a = \min(la, lb, lc) + \min(ra, rb, rc) + 1$。
- 如果当前节点无摄像头，但被子节点监控，那么子节点可以是其中之一或者两个都有摄像头，即 $b = \min(la + rb, lb + ra, la + ra)$。
- 如果当前节点无摄像头，也没被子节点监控，那么子节点必须被其子节点监控，即 $c = lb + rb$。

根结点不能处于未被覆盖的状态，答案是 $\min(a, b)$。

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
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        stk = [(root, 0)]
        sub = {}
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.right, 0))
                stk.append((node.left, 0))
                continue
            la, lb, lc = sub[id(node.left)] if node.left is not None else (inf, 0, 0)
            ra, rb, rc = sub[id(node.right)] if node.right is not None else (inf, 0, 0)
            a = min(la, lb, lc) + min(ra, rb, rc) + 1
            b = min(la + rb, lb + ra, la + ra)
            c = lb + rb
            sub[id(node)] = (a, b, c)
        a, b, _ = sub[id(root)]
        return min(a, b)
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
    private static final int INF = 1 << 29;

    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, int[]> sub = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
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
            int[] l = node.left == null ? new int[] {INF, 0, 0} : sub.get(node.left);
            int[] r = node.right == null ? new int[] {INF, 0, 0} : sub.get(node.right);
            int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
            int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
            int c = l[1] + r[1];
            sub.put(node, new int[] {a, b, c});
        }
        int[] ans = sub.get(root);
        return Math.min(ans[0], ans[1]);
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
    int minCameraCover(TreeNode* root) {
        if (!root) {
            return 0;
        }
        const int inf = 1 << 29;
        unordered_map<TreeNode*, array<int, 3>> sub;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
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
            array<int, 3> l = node->left ? sub[node->left] : array<int, 3>{inf, 0, 0};
            array<int, 3> r = node->right ? sub[node->right] : array<int, 3>{inf, 0, 0};
            int a = 1 + min({l[0], l[1], l[2]}) + min({r[0], r[1], r[2]});
            int b = min({l[0] + r[0], l[0] + r[1], l[1] + r[0]});
            int c = l[1] + r[1];
            sub[node] = {a, b, c};
        }
        auto ans = sub[root];
        return min(ans[0], ans[1]);
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
func minCameraCover(root *TreeNode) int {
	if root == nil {
		return 0
	}
	const inf = 1 << 29
	sub := map[*TreeNode][3]int{}
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
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
		l := [3]int{inf, 0, 0}
		r := [3]int{inf, 0, 0}
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		a := 1 + min(l[0], min(l[1], l[2])) + min(r[0], min(r[1], r[2]))
		b := min(l[0]+r[0], min(l[0]+r[1], l[1]+r[0]))
		c := l[1] + r[1]
		sub[node] = [3]int{a, b, c}
	}
	ans := sub[root]
	return min(ans[0], ans[1])
}
```

#### TypeScript

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

function minCameraCover(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    const inf = 1 << 29;
    const sub = new Map<TreeNode, number[]>();
    const stk: [TreeNode, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (state === 0) {
            stk.push([node, 1]);
            if (node.right) {
                stk.push([node.right, 0]);
            }
            if (node.left) {
                stk.push([node.left, 0]);
            }
            continue;
        }
        const l = node.left ? sub.get(node.left)! : [inf, 0, 0];
        const r = node.right ? sub.get(node.right)! : [inf, 0, 0];
        const a = 1 + Math.min(l[0], l[1], l[2]) + Math.min(r[0], r[1], r[2]);
        const b = Math.min(l[0] + r[0], l[0] + r[1], l[1] + r[0]);
        const c = l[1] + r[1];
        sub.set(node, [a, b, c]);
    }
    const ans = sub.get(root)!;
    return Math.min(ans[0], ans[1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
