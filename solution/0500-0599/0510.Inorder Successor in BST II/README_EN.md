---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/README_EN.md
tags:
    - Tree
    - Binary Search Tree
    - Binary Tree
---

<!-- problem:start -->

# [510. Inorder Successor in BST II 🔒](https://leetcode.com/problems/inorder-successor-in-bst-ii)

[中文文档](/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/README.md)

## Description

<p>Given a <code>node</code> in a binary search tree, return <em>the in-order successor of that node in the BST</em>. If that node has no in-order successor, return <code>null</code>.</p>

<p>The successor of a <code>node</code> is the node with the smallest key greater than <code>node.val</code>.</p>

<p>You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for <code>Node</code>:</p>

<pre>
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
</pre>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_1.png" style="width: 122px; height: 117px;" />
<pre>
<strong>Input:</strong> tree = [2,1,3], node = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1&#39;s in-order successor node is 2. Note that both the node and the return value is of Node type.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_2.png" style="width: 246px; height: 229px;" />
<pre>
<strong>Input:</strong> tree = [5,3,6,2,4,null,null,1], node = 6
<strong>Output:</strong> null
<strong>Explanation:</strong> There is no in-order successor of the current node, so the answer is null.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>All Nodes will have unique values.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it without looking up any of the node&#39;s values?</p>

## Solutions

<!-- solution:start -->

### Solution 1: Case Discussion

If the `node` has a right subtree, then the in-order successor of `node` is the leftmost node in the right subtree.

If the `node` does not have a right subtree, then if `node` is the right child of its parent, we continue to search upwards until the parent of the node is null, or the node is the left child of its parent. In this case, the parent node is the in-order successor.

The time complexity is $O(h)$, where $h$ is the height of the binary tree. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None
"""

class Solution:
    def inorderSuccessor(self, node: 'Node') -> 'Optional[Node]':
        if node.right:
            node = node.right
            while node.left:
                node = node.left
            return node
        while node.parent and node.parent.right is node:
            node = node.parent
        return node.parent
```

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }
}
```

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* parent;
};
*/

class Solution {
public:
    Node* inorderSuccessor(Node* node) {
        if (node->right) {
            node = node->right;
            while (node->left) {
                node = node->left;
            }
            return node;
        }
        while (node->parent && node->parent->right == node) {
            node = node->parent;
        }
        return node->parent;
    }
};
```

```go
/**
 * Definition for Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Parent *Node
 * }
 */

func inorderSuccessor(node *Node) *Node {
	if node.Right != nil {
		node = node.Right
		for node.Left != nil {
			node = node.Left
		}
		return node
	}
	for node.Parent != nil && node == node.Parent.Right {
		node = node.Parent
	}
	return node.Parent
}
```

```ts
/**
 * Definition for a binary tree node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     parent: Node | null
 *     constructor(val?: number, left?: Node | null, right?: Node | null, parent?: Node | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.parent = (parent===undefined ? null : parent)
 *     }
 * }
 */

function inorderSuccessor(node: Node | null): Node | null {
    if (node.right) {
        node = node.right;
        while (node.left) {
            node = node.left;
        }
        return node;
    }
    while (node.parent && node === node.parent.right) {
        node = node.parent;
    }
    return node.parent;
}
```

```js
/**
 * // Definition for a Node.
 * function Node(val) {
 *    this.val = val;
 *    this.left = null;
 *    this.right = null;
 *    this.parent = null;
 * };
 */

/**
 * @param {Node} node
 * @return {Node}
 */
var inorderSuccessor = function (node) {
    if (node.right) {
        node = node.right;
        while (node.left) {
            node = node.left;
        }
        return node;
    }
    while (node.parent && node === node.parent.right) {
        node = node.parent;
    }
    return node.parent;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
