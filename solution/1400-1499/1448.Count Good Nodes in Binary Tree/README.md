---
comments: true
difficulty: 中等
rating: 1360
source: 第 26 场双周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1448. 统计二叉树中好节点的数目](https://leetcode.cn/problems/count-good-nodes-in-binary-tree)

[English Version](/solution/1400-1499/1448.Count%20Good%20Nodes%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵根为&nbsp;<code>root</code>&nbsp;的二叉树，请你返回二叉树中好节点的数目。</p>

<p>「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1448.Count%20Good%20Nodes%20in%20Binary%20Tree/images/test_sample_1.png" style="height: 156px; width: 263px;"></strong></p>

<pre><strong>输入：</strong>root = [3,1,4,3,null,1,5]
<strong>输出：</strong>4
<strong>解释：</strong>图中蓝色节点为好节点。
根节点 (3) 永远是个好节点。
节点 4 -&gt; (3,4) 是路径中的最大值。
节点 5 -&gt; (3,4,5) 是路径中的最大值。
节点 3 -&gt; (3,1,3) 是路径中的最大值。</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1448.Count%20Good%20Nodes%20in%20Binary%20Tree/images/test_sample_2.png" style="height: 161px; width: 157px;"></strong></p>

<pre><strong>输入：</strong>root = [3,3,null,4,2]
<strong>输出：</strong>3
<strong>解释：</strong>节点 2 -&gt; (3, 3, 2) 不是好节点，因为 &quot;3&quot; 比它大。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [1]
<strong>输出：</strong>1
<strong>解释：</strong>根节点是好节点。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树中节点数目范围是&nbsp;<code>[1, 10^5]</code>&nbsp;。</li>
	<li>每个节点权值的范围是&nbsp;<code>[-10^4, 10^4]</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 好节点要求根到该点路径上没有更大的值。 $n\le 10^5$，在 DFS 中下传路径最大值 $mx$，当前值不小于 $mx$ 则计数并更新 $mx$。

<!-- thinking:end -->

我们设计一个函数 $dfs(root, mx)$，表示从当前节点 $root$ 开始搜索好节点，其中 $mx$ 表示从根节点到当前节点的路径（不包括当前节点）上的最大值。

函数 $dfs(root, mx)$ 的执行逻辑如下：

如果 $root$ 为空，说明搜索结束，直接返回；

否则，我们判断 $root.val$ 与 $mx$ 的大小关系。如果 $mx \leq root.val$，说明 $root$ 是好节点，答案加一，并且我们需要更新 $mx$ 的值为 $root.val$。

接下来，我们递归调用 $dfs(root.left, mx)$ 和 $dfs(root.right, mx)$。

在主函数中，我们调用 $dfs(root, -10^6)$，其中 $-10^6$ 表示负无穷，因为题目中说明了每个节点权值的范围是 $[-10^4, 10^4]$，所以 $-10^6$ 肯定是一个比所有节点权值都小的值，这样就能保证 $dfs(root, -10^6)$ 一定会把根节点 $root$ 算作好节点。

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
    def goodNodes(self, root: TreeNode) -> int:
        def dfs(root: TreeNode, mx: int):
            if root is None:
                return
            nonlocal ans
            if mx <= root.val:
                ans += 1
                mx = root.val
            dfs(root.left, mx)
            dfs(root.right, mx)

        ans = 0
        dfs(root, -1000000)
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
    private int ans = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, -100000);
        return ans;
    }

    private void dfs(TreeNode root, int mx) {
        if (root == null) {
            return;
        }
        if (mx <= root.val) {
            ++ans;
            mx = root.val;
        }
        dfs(root.left, mx);
        dfs(root.right, mx);
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
    int goodNodes(TreeNode* root) {
        int ans = 0;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int mx) {
            if (!root) {
                return;
            }
            if (mx <= root->val) {
                ++ans;
                mx = root->val;
            }
            dfs(root->left, mx);
            dfs(root->right, mx);
        };
        dfs(root, -1e6);
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
func goodNodes(root *TreeNode) (ans int) {
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, mx int) {
		if root == nil {
			return
		}
		if mx <= root.Val {
			ans++
			mx = root.Val
		}
		dfs(root.Left, mx)
		dfs(root.Right, mx)
	}
	dfs(root, -10001)
	return
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

function goodNodes(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (root: TreeNode | null, mx: number) => {
        if (!root) {
            return;
        }
        if (mx <= root.val) {
            ++ans;
            mx = root.val;
        }
        dfs(root.left, mx);
        dfs(root.right, mx);
    };
    dfs(root, -1e6);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 好节点要求从根到该点的路径上没有更大的值。$n$ 可以到 $10^5$，下传路径最大值 $mx$，当前值不小于 $mx$ 就计数并更新 $mx$，一次遍历就能数完。
>
> 搜索先进入左孩子。一条左链的长度可以到 $n$，递归会在计数完成前溢出。每个结点只和自己路径上的最大值比较，左右孩子互不影响。
>
> 待访问的结点可以连同到达它之前的 $mx$ 放进显式栈。弹出后若当前值不小于 $mx$ 就计数，并把 $mx$ 更新为当前值，再把右孩子和左孩子连同这个 $mx$ 压栈。左孩子后压入，因此先被处理。
>
> 初始 $mx$ 小于结点值的下界 $-10^4$，根一定被算作好节点。每个结点入栈一次。

<!-- thinking:end -->

我们用显式栈从根开始统计好节点。栈中每个元素是一个结点，以及从根到它的父结点路径上的最大值 $mx$。弹出结点后，若结点为空则跳过；若 $mx \le \textit{val}$，答案加一，并把 $mx$ 更新为当前结点的值。随后先压入右孩子，再压入左孩子，二者都带上更新后的 $mx$。初始 $mx$ 小于题目给出的最小结点值 $-10^4$，因此根一定被计入。

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
    def goodNodes(self, root: TreeNode) -> int:
        ans = 0
        stk = [(root, -1000000)]
        while stk:
            node, mx = stk.pop()
            if node is None:
                continue
            if mx <= node.val:
                ans += 1
                mx = node.val
            stk.append((node.right, mx))
            stk.append((node.left, mx))
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
    public int goodNodes(TreeNode root) {
        int ans = 0;
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> limits = new ArrayDeque<>();
        if (root != null) {
            nodes.push(root);
            limits.push(-100000);
        }
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            int mx = limits.pop();
            if (mx <= node.val) {
                ++ans;
                mx = node.val;
            }
            if (node.right != null) {
                nodes.push(node.right);
                limits.push(mx);
            }
            if (node.left != null) {
                nodes.push(node.left);
                limits.push(mx);
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
    int goodNodes(TreeNode* root) {
        int ans = 0;
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(root, -1000000);
        while (!stk.empty()) {
            auto [node, mx] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (mx <= node->val) {
                ++ans;
                mx = node->val;
            }
            stk.emplace_back(node->right, mx);
            stk.emplace_back(node->left, mx);
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
func goodNodes(root *TreeNode) (ans int) {
	stk := []struct {
		node *TreeNode
		mx   int
	}{{root, -10001}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, mx := cur.node, cur.mx
		if node == nil {
			continue
		}
		if mx <= node.Val {
			ans++
			mx = node.Val
		}
		stk = append(stk, struct {
			node *TreeNode
			mx   int
		}{node.Right, mx}, struct {
			node *TreeNode
			mx   int
		}{node.Left, mx})
	}
	return
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

function goodNodes(root: TreeNode | null): number {
    let ans = 0;
    const stk: [TreeNode | null, number][] = [[root, -1e6]];
    while (stk.length) {
        const [node, limit] = stk.pop()!;
        if (!node) {
            continue;
        }
        let mx = limit;
        if (mx <= node.val) {
            ++ans;
            mx = node.val;
        }
        stk.push([node.right, mx]);
        stk.push([node.left, mx]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
