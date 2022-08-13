# [02.08. Linked List Cycle](https://leetcode.cn/problems/linked-list-cycle-lcci)

[中文文档](/lcci/02.08.Linked%20List%20Cycle/README.md)

## Description

<p>Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.</p>

<p>Circular linked list: A (corrupt) linked list in which a node&#39;s next pointer points to an earlier node, so as to make a loop in the linked list.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>head = [3,2,0,-4], pos = 1

<strong>Output: </strong>tail connects to node index 1</pre>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>head = [1,2], pos = 0

<strong>Output: </strong>tail connects to node index 0</pre>

<p><strong>Example 3: </strong></p>

<pre>

<strong>Input: </strong>head = [1], pos = -1

<strong>Output: </strong>no cycle</pre>

<p><strong>Follow Up: </strong><br />

Can you solve it without using additional space?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        slow = fast = head
        has_cycle = False
        while not has_cycle and fast and fast.next:
            slow, fast = slow.next, fast.next.next
            has_cycle = slow == fast
        if not has_cycle:
            return None
        p = head
        while p != slow:
            p, slow = p.next, slow.next
        return p
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (!hasCycle && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            hasCycle = slow == fast;
        }
        if (!hasCycle) {
            return null;
        }
        ListNode p = head;
        while (p != slow) {
            p = p.next;
            slow = slow.next;
        }
        return p;
    }
}
```

### **C++**

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* detectCycle(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head;
        bool hasCycle = false;
        while (!hasCycle && fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            hasCycle = slow == fast;
        }
        if (!hasCycle) {
            return nullptr;
        }
        ListNode* p = head;
        while (p != slow) {
            p = p->next;
            slow = slow->next;
        }
        return p;
    }
};
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var detectCycle = function (head) {
    let slow = head;
    let fast = head;
    let hasCycle = false;
    while (!hasCycle && fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        hasCycle = slow == fast;
    }
    if (!hasCycle) {
        return null;
    }
    let p = head;
    while (p != slow) {
        p = p.next;
        slow = slow.next;
    }
    return p;
};
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func detectCycle(head *ListNode) *ListNode {
	slow, fast := head, head
	hasCycle := false
	for !hasCycle && fast != nil && fast.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
		hasCycle = slow == fast
	}
	if !hasCycle {
		return nil
	}
	p := head
	for p != slow {
		p, slow = p.Next, slow.Next
	}
	return p
}
```

### **...**

```

```

<!-- tabs:end -->
