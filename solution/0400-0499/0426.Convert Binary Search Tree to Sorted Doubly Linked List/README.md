---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/README.md
tags:
    - 栈
    - 树
    - 深度优先搜索
    - 二叉搜索树
    - 链表
    - 二叉树
    - 双向链表
---

<!-- problem:start -->

# [426. 将二叉搜索树转化为排序的双向链表 🔒](https://leetcode.cn/problems/convert-binary-search-tree-to-sorted-doubly-linked-list)

[English Version](/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>将一个 <strong>二叉搜索树</strong> 就地转化为一个 <strong>已排序的双向循环链表</strong> 。</p>

<p>对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。</p>

<p>特别地，我们希望可以 <strong>原地</strong> 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>root = [4,2,5,1,3] 

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/images/bstdllreturndll.png" style="width: 400px;" />
<strong>输出：</strong>[1,2,3,4,5]

<strong>解释：</strong>下图显示了转化后的二叉搜索树，实线表示后继关系，虚线表示前驱关系。
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/images/bstdllreturnbst.png" style="width: 400px;" />
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>[1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量在范围&nbsp;<code>[0, 2000]</code>&nbsp;中</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li>树中的所有值都是 <strong>独一无二</strong> 的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> BST 的中序遍历即为升序。若先收集节点再连边，需要额外数组。题目要求原地，用 $\textit{left}/\textit{right}$ 充当前驱后继。
>
> 中序遍历时把 $\textit{prev}$ 与当前节点互指，并记下第一个节点为 $\textit{head}$；遍历结束后再把 $\textit{head}$ 与最后一个节点连成环。
>
> 先左后根再右，保证链接顺序与有序序列一致；哑元或空 $\textit{prev}$ 用来识别链表头。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
"""


class Solution:
    def treeToDoublyList(self, root: 'Optional[Node]') -> 'Optional[Node]':
        def dfs(root):
            if root is None:
                return
            nonlocal prev, head
            dfs(root.left)
            if prev:
                prev.right = root
                root.left = prev
            else:
                head = root
            prev = root
            dfs(root.right)

        if root is None:
            return None
        head = prev = None
        dfs(root)
        prev.right = head
        head.left = prev
        return head
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    private Node prev;
    private Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        prev = null;
        head = null;
        dfs(root);
        prev.right = head;
        head.left = prev;
        return head;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev != null) {
            prev.right = root;
            root.left = prev;
        } else {
            head = root;
        }
        prev = root;
        dfs(root.right);
    }
}
```

#### C++

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;

    Node() {}

    Node(int _val) {
        val = _val;
        left = NULL;
        right = NULL;
    }

    Node(int _val, Node* _left, Node* _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
public:
    Node* prev;
    Node* head;

    Node* treeToDoublyList(Node* root) {
        if (!root) return nullptr;
        prev = nullptr;
        head = nullptr;
        dfs(root);
        prev->right = head;
        head->left = prev;
        return head;
    }

    void dfs(Node* root) {
        if (!root) return;
        dfs(root->left);
        if (prev) {
            prev->right = root;
            root->left = prev;
        } else
            head = root;
        prev = root;
        dfs(root->right);
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
 * }
 */

func treeToDoublyList(root *Node) *Node {
	if root == nil {
		return root
	}
	var prev, head *Node

	var dfs func(root *Node)
	dfs = func(root *Node) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if prev != nil {
			prev.Right = root
			root.Left = prev
		} else {
			head = root
		}
		prev = root
		dfs(root.Right)
	}
	dfs(root)
	prev.Right = head
	head.Left = prev
	return head
}
```

#### JavaScript

```js
/**
 * // Definition for a Node.
 * function Node(val, left, right) {
 *      this.val = val;
 *      this.left = left;
 *      this.right = right;
 *  };
 */

/**
 * @param {Node} root
 * @return {Node}
 */
var treeToDoublyList = function (root) {
    if (!root) return root;
    let prev = null;
    let head = null;

    function dfs(root) {
        if (!root) return;
        dfs(root.left);
        if (prev) {
            prev.right = root;
            root.left = prev;
        } else {
            head = root;
        }
        prev = root;
        dfs(root.right);
    }
    dfs(root);
    prev.right = head;
    head.left = prev;
    return head;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
