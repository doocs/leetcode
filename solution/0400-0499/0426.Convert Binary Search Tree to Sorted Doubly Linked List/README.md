# [426. 将二叉搜索树转化为排序的双向链表](https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list)

[English Version](/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>将一个 <strong>二叉搜索树</strong> 就地转化为一个 <strong>已排序的双向循环链表</strong> 。</p>

<p>对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。</p>

<p>特别地，我们希望可以 <strong>就地</strong> 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>root = [4,2,5,1,3] 

<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/images/bstdllreturndll.png" style="width: 400px;" />
<strong>输出：</strong>[1,2,3,4,5]

<strong>解释：</strong>下图显示了转化后的二叉搜索树，实线表示后继关系，虚线表示前驱关系。
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0426.Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List/images/bstdllreturnbst.png" style="width: 400px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>[1,2,3]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
<strong>解释：</strong>输入是空树，所以输出也是空链表。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-1000 <= Node.val <= 1000</code></li>
	<li><code>Node.left.val < Node.val < Node.right.val</code></li>
	<li><code>Node.val</code> 的所有值都是独一无二的</li>
	<li><code>0 <= Number of Nodes <= 2000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 排序链表：二叉搜索树中序遍历得到有序序列
- 循环链表：头节点指向链表尾节点，尾节点指向链表头节点
- 双向链表：`pre.right = cur`、`cur.left = pre`、`pre = cur`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    def treeToDoublyList(self, root: 'Node') -> 'Node':
        def dfs(cur):
            if cur is None:
                return
            dfs(cur.left)
            if self.pre is None:
                self.head = cur
            else:
                self.pre.right = cur
            cur.left = self.pre
            self.pre = cur
            dfs(cur.right)
        if root is None:
            return None
        self.head = self.pre = None
        dfs(root)
        self.head.left = self.pre
        self.pre.right = self.head
        return self.head
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
    private Node head;
    private Node pre;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(Node cur) {
        if (cur == null) return;
        dfs(cur.left);
        if (pre == null) head = cur;
        else pre.right = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
}
```

### **JavaScript**

```js
/**
 * // Definition for a Node.
 * function Node(val,left,right) {
 *    this.val = val;
 *    this.left = left;
 *    this.right = right;
 * };
 */
/**
 * @param {Node} root
 * @return {Node}
 */
var treeToDoublyList = function (root) {
    function dfs(cur) {
        if (!cur) return;
        dfs(cur.left);
        if (!pre) head = cur;
        else pre.right = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
    if (!root) return null;
    let head, pre;
    dfs(root);
    head.left = pre;
    pre.right = head;
    return head;
};
```

### **...**

```

```

<!-- tabs:end -->
