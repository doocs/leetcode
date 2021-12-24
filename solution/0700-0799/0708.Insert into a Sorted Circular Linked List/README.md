# [708. 循环有序列表的插入](https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list)

[English Version](/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定循环升序列表中的一个点，写一个函数向这个列表中插入一个新元素 <code>insertVal</code> ，使这个列表仍然是循环升序的。</p>

<p>给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。</p>

<p>如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。</p>

<p>如果列表为空（给定的节点是 <code>null</code>），你需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/images/example_1_before_65p.jpg" style="height: 149px; width: 250px;" /><br />
 
<pre>
<strong>输入：</strong>head = [3,4,1], insertVal = 2
<strong>输出：</strong>[3,4,1,2]
<strong>解释：</strong>在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/images/example_1_after_65p.jpg" style="height: 149px; width: 250px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [], insertVal = 1
<strong>输出：</strong>[1]
<strong>解释：</strong>列表为空（给定的节点是 <code>null</code>），创建一个循环有序列表并返回这个节点。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [1], insertVal = 0
<strong>输出：</strong>[1,0]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= Number of Nodes <= 5 * 10^4</code></li>
	<li><code><font face="monospace">-10^6 <= Node.val <= 10^6</font></code></li>
	<li><code>-10^6 <= insertVal <= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, next=None):
        self.val = val
        self.next = next
"""

class Solution:
    def insert(self, head: 'Node', insertVal: int) -> 'Node':
        node = Node(val=insertVal)
        if head is None:
            node.next = node
            return node
        pre, cur = head, head.next
        while 1:
            if pre.val <= insertVal <= cur.val or (pre.val > cur.val and (insertVal >= pre.val or insertVal <= cur.val)):
                break
            pre, cur = cur, cur.next
            if pre == head:
                break
        pre.next = node
        node.next = cur
        return head
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node pre = head, cur = head.next;
        while (true) {
            if ((pre.val <= insertVal && insertVal <= cur.val) || (pre.val > cur.val && (insertVal >= pre.val || cur.val >= insertVal))) {
                break;
            }
            pre = cur;
            cur = cur.next;
            if (pre == head) {
                break;
            }
        }
        pre.next = node;
        node.next = cur;
        return head;
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
    Node* next;

    Node() {}

    Node(int _val) {
        val = _val;
        next = NULL;
    }

    Node(int _val, Node* _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
public:
    Node* insert(Node* head, int insertVal) {
        Node* insert = new Node(insertVal);
        if (head == nullptr) {
            head = insert;
            head->next = head;
        } else if (head->next == nullptr) {
            head->next = insert;
            insert->next = head;
        } else {
            insertCore(head, insert);
        }

        return head;
    }

    void insertCore(Node* head, Node* insert) {
        Node* cur = head;
        Node* maxNode = head;
        Node* next = head->next;

        while (!(cur->val <= insert->val && insert->val <= next->val) && next != head) {
            cur = cur->next;
            next = next->next;

            if (cur->val >= maxNode->val)
                maxNode = cur;
        }

        if (cur->val <= insert->val && insert->val <= next->val) {
            insert->next = next;
            cur->next = insert;
        } else {
            insert->next = maxNode->next;
            maxNode->next = insert;

        }

    }
};
```

### **...**

```

```

<!-- tabs:end -->
