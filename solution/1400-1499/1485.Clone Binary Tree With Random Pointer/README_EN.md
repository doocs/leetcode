---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Binary Tree
---

<!-- problem:start -->

# [1485. Clone Binary Tree With Random Pointer 🔒](https://leetcode.com/problems/clone-binary-tree-with-random-pointer)

[中文文档](/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/README.md)

## Description

<!-- description:start -->

<p>A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.</p>

<p>Return a <a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>deep copy</strong></a> of the tree.</p>

<p>The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of <code>[val, random_index]</code> where:</p>

<ul>
	<li><code>val</code>: an integer representing <code>Node.val</code></li>
	<li><code>random_index</code>: the index of the node (in the input) where the random pointer points to, or <code>null</code> if it does not point to any node.</li>
</ul>

<p>You will be given the tree in class <code>Node</code> and you should return the cloned tree in class <code>NodeCopy</code>. <code>NodeCopy</code> class is just a clone of <code>Node</code> class with the same attributes and constructors.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/clone_1.png" style="width: 500px; height: 473px;" />
<pre>
<strong>Input:</strong> root = [[1,null],null,[4,3],[7,0]]
<strong>Output:</strong> [[1,null],null,[4,3],[7,0]]
<strong>Explanation:</strong> The original binary tree is [1,null,4,7].
The random pointer of node one is null, so it is represented as [1, null].
The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3 is the index of node 7 in the array representing the tree.
The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0 is the index of node 1 in the array representing the tree.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/clone_2.png" style="width: 500px; height: 540px;" />
<pre>
<strong>Input:</strong> root = [[1,4],null,[1,0],null,[1,5],[1,5]]
<strong>Output:</strong> [[1,4],null,[1,0],null,[1,5],[1,5]]
<strong>Explanation:</strong> The random pointer of a node can be the node itself.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/clone_3.png" style="width: 500px; height: 426px;" />
<pre>
<strong>Input:</strong> root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
<strong>Output:</strong> [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the <code>tree</code> is in the range <code>[0, 1000].</code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + DFS

<!-- thinking:start -->

> **Thinking**
>
> Besides left and right there is a $\textit{random}$ pointer that may form cycles, so a naive recursion can loop or copy a node twice.
>
> Map each original node to its copy: create the copy first, then recurse on left, right, and $\textit{random}$. A hit in the map returns the existing copy.

<!-- thinking:end -->

We use a hash table $\textit{seen}$ to record the correspondence between each node in the original tree and its copy, then perform a depth-first search.

Define $\text{dfs}(root)$ to return the copy of $root$:

- If $root$ is null, return null;
- If $root$ is already in $\textit{seen}$, return $\textit{seen}[root]$;
- Otherwise create a copy node $\textit{copy}$, set $\textit{seen}[root] = \textit{copy}$, then recurse on the left child, right child, and $\textit{random}$ pointer;
- Finally return $\textit{copy}$.

The main function returns $\text{dfs}(root)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
# Definition for Node.
# class Node:
#     def __init__(self, val=0, left=None, right=None, random=None):
#         self.val = val
#         self.left = left
#         self.right = right
#         self.random = random


class Solution:
    def copyRandomBinaryTree(self, root: "Optional[Node]") -> "Optional[NodeCopy]":
        def dfs(root: Optional[Node]) -> Optional[NodeCopy]:
            if root is None:
                return None
            if root in seen:
                return seen[root]
            copy = NodeCopy(root.val)
            seen[root] = copy
            copy.left = dfs(root.left)
            copy.right = dfs(root.right)
            copy.random = dfs(root.random)
            return copy

        seen = {}
        return dfs(root)
```

#### Java

```java
/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    private Map<Node, NodeCopy> seen;

    public NodeCopy copyRandomBinaryTree(Node root) {
        seen = new HashMap<>();
        return dfs(root);
    }

    private NodeCopy dfs(Node root) {
        if (root == null) {
            return null;
        }
        if (seen.containsKey(root)) {
            return seen.get(root);
        }
        NodeCopy copy = new NodeCopy(root.val);
        seen.put(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    }
}
```

#### C++

```cpp
/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *random;
 *     Node() : val(0), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x) : val(x), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x, Node *left, Node *right, Node *random) : val(x), left(left), right(right), random(random) {}
 * };
 */

class Solution {
public:
    NodeCopy* copyRandomBinaryTree(Node* root) {
        unordered_map<Node*, NodeCopy*> seen;
        auto dfs = [&](this auto&& dfs, Node* root) -> NodeCopy* {
            if (!root) {
                return nullptr;
            }
            if (seen.contains(root)) {
                return seen[root];
            }
            NodeCopy* copy = new NodeCopy(root->val);
            seen[root] = copy;
            copy->left = dfs(root->left);
            copy->right = dfs(root->right);
            copy->random = dfs(root->random);
            return copy;
        };
        return dfs(root);
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Random *Node
 * }
 */

func copyRandomBinaryTree(root *Node) *NodeCopy {
	seen := make(map[*Node]*NodeCopy)
	var dfs func(root *Node) *NodeCopy
	dfs = func(root *Node) *NodeCopy {
		if root == nil {
			return nil
		}
		if v, ok := seen[root]; ok {
			return v
		}
		copy := &NodeCopy{Val: root.Val}
		seen[root] = copy
		copy.Left = dfs(root.Left)
		copy.Right = dfs(root.Right)
		copy.Random = dfs(root.Random)
		return copy
	}
	return dfs(root)
}
```

#### TypeScript

```ts
/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     random: Node | null
 *     constructor(val?: number, left?: Node | null, right?: Node | null, random?: Node | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomBinaryTree(root: Node | null): NodeCopy | null {
    const seen = new Map<Node, NodeCopy>();
    const dfs = (root: Node | null): NodeCopy | null => {
        if (root === null) {
            return null;
        }
        if (seen.has(root)) {
            return seen.get(root)!;
        }
        const copy = new NodeCopy(root.val);
        seen.set(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    };
    return dfs(root);
}
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node random;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _random) {
        val = _val;
        left = _left;
        right = _right;
        random = _random;
    }
}
*/

public class Solution {
    public NodeCopy CopyRandomBinaryTree(Node root) {
        var seen = new Dictionary<Node, NodeCopy>();
        NodeCopy Dfs(Node root) {
            if (root == null) {
                return null;
            }
            if (seen.ContainsKey(root)) {
                return seen[root];
            }
            var copy = new NodeCopy(root.val);
            seen[root] = copy;
            copy.left = Dfs(root.left);
            copy.right = Dfs(root.right);
            copy.random = Dfs(root.random);
            return copy;
        }
        return Dfs(root);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
