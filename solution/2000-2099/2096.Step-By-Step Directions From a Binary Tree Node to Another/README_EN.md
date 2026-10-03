---
comments: true
difficulty: Medium
rating: 1804
source: Weekly Contest 270 Q3
tags:
    - Tree
    - Depth-First Search
    - String
    - Binary Tree
    - Lowest Common Ancestor
    - Binary Lifting
---

<!-- problem:start -->

# [2096. Step-By-Step Directions From a Binary Tree Node to Another](https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another)

[中文文档](/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a <strong>binary tree</strong> with <code>n</code> nodes. Each node is uniquely assigned a value from <code>1</code> to <code>n</code>. You are also given an integer <code>startValue</code> representing the value of the start node <code>s</code>, and a different integer <code>destValue</code> representing the value of the destination node <code>t</code>.</p>

<p>Find the <strong>shortest path</strong> starting from node <code>s</code> and ending at node <code>t</code>. Generate step-by-step directions of such path as a string consisting of only the <strong>uppercase</strong> letters <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;U&#39;</code>. Each letter indicates a specific direction:</p>

<ul>
	<li><code>&#39;L&#39;</code> means to go from a node to its <strong>left child</strong> node.</li>
	<li><code>&#39;R&#39;</code> means to go from a node to its <strong>right child</strong> node.</li>
	<li><code>&#39;U&#39;</code> means to go from a node to its <strong>parent</strong> node.</li>
</ul>

<p>Return <em>the step-by-step directions of the <strong>shortest path</strong> from node </em><code>s</code><em> to node</em> <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg1.png" style="width: 214px; height: 163px;" />
<pre>
<strong>Input:</strong> root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
<strong>Output:</strong> &quot;UURL&quot;
<strong>Explanation:</strong> The shortest path is: 3 &rarr; 1 &rarr; 5 &rarr; 2 &rarr; 6.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg2.png" style="width: 74px; height: 102px;" />
<pre>
<strong>Input:</strong> root = [2,1], startValue = 2, destValue = 1
<strong>Output:</strong> &quot;L&quot;
<strong>Explanation:</strong> The shortest path is: 2 &rarr; 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li><code>1 &lt;= startValue, destValue &lt;= n</code></li>
	<li><code>startValue != destValue</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Lowest Common Ancestor + DFS

<!-- thinking:start -->

> **Thinking**
>
> The unique path goes through the LCA. Upward edges become `U`; the descent uses `L`/`R`. Three tree walks are fine for $n \le 10^5$.
>
> Find the LCA, DFS both directions from it, replace the start path by `U`s, and concatenate.

<!-- thinking:end -->

We can first find the lowest common ancestor of nodes $\textit{startValue}$ and $\textit{destValue}$, denoted as $\textit{node}$. Then, starting from $\textit{node}$, we find the paths to $\textit{startValue}$ and $\textit{destValue}$ respectively. The path from $\textit{startValue}$ to $\textit{node}$ will consist of a number of $\textit{U}$s, and the path from $\textit{node}$ to $\textit{destValue}$ will be the $\textit{path}$. Finally, we concatenate these two paths.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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

### Solution 2: Lowest Common Ancestor + DFS (Optimized)

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 finds the LCA then walks twice more. Paths from the root share a prefix; stripping it is exactly “up to the LCA then down.” The extra LCA search disappears.
>
> Two DFS strings, skip the common prefix of length $i$, emit $(|start|-i)$ `U`s plus the destination suffix.

<!-- thinking:end -->

We can start from $\textit{root}$, find the paths to $\textit{startValue}$ and $\textit{destValue}$, denoted as $\textit{pathToStart}$ and $\textit{pathToDest}$, respectively. Then, remove the longest common prefix of $\textit{pathToStart}$ and $\textit{pathToDest}$. At this point, the length of $\textit{pathToStart}$ is the number of $\textit{U}$s in the answer, and the path of $\textit{pathToDest}$ is the path in the answer. We just need to concatenate these two paths.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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

### Solution 3: Explicit-Stack Lowest Common Ancestor

<!-- thinking:start -->

> **Thinking**
>
> The shortest path goes through the lowest common ancestor. Every step above that ancestor is `U`, and the descent from the ancestor to the destination is `L` or `R`. Three linear walks fit $n \le 10^5$.
>
> Both the ancestor search and the direction search enter the left child first. A left chain can be $n$ nodes long, so recursion overflows before the direction string is finished.
>
> The ancestor is known only after both subtrees return: the current node is the ancestor when both sides found a target, otherwise the non-empty side is passed upward. An explicit stack separates “expand both children” from “both children have returned,” and the direction search pushes a backtrack marker before the left child. A failed left subtree rewrites the last step to `R` and searches the right child, stopping when the target is found.
>
> The length of the start path is the number of `U`s, concatenated with the path from the ancestor to the destination.

<!-- thinking:end -->

An explicit stack finds the lowest common ancestor of $\textit{startValue}$ and $\textit{destValue}$, denoted as $\textit{node}$. State $0$ expands the two children, and state $1$ decides the ancestor after both sides return: the current node when both sides are non-empty, otherwise the non-empty side. The same stack then walks from $\textit{node}$, recording `L` to the left and rewriting that step to `R` when the left subtree misses the target. The number of steps from $\textit{startValue}$ back to $\textit{node}$ is the number of `U`s, followed by the direction string from $\textit{node}$ to $\textit{destValue}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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

### Solution 4: Explicit-Stack Root Paths

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 still runs a separate ancestor search and then walks twice from that ancestor. Paths from the root share a prefix, and stripping it is the same as climbing back to the ancestor and then descending, so the dedicated ancestor search can be dropped.
>
> The two direction searches can still follow a chain of length $n$, so they use the same explicit stack: push a backtrack marker, then the left child, and rewrite the last step to `R` when the left subtree fails.
>
> After the common prefix of length $i$ is removed, the answer is $(|\textit{start}|-i)$ `U`s plus the destination suffix.

<!-- thinking:end -->

An explicit stack walks from $\textit{root}$ to $\textit{startValue}$ and to $\textit{destValue}$, producing $\textit{pathToStart}$ and $\textit{pathToDest}$. A failed left subtree rewrites the last step to `R` before the right child is searched, so the strings match the previous depth-first order. After the longest common prefix is removed, the remaining length of $\textit{pathToStart}$ is the number of `U`s, and the remaining part of $\textit{pathToDest}$ is the downward path. Concatenate the two pieces.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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
