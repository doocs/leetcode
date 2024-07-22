---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/README.md
rating: 1804
source: 第 270 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 字符串
    - 二叉树
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

        return left && right ? node : left ?? right;
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

        return left && right ? node : left ?? right;
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

<!-- problem:end -->
