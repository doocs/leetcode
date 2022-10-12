# [1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii)

[中文文档](/solution/1600-1699/1650.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20III/README.md)

## Description

<p>Given two nodes of a&nbsp;binary tree <code>p</code> and <code>q</code>, return <em>their&nbsp;lowest common ancestor (LCA)</em>.</p>

<p>Each node will have a reference to its parent node. The definition for <code>Node</code> is below:</p>

<pre>
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
</pre>

<p>According to the <strong><a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a></strong>: &quot;The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow <b>a node to be a descendant of itself</b>).&quot;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1650.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20III/images/binarytree.png" style="width: 200px; height: 190px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> The LCA of nodes 5 and 1 is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1650.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20III/images/binarytree.png" style="width: 200px; height: 190px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<strong>Output:</strong> 5
<strong>Explanation:</strong> The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2], p = 1, q = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 10<sup>5</sup>]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>p != q</code></li>
	<li><code>p</code> and <code>q</code> exist in the tree.</li>
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
    def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
        a, b = p, q
        while a != b:
            a = a.parent if a.parent else q
            b = b.parent if b.parent else p
        return a
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
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a.parent == null ? q : a.parent;
            b = b.parent == null ? p : b.parent;
        }
        return a;
    }
}
```

### **C++**

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
    Node* lowestCommonAncestor(Node* p, Node* q) {
        Node* a = p;
        Node* b = q;
        while (a != b) {
            a = a->parent ? a->parent : q;
            b = b->parent ? b->parent : p;
        }
        return a;
    }
};
```

### **Go**

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

func lowestCommonAncestor(p *Node, q *Node) *Node {
	a, b := p, q
	for a != b {
		if a.Parent != nil {
			a = a.Parent
		} else {
			a = q
		}
		if b.Parent != nil {
			b = b.Parent
		} else {
			b = p
		}
	}
	return a
}
```

### **...**

```

```

<!-- tabs:end -->
