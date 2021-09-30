# [285. Inorder Successor in BST](https://leetcode.com/problems/inorder-successor-in-bst)

[中文文档](/solution/0200-0299/0285.Inorder%20Successor%20in%20BST/README.md)

## Description

<p>Given the <code>root</code> of a binary search tree and a node <code>p</code> in it, return <em>the in-order successor of that node in the BST</em>. If the given node has no in-order successor in the tree, return <code>null</code>.</p>

<p>The successor of a node <code>p</code> is the node with the smallest key greater than <code>p.val</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0285.Inorder%20Successor%20in%20BST/images/285_example_1.png" style="width: 122px; height: 117px;" />
<pre>
<strong>Input:</strong> root = [2,1,3], p = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1&#39;s in-order successor node is 2. Note that both p and the return value is of TreeNode type.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0285.Inorder%20Successor%20in%20BST/images/285_example_2.png" style="width: 246px; height: 229px;" />
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,null,1], p = 6
<strong>Output:</strong> null
<strong>Explanation:</strong> There is no in-order successor of the current node, so the answer is <code>null</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>All Nodes will have unique values.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @return {TreeNode}
 */
var inorderSuccessor = function (root, p) {
    function findMin(root) {
        if (!root) {
            return null;
        }
        while (root.left) {
            root = root.left;
        }
        return root;
    }
    if (!root) {
        return null;
    }
    let successor = null;
    while (root) {
        if (root.val > p.val) {
            successor = root;
            root = root.left;
        } else if (root.val < p.val) {
            root = root.right;
        } else {
            if (root.right) {
                successor = findMin(root.right);
            }
            break;
        }
    }
    return successor;
};
```

### **...**

```

```

<!-- tabs:end -->
