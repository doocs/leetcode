# [1666. Change the Root of a Binary Tree](https://leetcode.com/problems/change-the-root-of-a-binary-tree)

[中文文档](/solution/1600-1699/1666.Change%20the%20Root%20of%20a%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and a <code>leaf</code> node, reroot the tree so that the <code>leaf</code> is the new root.</p>

<p>You can reroot the tree with the following steps for each node <code>cur</code> on the path <strong>starting from the </strong><code>leaf</code> up to the <code>root</code>​​​ <strong>excluding the root</strong>:</p>

<ol>
	<li>If <code>cur</code> has a left child, then that child becomes <code>cur</code>&#39;s right child.</li>
	<li><code>cur</code>&#39;s original parent becomes <code>cur</code>&#39;s left child. Note that in this process the original parent&#39;s pointer to <code>cur</code> becomes <code>null</code>, making it have at most one child.</li>
</ol>

<p>Return <em>the new root </em><em>of the rerooted tree.</em></p>

<p><strong>Note:</strong> Ensure that your solution sets the <code>Node.parent</code> pointers correctly after rerooting or you will receive &quot;Wrong Answer&quot;.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1666.Change%20the%20Root%20of%20a%20Binary%20Tree/images/fliptree.png" style="width: 400px; height: 298px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 7
<strong>Output:</strong> [7,2,null,5,4,3,6,null,null,null,1,null,null,0,8]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 0
<strong>Output:</strong> [0,1,null,3,8,5,null,null,null,6,2,null,null,7,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 100]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>leaf</code> exist in the tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
    def flipBinaryTree(self, root: "Node", leaf: "Node") -> "Node":
        cur = leaf
        p = cur.parent
        while cur != root:
            gp = p.parent
            if cur.left:
                cur.right = cur.left
            cur.left = p
            p.parent = cur
            if p.left == cur:
                p.left = None
            elif p.right == cur:
                p.right = None
            cur = p
            p = gp
        leaf.parent = None
        return leaf
```

### **Java**

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
    public Node flipBinaryTree(Node root, Node leaf) {
        Node cur = leaf;
        Node p = cur.parent;
        while (cur != root) {
            Node gp = p.parent;
            if (cur.left != null) {
                cur.right = cur.left;
            }
            cur.left = p;
            p.parent = cur;
            if (p.left == cur) {
                p.left = null;
            } else if (p.right == cur) {
                p.right = null;
            }
            cur = p;
            p = gp;
        }
        leaf.parent = null;
        return leaf;
    }
}
```

### **C++**

```cpp
/*
// Definition for a Node->
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
    Node* flipBinaryTree(Node* root, Node * leaf) {
        Node* cur = leaf;
        Node* p = cur->parent;
        while (cur != root) {
            Node* gp = p->parent;
            if (cur->left) {
                cur->right = cur->left;
            }
            cur->left = p;
            p->parent = cur;
            if (p->left == cur) {
                p->left = nullptr;
            } else if (p->right == cur) {
                p->right = nullptr;
            }
            cur = p;
            p = gp;
        }
        leaf->parent = nullptr;
        return leaf;
    }
};
```

### **C#**

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
*/

public class Solution {
    public Node FlipBinaryTree(Node root, Node leaf) {
        Node cur = leaf;
        Node p = cur.parent;
        while (cur != root) {
            Node gp = p.parent;
            if (cur.left != null) {
                cur.right = cur.left;
            }
            cur.left = p;
            p.parent = cur;
            if (p.left == cur) {
                p.left = null;
            } else if (p.right == cur) {
                p.right = null;
            }
            cur = p;
            p = gp;
        }
        leaf.parent = null;
        return leaf;
    }
}
```

### **JavaScript**

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
var flipBinaryTree = function (root, leaf) {
    let cur = leaf;
    let p = cur.parent;
    while (cur != root) {
        const gp = p.parent;
        if (cur.left != null) {
            cur.right = cur.left;
        }
        cur.left = p;
        p.parent = cur;
        if (p.left == cur) {
            p.left = null;
        } else if (p.right == cur) {
            p.right = null;
        }
        cur = p;
        p = gp;
    }
    leaf.parent = null;
    return leaf;
};
```

### **...**

```

```

<!-- tabs:end -->
