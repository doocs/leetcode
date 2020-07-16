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


### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        fast = slow = p = head
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
            if fast == slow:
                break
        if fast is None or fast.next is None:
            return None
        while slow != p:
            p = p.next
            slow = slow.next
        return p
```

### Java

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
         ListNode fast = head, slow = head;
         while (fast != null && fast.next != null) {
             fast = fast.next.next;
             slow = slow.next;
             if (fast == slow) break;
         }
         if (fast == null || fast.next == null) return null;
         ListNode p = head;
         while (p != slow) {
             p = p.next;
             slow = slow.next;
         }
         return p;
    }
}
```

### ...
```

```
