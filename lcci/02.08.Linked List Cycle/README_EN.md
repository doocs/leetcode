# [02.08. Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle-lcci)

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
    def hasCycle(self, head: ListNode) -> bool:
        slow = fast = head
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
            if slow == fast:
                return True
        return False
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
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
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
    bool hasCycle(ListNode *head) {
        ListNode* slow = head;
        ListNode* fast = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
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
 * @return {boolean}
 */
var hasCycle = function(head) {
    let slow = head;
    let fast = head;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return true;
        }
    }
    return false;
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
func hasCycle(head *ListNode) bool {
    slow, fast := head, head
    for fast != nil && fast.Next != nil {
        slow, fast = slow.Next, fast.Next.Next
        if slow == fast {
            return true
        }
    }
    return false
}
```

### **...**

```

```

<!-- tabs:end -->
