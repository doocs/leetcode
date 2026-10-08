---
comments: true
difficulty: 中等
rating: 1804
source: 第 270 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 字符串
    - 二叉树
    - 最近公共祖先
---

<!-- problem:start -->

# [2096. 从二叉树一个节点到另一个节点每一步的方向](https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another)

[English Version](/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <strong>二叉树</strong>&nbsp;的根节点&nbsp;<code>root</code>&nbsp;，这棵二叉树总共有&nbsp;<code>n</code>&nbsp;个节点。每个节点的值为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;中的一个整数，且互不相同。给你一个整数&nbsp;<code>startValue</code>&nbsp;，表示起点节点 <code>s</code>&nbsp;的值，和另一个不同的整数&nbsp;<code>destValue</code>&nbsp;，表示终点节点&nbsp;<code>t</code>&nbsp;的值。</p>

<p>请找到从节点&nbsp;<code>s</code>&nbsp;到节点 <code>t</code>&nbsp;的 <strong>最短路径</strong>&nbsp;，并以字符串的形式返回每一步的方向。每一步用 <strong>大写</strong>&nbsp;字母&nbsp;<code>'L'</code>&nbsp;，<code>'R'</code>&nbsp;和&nbsp;<code>'U'</code>&nbsp;分别表示一种方向：</p>

<ul>
	<li><code>'L'</code>&nbsp;表示从一个节点前往它的 <strong>左孩子</strong>&nbsp;节点。</li>
	<li><code>'R'</code>&nbsp;表示从一个节点前往它的 <strong>右孩子</strong>&nbsp;节点。</li>
	<li><code>'U'</code>&nbsp;表示从一个节点前往它的 <strong>父</strong>&nbsp;节点。</li>
</ul>

<p>请你返回从 <code>s</code>&nbsp;到 <code>t</code>&nbsp;<strong>最短路径</strong>&nbsp;每一步的方向。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg1.png" style="width: 214px; height: 163px;"></p>

<pre><b>输入：</b>root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
<b>输出：</b>"UURL"
<b>解释：</b>最短路径为：3 → 1 → 5 → 2 → 6 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg2.png" style="width: 74px; height: 102px;"></p>

<pre><b>输入：</b>root = [2,1], startValue = 2, destValue = 1
<b>输出：</b>"L"
<b>解释：</b>最短路径为：2 → 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目为&nbsp;<code>n</code>&nbsp;。</li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>树中所有节点的值 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>1 &lt;= startValue, destValue &lt;= n</code></li>
	<li><code>startValue != destValue</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：最近公共祖先 + DFS

<!-- thinking:start -->

> **思考**
>
> 从起点到终点的路径必经过 LCA。向上一律走 `U`，再沿 LCA 到终点的左右孩子走 `L`/`R`。 $n \le 10^5$，三次遍历可接受。
>
> 先求 LCA，再两次 DFS 记录从 LCA 到两端的方向串，把去程改成等长的 `U` 后拼接。

<!-- thinking:end -->

我们可以先找到节点 $\textit{startValue}$ 和 $\textit{destValue}$ 的最近公共祖先，记为 $\textit{node}$，然后分别从 $\textit{node}$ 出发，找到 $\textit{startValue}$ 和 $\textit{destValue}$ 的路径。那么从 $\textit{startValue}$ 到 $\textit{node}$ 的路径就是 $\textit{U}$ 的个数，从 $\textit{node}$ 到 $\textit{destValue}$ 的路径就是 $\textit{path}$ 的路径，最后将这两个路径拼接起来即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

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
    def getDirections(
        self, root: Optional[TreeNode], startValue: int, destValue: int
    ) -> str:
        def lca(node: Optional[TreeNode], p: int, q: int):
            if node is None or node.val in (p, q):
                return node
            left = lca(node.left, p, q)
            right = lca(node.right, p, q)
            if left and right:
                return node
            return left or right

        def dfs(node: Optional[TreeNode], x: int, path: List[str]):
            if node is None:
                return False
            if node.val == x:
                return True
            path.append("L")
            if dfs(node.left, x, path):
                return True
            path[-1] = "R"
            if dfs(node.right, x, path):
                return True
            path.pop()
            return False

        node = lca(root, startValue, destValue)

        path_to_start = []
        path_to_dest = []

        dfs(node, startValue, path_to_start)
        dfs(node, destValue, path_to_dest)

        return "U" * len(path_to_start) + "".join(path_to_dest)
```

#### Java

```java
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode node = lca(root, startValue, destValue);
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return "U".repeat(pathToStart.length()) + pathToDest.toString();
    }

    private TreeNode lca(TreeNode node, int p, int q) {
        if (node == null || node.val == p || node.val == q) {
            return node;
        }
        TreeNode left = lca(node.left, p, q);
        TreeNode right = lca(node.right, p, q);
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }

    private boolean dfs(TreeNode node, int x, StringBuilder path) {
        if (node == null) {
            return false;
        }
        if (node.val == x) {
            return true;
        }
        path.append('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path.setCharAt(path.length() - 1, 'R');
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        return false;
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
    string getDirections(TreeNode* root, int startValue, int destValue) {
        TreeNode* node = lca(root, startValue, destValue);
        string pathToStart, pathToDest;
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return string(pathToStart.size(), 'U') + pathToDest;
    }

private:
    TreeNode* lca(TreeNode* node, int p, int q) {
        if (node == nullptr || node->val == p || node->val == q) {
            return node;
        }
        TreeNode* left = lca(node->left, p, q);
        TreeNode* right = lca(node->right, p, q);
        if (left != nullptr && right != nullptr) {
            return node;
        }
        return left != nullptr ? left : right;
    }

    bool dfs(TreeNode* node, int x, string& path) {
        if (node == nullptr) {
            return false;
        }
        if (node->val == x) {
            return true;
        }
        path.push_back('L');
        if (dfs(node->left, x, path)) {
            return true;
        }
        path.back() = 'R';
        if (dfs(node->right, x, path)) {
            return true;
        }
        path.pop_back();
        return false;
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
func getDirections(root *TreeNode, startValue int, destValue int) string {
	var lca func(node *TreeNode, p, q int) *TreeNode
	lca = func(node *TreeNode, p, q int) *TreeNode {
		if node == nil || node.Val == p || node.Val == q {
			return node
		}
		left := lca(node.Left, p, q)
		right := lca(node.Right, p, q)
		if left != nil && right != nil {
			return node
		}
		if left != nil {
			return left
		}
		return right
	}
	var dfs func(node *TreeNode, x int, path *[]byte) bool
	dfs = func(node *TreeNode, x int, path *[]byte) bool {
		if node == nil {
			return false
		}
		if node.Val == x {
			return true
		}
		*path = append(*path, 'L')
		if dfs(node.Left, x, path) {
			return true
		}
		(*path)[len(*path)-1] = 'R'
		if dfs(node.Right, x, path) {
			return true
		}
		*path = (*path)[:len(*path)-1]
		return false
	}

	node := lca(root, startValue, destValue)
	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(node, startValue, &pathToStart)
	dfs(node, destValue, &pathToDest)
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart))) + string(pathToDest)
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

function getDirections(root: TreeNode | null, startValue: number, destValue: number): string {
    const lca = (node: TreeNode | null, p: number, q: number): TreeNode | null => {
        if (node === null || [p, q].includes(node.val)) {
            return node;
        }
        const left = lca(node.left, p, q);
        const right = lca(node.right, p, q);

        return left && right ? node : (left ?? right);
    };

    const dfs = (node: TreeNode | null, x: number, path: string[]): boolean => {
        if (node === null) {
            return false;
        }
        if (node.val === x) {
            return true;
        }
        path.push('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.pop();
        return false;
    };

    const node = lca(root, startValue, destValue);
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
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
 * @param {number} startValue
 * @param {number} destValue
 * @return {string}
 */
var getDirections = function (root, startValue, destValue) {
    const lca = (node, p, q) => {
        if (node === null || [p, q].includes(node.val)) {
            return node;
        }
        const left = lca(node.left, p, q);
        const right = lca(node.right, p, q);

        return left && right ? node : (left ?? right);
    };

    const dfs = (node, x, path) => {
        if (node === null) {
            return false;
        }
        if (node.val === x) {
            return true;
        }
        path.push('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.pop();
        return false;
    };

    const node = lca(root, startValue, destValue);
    const pathToStart = [];
    const pathToDest = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：最近公共祖先 + DFS（优化）

<!-- thinking:start -->

> **思考**
>
> 方法一先找 LCA 再走两遍。从根到两端的路径共享一段前缀，去掉该前缀后剩余即「回到 LCA 再下去」。少一次 LCA 专用搜索。
>
> 两次 DFS 得到方向串，对齐公共前缀长度 $i$，答案为 $(|start|-i)$ 个 `U` 加终点后缀。

<!-- thinking:end -->

我们可以从 $\textit{root}$ 出发，找到 $\textit{startValue}$ 和 $\textit{destValue}$ 的路径，记为 $\textit{pathToStart}$ 和 $\textit{pathToDest}$，然后去除 $\textit{pathToStart}$ 和 $\textit{pathToDest}$ 的最长公共前缀，此时 $\textit{pathToStart}$ 的路径长度就是答案中 $\textit{U}$ 的个数，而 $\textit{pathToDest}$ 的路径就是答案中的路径，我们只需要将这两个路径拼接起来即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

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
    def getDirections(
        self, root: Optional[TreeNode], startValue: int, destValue: int
    ) -> str:
        def dfs(node: Optional[TreeNode], x: int, path: List[str]):
            if node is None:
                return False
            if node.val == x:
                return True
            path.append("L")
            if dfs(node.left, x, path):
                return True
            path[-1] = "R"
            if dfs(node.right, x, path):
                return True
            path.pop()
            return False

        path_to_start = []
        path_to_dest = []

        dfs(root, startValue, path_to_start)
        dfs(root, destValue, path_to_dest)
        i = 0
        while (
            i < len(path_to_start)
            and i < len(path_to_dest)
            and path_to_start[i] == path_to_dest[i]
        ):
            i += 1
        return "U" * (len(path_to_start) - i) + "".join(path_to_dest[i:])
```

#### Java

```java
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.length() && i < pathToDest.length()
            && pathToStart.charAt(i) == pathToDest.charAt(i)) {
            ++i;
        }
        return "U".repeat(pathToStart.length() - i) + pathToDest.substring(i);
    }

    private boolean dfs(TreeNode node, int x, StringBuilder path) {
        if (node == null) {
            return false;
        }
        if (node.val == x) {
            return true;
        }
        path.append('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path.setCharAt(path.length() - 1, 'R');
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        return false;
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
    string getDirections(TreeNode* root, int startValue, int destValue) {
        string pathToStart, pathToDest;
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.size() && i < pathToDest.size() && pathToStart[i] == pathToDest[i]) {
            i++;
        }
        return string(pathToStart.size() - i, 'U') + pathToDest.substr(i);
    }

private:
    bool dfs(TreeNode* node, int x, string& path) {
        if (node == nullptr) {
            return false;
        }
        if (node->val == x) {
            return true;
        }
        path.push_back('L');
        if (dfs(node->left, x, path)) {
            return true;
        }
        path.back() = 'R';
        if (dfs(node->right, x, path)) {
            return true;
        }
        path.pop_back();
        return false;
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
func getDirections(root *TreeNode, startValue int, destValue int) string {
	var dfs func(node *TreeNode, x int, path *[]byte) bool
	dfs = func(node *TreeNode, x int, path *[]byte) bool {
		if node == nil {
			return false
		}
		if node.Val == x {
			return true
		}
		*path = append(*path, 'L')
		if dfs(node.Left, x, path) {
			return true
		}
		(*path)[len(*path)-1] = 'R'
		if dfs(node.Right, x, path) {
			return true
		}
		*path = (*path)[:len(*path)-1]
		return false
	}

	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(root, startValue, &pathToStart)
	dfs(root, destValue, &pathToDest)
	i := 0
	for i < len(pathToStart) && i < len(pathToDest) && pathToStart[i] == pathToDest[i] {
		i++
	}
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart)-i)) + string(pathToDest[i:])
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

function getDirections(root: TreeNode | null, startValue: number, destValue: number): string {
    const dfs = (node: TreeNode | null, x: number, path: string[]): boolean => {
        if (node === null) {
            return false;
        }
        if (node.val === x) {
            return true;
        }
        path.push('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.pop();
        return false;
    };
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(root, startValue, pathToStart);
    dfs(root, destValue, pathToDest);
    let i = 0;
    while (pathToStart[i] === pathToDest[i]) {
        ++i;
    }
    return 'U'.repeat(pathToStart.length - i) + pathToDest.slice(i).join('');
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
 * @param {number} startValue
 * @param {number} destValue
 * @return {string}
 */
var getDirections = function (root, startValue, destValue) {
    const dfs = (node, x, path) => {
        if (node === null) {
            return false;
        }
        if (node.val === x) {
            return true;
        }
        path.push('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.pop();
        return false;
    };
    const pathToStart = [];
    const pathToDest = [];
    dfs(root, startValue, pathToStart);
    dfs(root, destValue, pathToDest);
    let i = 0;
    while (pathToStart[i] === pathToDest[i]) {
        ++i;
    }
    return 'U'.repeat(pathToStart.length - i) + pathToDest.slice(i).join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：显式栈求最近公共祖先

<!-- thinking:start -->

> **思考**
>
> 从起点到终点的最短路径一定经过二者的最近公共祖先。祖先之上的每一步都是 `U`，从祖先到终点再按左右孩子记 `L` 或 `R`。$n$ 可以到 $10^5$，三次线性遍历都在时限内。
>
> 求祖先和记录方向都先走进左孩子。一条左链的长度可以到 $n$，递归会在方向串写完之前溢出。
>
> 最近公共祖先要等左右子树都返回后才能决定：两边都找到目标时当前结点就是祖先，否则把非空的一侧传上去。显式栈用状态把“先展开左右孩子”和“孩子都已返回”分开，弹出完成标记时再做这个选择。方向搜索同样先压入回退标记，再压左孩子；左子树失败后把路径末尾改成 `R` 并搜索右孩子，找到目标就停。
>
> 起点到祖先的方向串长度就是 `U` 的个数，拼上祖先到终点的方向串。

<!-- thinking:end -->

我们用显式栈找到节点 $\textit{startValue}$ 和 $\textit{destValue}$ 的最近公共祖先，记为 $\textit{node}$。栈里的状态 $0$ 展开左右孩子，状态 $1$ 在两侧都返回后决定祖先：两侧都非空则当前结点是祖先，否则取非空的一侧。然后再用同一个栈从 $\textit{node}$ 出发，先向左记录 `L`，左子树没有目标时把末尾改成 `R` 继续向右，找到目标即停止。从 $\textit{startValue}$ 回到 $\textit{node}$ 的步数就是 `U` 的个数，接上从 $\textit{node}$ 到 $\textit{destValue}$ 的方向串。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

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
    def getDirections(
        self, root: Optional[TreeNode], startValue: int, destValue: int
    ) -> str:
        def lca(root: Optional[TreeNode], p: int, q: int):
            ret = {}
            stk = [(root, 0)]
            while stk:
                node, state = stk.pop()
                if state == 0:
                    if node is None:
                        continue
                    if node.val in (p, q):
                        ret[id(node)] = node
                        continue
                    stk.append((node, 1))
                    stk.append((node.right, 0))
                    stk.append((node.left, 0))
                else:
                    left = ret.get(id(node.left)) if node.left is not None else None
                    right = ret.get(id(node.right)) if node.right is not None else None
                    if left and right:
                        ret[id(node)] = node
                    else:
                        ret[id(node)] = left or right
            return ret.get(id(root))

        def dfs(start: Optional[TreeNode], x: int, path: List[str]) -> bool:
            stk = [(start, 0)]
            while stk:
                node, state = stk.pop()
                if state == 0:
                    if node is None:
                        continue
                    if node.val == x:
                        return True
                    path.append('L')
                    stk.append((node, 1))
                    stk.append((node.left, 0))
                elif state == 1:
                    path[-1] = 'R'
                    stk.append((node, 2))
                    stk.append((node.right, 0))
                else:
                    path.pop()
            return False

        node = lca(root, startValue, destValue)
        path_to_start: List[str] = []
        path_to_dest: List[str] = []
        dfs(node, startValue, path_to_start)
        dfs(node, destValue, path_to_dest)
        return 'U' * len(path_to_start) + ''.join(path_to_dest)
```

#### Java

```java
class Solution {
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode node = lca(root, startValue, destValue);
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return "U".repeat(pathToStart.length()) + pathToDest.toString();
    }

    private TreeNode lca(TreeNode root, int p, int q) {
        Map<TreeNode, TreeNode> ret = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                if (node == null) {
                    continue;
                }
                if (node.val == p || node.val == q) {
                    ret.put(node, node);
                    continue;
                }
                stk.push(new Frame(node, 1));
                stk.push(new Frame(node.right, 0));
                stk.push(new Frame(node.left, 0));
            } else {
                TreeNode left = node.left == null ? null : ret.get(node.left);
                TreeNode right = node.right == null ? null : ret.get(node.right);
                if (left != null && right != null) {
                    ret.put(node, node);
                } else {
                    ret.put(node, left != null ? left : right);
                }
            }
        }
        return ret.get(root);
    }

    private boolean dfs(TreeNode start, int x, StringBuilder path) {
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(start, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                if (node == null) {
                    continue;
                }
                if (node.val == x) {
                    return true;
                }
                path.append('L');
                stk.push(new Frame(node, 1));
                stk.push(new Frame(node.left, 0));
            } else if (cur.state == 1) {
                path.setCharAt(path.length() - 1, 'R');
                stk.push(new Frame(node, 2));
                stk.push(new Frame(node.right, 0));
            } else {
                path.deleteCharAt(path.length() - 1);
            }
        }
        return false;
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
    string getDirections(TreeNode* root, int startValue, int destValue) {
        TreeNode* node = lca(root, startValue, destValue);
        string pathToStart, pathToDest;
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return string(pathToStart.size(), 'U') + pathToDest;
    }

private:
    TreeNode* lca(TreeNode* root, int p, int q) {
        unordered_map<TreeNode*, TreeNode*> ret;
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(root, 0);
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (node == nullptr) {
                    continue;
                }
                if (node->val == p || node->val == q) {
                    ret[node] = node;
                    continue;
                }
                stk.emplace_back(node, 1);
                stk.emplace_back(node->right, 0);
                stk.emplace_back(node->left, 0);
            } else {
                TreeNode* left = node->left && ret.count(node->left) ? ret[node->left] : nullptr;
                TreeNode* right = node->right && ret.count(node->right) ? ret[node->right] : nullptr;
                if (left != nullptr && right != nullptr) {
                    ret[node] = node;
                } else {
                    ret[node] = left != nullptr ? left : right;
                }
            }
        }
        return ret.count(root) ? ret[root] : nullptr;
    }

    bool dfs(TreeNode* start, int x, string& path) {
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(start, 0);
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (node == nullptr) {
                    continue;
                }
                if (node->val == x) {
                    return true;
                }
                path.push_back('L');
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
            } else if (state == 1) {
                path.back() = 'R';
                stk.emplace_back(node, 2);
                stk.emplace_back(node->right, 0);
            } else {
                path.pop_back();
            }
        }
        return false;
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
func getDirections(root *TreeNode, startValue int, destValue int) string {
	lca := func(root *TreeNode, p, q int) *TreeNode {
		ret := map[*TreeNode]*TreeNode{}
		stk := [][2]interface{}{{root, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			node, _ := cur[0].(*TreeNode)
			state := cur[1].(int)
			if state == 0 {
				if node == nil {
					continue
				}
				if node.Val == p || node.Val == q {
					ret[node] = node
					continue
				}
				stk = append(stk, [2]interface{}{node, 1}, [2]interface{}{node.Right, 0}, [2]interface{}{node.Left, 0})
			} else {
				var left, right *TreeNode
				if node.Left != nil {
					left = ret[node.Left]
				}
				if node.Right != nil {
					right = ret[node.Right]
				}
				if left != nil && right != nil {
					ret[node] = node
				} else if left != nil {
					ret[node] = left
				} else {
					ret[node] = right
				}
			}
		}
		return ret[root]
	}
	dfs := func(start *TreeNode, x int, path *[]byte) bool {
		stk := [][2]interface{}{{start, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			node, _ := cur[0].(*TreeNode)
			state := cur[1].(int)
			if state == 0 {
				if node == nil {
					continue
				}
				if node.Val == x {
					return true
				}
				*path = append(*path, 'L')
				stk = append(stk, [2]interface{}{node, 1}, [2]interface{}{node.Left, 0})
			} else if state == 1 {
				(*path)[len(*path)-1] = 'R'
				stk = append(stk, [2]interface{}{node, 2}, [2]interface{}{node.Right, 0})
			} else {
				*path = (*path)[:len(*path)-1]
			}
		}
		return false
	}

	node := lca(root, startValue, destValue)
	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(node, startValue, &pathToStart)
	dfs(node, destValue, &pathToDest)
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart))) + string(pathToDest)
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

function getDirections(root: TreeNode | null, startValue: number, destValue: number): string {
    const lca = (root: TreeNode | null, p: number, q: number): TreeNode | null => {
        const ret = new Map<TreeNode, TreeNode | null>();
        const stk: [TreeNode | null, number][] = [[root, 0]];
        while (stk.length) {
            const [node, state] = stk.pop()!;
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === p || node.val === q) {
                    ret.set(node, node);
                    continue;
                }
                stk.push([node, 1]);
                stk.push([node.right, 0]);
                stk.push([node.left, 0]);
            } else {
                const left = node!.left ? ret.get(node!.left) : null;
                const right = node!.right ? ret.get(node!.right) : null;
                ret.set(node!, left && right ? node : (left ?? right));
            }
        }
        return ret.get(root!) ?? null;
    };

    const dfs = (start: TreeNode | null, x: number, path: string[]): boolean => {
        const stk: [TreeNode | null, number][] = [[start, 0]];
        while (stk.length) {
            const [node, state] = stk.pop()!;
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === x) {
                    return true;
                }
                path.push('L');
                stk.push([node, 1]);
                stk.push([node.left, 0]);
            } else if (state === 1) {
                path[path.length - 1] = 'R';
                stk.push([node, 2]);
                stk.push([node!.right, 0]);
            } else {
                path.pop();
            }
        }
        return false;
    };

    const node = lca(root, startValue, destValue);
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
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
 * @param {number} startValue
 * @param {number} destValue
 * @return {string}
 */
var getDirections = function (root, startValue, destValue) {
    const lca = (root, p, q) => {
        const ret = new Map();
        const stk = [[root, 0]];
        while (stk.length) {
            const [node, state] = stk.pop();
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === p || node.val === q) {
                    ret.set(node, node);
                    continue;
                }
                stk.push([node, 1]);
                stk.push([node.right, 0]);
                stk.push([node.left, 0]);
            } else {
                const left = node.left ? ret.get(node.left) : null;
                const right = node.right ? ret.get(node.right) : null;
                ret.set(node, left && right ? node : (left ?? right));
            }
        }
        return ret.get(root) ?? null;
    };

    const dfs = (start, x, path) => {
        const stk = [[start, 0]];
        while (stk.length) {
            const [node, state] = stk.pop();
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === x) {
                    return true;
                }
                path.push('L');
                stk.push([node, 1]);
                stk.push([node.left, 0]);
            } else if (state === 1) {
                path[path.length - 1] = 'R';
                stk.push([node, 2]);
                stk.push([node.right, 0]);
            } else {
                path.pop();
            }
        }
        return false;
    };

    const node = lca(root, startValue, destValue);
    const pathToStart = [];
    const pathToDest = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法四：显式栈对齐根路径

<!-- thinking:start -->

> **思考**
>
> 方法一仍然单独求一次最近公共祖先，再从祖先出发走两遍。从根到两端的路径共享一段前缀，去掉这段前缀就等于先回到祖先再向下，那一次专门的祖先搜索可以省掉。
>
> 两次方向搜索仍可能沿着一条长度为 $n$ 的链往下走，所以同样放进显式栈：先压回退标记，再压左孩子，左子树失败后把末尾改成 `R`。
>
> 对齐公共前缀长度 $i$ 之后，答案是 $(|\textit{start}|-i)$ 个 `U` 加上终点路径的后缀。

<!-- thinking:end -->

我们用显式栈从 $\textit{root}$ 出发，分别找到 $\textit{startValue}$ 和 $\textit{destValue}$ 的方向串，记为 $\textit{pathToStart}$ 和 $\textit{pathToDest}$。栈在向左失败后把路径末尾改成 `R` 再向右，因此方向串与原先的深度优先搜索一致。去掉二者的最长公共前缀后，$\textit{pathToStart}$ 剩下的长度就是答案中 `U` 的个数，$\textit{pathToDest}$ 剩下的部分就是向下的方向，把两段拼接起来即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

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
    def getDirections(
        self, root: Optional[TreeNode], startValue: int, destValue: int
    ) -> str:
        def dfs(start: Optional[TreeNode], x: int, path: List[str]) -> bool:
            stk = [(start, 0)]
            while stk:
                node, state = stk.pop()
                if state == 0:
                    if node is None:
                        continue
                    if node.val == x:
                        return True
                    path.append('L')
                    stk.append((node, 1))
                    stk.append((node.left, 0))
                elif state == 1:
                    path[-1] = 'R'
                    stk.append((node, 2))
                    stk.append((node.right, 0))
                else:
                    path.pop()
            return False

        path_to_start: List[str] = []
        path_to_dest: List[str] = []
        dfs(root, startValue, path_to_start)
        dfs(root, destValue, path_to_dest)
        i = 0
        while (
            i < len(path_to_start)
            and i < len(path_to_dest)
            and path_to_start[i] == path_to_dest[i]
        ):
            i += 1
        return 'U' * (len(path_to_start) - i) + ''.join(path_to_dest[i:])
```

#### Java

```java
class Solution {
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.length() && i < pathToDest.length()
            && pathToStart.charAt(i) == pathToDest.charAt(i)) {
            ++i;
        }
        return "U".repeat(pathToStart.length() - i) + pathToDest.substring(i);
    }

    private boolean dfs(TreeNode start, int x, StringBuilder path) {
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(start, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                if (node == null) {
                    continue;
                }
                if (node.val == x) {
                    return true;
                }
                path.append('L');
                stk.push(new Frame(node, 1));
                stk.push(new Frame(node.left, 0));
            } else if (cur.state == 1) {
                path.setCharAt(path.length() - 1, 'R');
                stk.push(new Frame(node, 2));
                stk.push(new Frame(node.right, 0));
            } else {
                path.deleteCharAt(path.length() - 1);
            }
        }
        return false;
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
    string getDirections(TreeNode* root, int startValue, int destValue) {
        string pathToStart, pathToDest;
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.size() && i < pathToDest.size() && pathToStart[i] == pathToDest[i]) {
            i++;
        }
        return string(pathToStart.size() - i, 'U') + pathToDest.substr(i);
    }

private:
    bool dfs(TreeNode* start, int x, string& path) {
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(start, 0);
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (node == nullptr) {
                    continue;
                }
                if (node->val == x) {
                    return true;
                }
                path.push_back('L');
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
            } else if (state == 1) {
                path.back() = 'R';
                stk.emplace_back(node, 2);
                stk.emplace_back(node->right, 0);
            } else {
                path.pop_back();
            }
        }
        return false;
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
func getDirections(root *TreeNode, startValue int, destValue int) string {
	dfs := func(start *TreeNode, x int, path *[]byte) bool {
		stk := [][2]interface{}{{start, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			node, _ := cur[0].(*TreeNode)
			state := cur[1].(int)
			if state == 0 {
				if node == nil {
					continue
				}
				if node.Val == x {
					return true
				}
				*path = append(*path, 'L')
				stk = append(stk, [2]interface{}{node, 1}, [2]interface{}{node.Left, 0})
			} else if state == 1 {
				(*path)[len(*path)-1] = 'R'
				stk = append(stk, [2]interface{}{node, 2}, [2]interface{}{node.Right, 0})
			} else {
				*path = (*path)[:len(*path)-1]
			}
		}
		return false
	}

	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(root, startValue, &pathToStart)
	dfs(root, destValue, &pathToDest)
	i := 0
	for i < len(pathToStart) && i < len(pathToDest) && pathToStart[i] == pathToDest[i] {
		i++
	}
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart)-i)) + string(pathToDest[i:])
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

function getDirections(root: TreeNode | null, startValue: number, destValue: number): string {
    const dfs = (start: TreeNode | null, x: number, path: string[]): boolean => {
        const stk: [TreeNode | null, number][] = [[start, 0]];
        while (stk.length) {
            const [node, state] = stk.pop()!;
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === x) {
                    return true;
                }
                path.push('L');
                stk.push([node, 1]);
                stk.push([node.left, 0]);
            } else if (state === 1) {
                path[path.length - 1] = 'R';
                stk.push([node, 2]);
                stk.push([node!.right, 0]);
            } else {
                path.pop();
            }
        }
        return false;
    };
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(root, startValue, pathToStart);
    dfs(root, destValue, pathToDest);
    let i = 0;
    while (i < pathToStart.length && i < pathToDest.length && pathToStart[i] === pathToDest[i]) {
        ++i;
    }
    return 'U'.repeat(pathToStart.length - i) + pathToDest.slice(i).join('');
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
 * @param {number} startValue
 * @param {number} destValue
 * @return {string}
 */
var getDirections = function (root, startValue, destValue) {
    const dfs = (start, x, path) => {
        const stk = [[start, 0]];
        while (stk.length) {
            const [node, state] = stk.pop();
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === x) {
                    return true;
                }
                path.push('L');
                stk.push([node, 1]);
                stk.push([node.left, 0]);
            } else if (state === 1) {
                path[path.length - 1] = 'R';
                stk.push([node, 2]);
                stk.push([node.right, 0]);
            } else {
                path.pop();
            }
        }
        return false;
    };
    const pathToStart = [];
    const pathToDest = [];
    dfs(root, startValue, pathToStart);
    dfs(root, destValue, pathToDest);
    let i = 0;
    while (i < pathToStart.length && i < pathToDest.length && pathToStart[i] === pathToDest[i]) {
        ++i;
    }
    return 'U'.repeat(pathToStart.length - i) + pathToDest.slice(i).join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
