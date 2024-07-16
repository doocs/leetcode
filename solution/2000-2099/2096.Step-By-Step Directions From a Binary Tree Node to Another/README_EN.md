---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/README_EN.md
rating: 1804
source: Weekly Contest 270 Q3
tags:
    - Tree
    - Depth-First Search
    - String
    - Binary Tree
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
        if (node === null || node.val === p || node.val === q) {
            return node;
        }
        const left = lca(node.left, p, q);
        const right = lca(node.right, p, q);
        if (left !== null && right !== null) {
            return node;
        }
        return left !== null ? left : right;
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

#### Solution 2: DFS

<!-- tabs:start -->

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
    static byte[] path = new byte[200_001];
    int strtLevel = -1;
    int destLevel = -1;
    int comnLevel = -1;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        findPaths(root, startValue, destValue, 100_000);
        int answerIdx = comnLevel;
        for (int i = strtLevel; i > comnLevel; i--) {
            path[--answerIdx] = 'U';
        }
        return new String(path, answerIdx, destLevel - answerIdx);
    }

    private int findPaths(TreeNode node, int strtVal, int destVal, int level) {
        if (node == null) {
            return 0;
        }
        int result = 0;
        if (node.val == strtVal) {
            strtLevel = level;
            result = 1;
        } else if (node.val == destVal) {
            destLevel = level;
            result = 1;
        }
        int leftFound = 0;
        int rightFound = 0;
        if (comnLevel < 0) {
            if (destLevel < 0) {
                path[level] = 'L';
            }
            leftFound = findPaths(node.left, strtVal, destVal, level + 1);
            rightFound = 0;
            if (comnLevel < 0) {
                if (destLevel < 0) {
                    path[level] = 'R';
                }
                rightFound = findPaths(node.right, strtVal, destVal, level + 1);
            }
        }
        if (comnLevel < 0 && leftFound + rightFound + result == 2) {
            comnLevel = level;
        }
        return result | leftFound | rightFound;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
