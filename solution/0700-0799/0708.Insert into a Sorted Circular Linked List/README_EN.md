# [708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list)

[中文文档](/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/README.md)

## Description

<p>Given a node from a <strong>Circular Linked List</strong> which is sorted in ascending order,&nbsp;write a function to insert a value&nbsp;<code>insertVal</code> into the list such that it remains a&nbsp;sorted circular list. The given node can be a reference to <em>any</em> single node in the list, and may not be necessarily the smallest value in the circular&nbsp;list.</p>

<p>If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.</p>

<p>If the list is empty (i.e., given node is <code>null</code>), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/images/example_1_before_65p.jpg" style="width: 250px; height: 149px;" /><br />
&nbsp;
<pre>
<strong>Input:</strong> head = [3,4,1], insertVal = 2
<strong>Output:</strong> [3,4,1,2]
<strong>Explanation:</strong> In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/images/example_1_after_65p.jpg" style="width: 250px; height: 149px;" />

</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [], insertVal = 1
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The list is empty (given head is&nbsp;<code>null</code>). We create a new single circular list and return the reference to that single node.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [1], insertVal = 0
<strong>Output:</strong> [1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= Number of Nodes &lt;= 5 * 10^4</code></li>
	<li><code><font face="monospace">-10^6 &lt;= Node.val &lt;= 10^6</font></code></li>
	<li><code>-10^6 &lt;=&nbsp;insertVal &lt;= 10^6</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
