# [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii)

[中文文档](/solution/0000-0099/0092.Reverse%20Linked%20List%20II/README.md)

## Description

<p>Reverse a linked list from position <em>m</em> to <em>n</em>. Do it in one-pass.</p>

<p><strong>Note:&nbsp;</strong>1 &le; <em>m</em> &le; <em>n</em> &le; length of list.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL, <em>m</em> = 2, <em>n</em> = 4

<strong>Output:</strong> 1-&gt;4-&gt;3-&gt;2-&gt;5-&gt;NULL

</pre>

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
    def reverseBetween(self, head: ListNode, m: int, n: int) -> ListNode:
        if head is None or head.next is None or m == n:
            return head
        dummy = ListNode(0)
        dummy.next = head
        pre, cur = dummy, head
        for _ in range(m - 1):
            pre = cur
            cur = cur.next
        p, q = pre, cur
        for _ in range(n - m + 1):
            t = cur.next
            cur.next = pre
            pre = cur
            cur = t
        p.next = pre
        q.next = cur
        return dummy.next
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        for (int i = 0; i < m - 1; ++i) {
            pre = cur;
            cur = cur.next;
        }
        ListNode p = pre, q = cur;
        for (int i = 0; i < n - m + 1; ++i) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        p.next = pre;
        q.next = cur;
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
