# [708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list)

[中文文档](/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/README.md)

## Description

<p>Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value <code>insertVal</code> into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.</p>

<p>If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.</p>

<p>If the list is empty (i.e., the given node is <code>null</code>), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/images/example_1_before_65p.jpg" style="width: 250px; height: 149px;" /><br />
&nbsp;
<pre>
<strong>Input:</strong> head = [3,4,1], insertVal = 2
<strong>Output:</strong> [3,4,1,2]
<strong>Explanation:</strong> In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0708.Insert%20into%20a%20Sorted%20Circular%20Linked%20List/images/example_1_after_65p.jpg" style="width: 250px; height: 149px;" />

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
	<li>The number of nodes in the list is in the range <code>[0, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>6</sup> &lt;= Node.val, insertVal &lt;= 10<sup>6</sup></code></li>
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
    def insert(self, head: 'Optional[Node]', insertVal: int) -> 'Node':
        node = Node(insertVal)
        if head is None:
            node.next = node
            return node
        prev, curr = head, head.next
        while curr != head:
            if prev.val <= insertVal <= curr.val or (
                prev.val > curr.val and (insertVal >= prev.val or insertVal <= curr.val)
            ):
                break
            prev, curr = curr, curr.next
        prev.next = node
        node.next = curr
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
        Node prev = head, curr = head.next;
        while (curr != head) {
            if ((prev.val <= insertVal && insertVal <= curr.val) || (prev.val > curr.val && (insertVal >= prev.val || insertVal <= curr.val))) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = node;
        node.next = curr;
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
        Node* node = new Node(insertVal);
        if (!head) {
            node->next = node;
            return node;
        }
        Node *prev = head, *curr = head->next;
        while (curr != head) {
            if ((prev->val <= insertVal && insertVal <= curr->val) || (prev->val > curr->val && (insertVal >= prev->val || insertVal <= curr->val))) break;
            prev = curr;
            curr = curr->next;
        }
        prev->next = node;
        node->next = curr;
        return head;
    }
};
```

### **Go**

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 * }
 */

func insert(head *Node, x int) *Node {
	node := &Node{Val: x}
	if head == nil {
		node.Next = node
		return node
	}
	prev, curr := head, head.Next
	for curr != head {
		if (prev.Val <= x && x <= curr.Val) || (prev.Val > curr.Val && (x >= prev.Val || x <= curr.Val)) {
			break
		}
		prev, curr = curr, curr.Next
	}
	prev.Next = node
	node.Next = curr
	return head
}
```

### **...**

```

```

<!-- tabs:end -->
