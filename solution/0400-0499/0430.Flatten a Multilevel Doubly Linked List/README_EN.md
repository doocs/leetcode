# [430. Flatten a Multilevel Doubly Linked List](https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list)

[中文文档](/solution/0400-0499/0430.Flatten%20a%20Multilevel%20Doubly%20Linked%20List/README.md)

## Description

<p>You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.</p>

<p>Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
<strong>Output:</strong> [1,2,3,7,8,11,12,9,10,4,5,6]
<strong>Explanation:
</strong>
The multilevel linked list in the input is as follows:

<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0430.Flatten%20a%20Multilevel%20Doubly%20Linked%20List/images/multilevellinkedlist.png" style="width: 640px;" />

After flattening the multilevel linked list it becomes:

<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0430.Flatten%20a%20Multilevel%20Doubly%20Linked%20List/images/multilevellinkedlistflattened.png" style="width: 1100px;" />
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2,null,3]
<strong>Output:</strong> [1,3,2]
<strong>Explanation:

</strong>The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>How&nbsp;multilevel linked list is represented in test case:</strong></p>

<p>We use the&nbsp;multilevel linked list from <strong>Example 1</strong> above:</p>

<pre>
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL</pre>

<p>The serialization of each level is as follows:</p>

<pre>
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
</pre>

<p>To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:</p>

<pre>
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
</pre>

<p>Merging the serialization of each level and removing trailing nulls we obtain:</p>

<pre>
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of Nodes will not exceed <code>1000</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val, prev, next, child):
        self.val = val
        self.prev = prev
        self.next = next
        self.child = child
"""

class Solution:
    def flatten(self, head: 'Node') -> 'Node':
        def preorder(pre, cur):
            if cur is None:
                return pre
            cur.prev = pre
            pre.next = cur

            t = cur.next
            tail = preorder(cur, cur.child)
            cur.child = None
            return preorder(tail, t)
            
        if head is None:
            return None
        dummy = Node(0, None, head, None)
        preorder(dummy, head)
        dummy.next.prev = None
        return dummy.next
```

### **Java**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node();
        dummy.next = head;
        preorder(dummy, head);
        dummy.next.prev = null;
        return dummy.next;
    }

    private Node preorder(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }
        cur.prev = pre;
        pre.next = cur;

        Node t = cur.next;
        Node tail = preorder(cur, cur.child);
        cur.child = null;
        return preorder(tail, t);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
