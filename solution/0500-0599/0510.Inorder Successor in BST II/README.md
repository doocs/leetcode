# [510. 二叉搜索树中的中序后继 II](https://leetcode.cn/problems/inorder-successor-in-bst-ii)

[English Version](/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉搜索树和其中的一个节点 <code>node</code> ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 <code>null</code> 。</p>

<p>一个节点 <code>node</code> 的中序后继是键值比 <code>node.val</code> 大所有的节点中键值最小的那个。</p>

<p>你可以直接访问结点，但无法直接访问树。每个节点都会有其父节点的引用。节点 <code>Node</code> 定义如下：</p>

<pre>
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}</pre>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_1.png" style="height: 117px; width: 122px;" /></p>

<pre>
<strong>输入：</strong>tree = [2,1,3], node = 1
<strong>输出：</strong>2
<strong>解析：</strong>1 的中序后继结点是 2 。注意节点和返回值都是 Node 类型的。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_2.png" style="height: 229px; width: 246px;" /></p>

<pre>
<strong>输入：</strong>tree = [5,3,6,2,4,null,null,1], node = 6
<strong>输出：</strong>null
<strong>解析：</strong>该结点没有中序后继，因此返回<code> null 。</code>
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_34.png" style="height: 335px; width: 438px;" /></p>

<pre>
<strong>输入：</strong>tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 15
<strong>输出：</strong>17
</pre>

<p><strong>示例 4：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0510.Inorder%20Successor%20in%20BST%20II/images/285_example_34.png" style="height: 335px; width: 438px;" /></p>

<pre>
<strong>输入：</strong>tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 13
<strong>输出：</strong>15
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>tree = [0], node = 0
<strong>输出：</strong>null
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内。</li>
	<li><code>-10<sup>5</sup> <= Node.val <= 10<sup>5</sup></code></li>
	<li>树中各结点的值均保证唯一。</li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能否在不访问任何结点的值的情况下解决问题?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

判断 node 是否有右子树，

-   若有，找到右子树的最左节点返回
-   若没有，则向上寻找父节点，直到节点等于父节点的左孩子，返回父节点

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        while node.parent and node == node.parent.right:
            node = node.parent
        return node.parent
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
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
    Node* inorderSuccessor(Node* node) {
        if (node->right) {
            node = node->right;
            while (node->left) node = node->left;
            return node;
        }
        while (node->parent && node == node->parent->right) node = node->parent;
        return node->parent;
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
var inorderSuccessor = function (node) {
    if (node.right) {
        node = node.right;
        while (node.left) node = node.left;
        return node;
    }
    while (node.parent && node == node.parent.right) node = node.parent;
    return node.parent;
};
```

### **...**

```

```

<!-- tabs:end -->
