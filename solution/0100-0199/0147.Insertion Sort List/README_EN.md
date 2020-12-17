# [147. Insertion Sort List](https://leetcode.com/problems/insertion-sort-list)

[中文文档](/solution/0100-0199/0147.Insertion%20Sort%20List/README.md)

## Description

<p>Sort a linked list using insertion sort.</p>

<ol>

</ol>

<p><img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif" style="height:180px; width:300px" /><br />

<small>A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.<br />

With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list</small><br />

&nbsp;</p>

<ol>

</ol>

<p><strong>Algorithm of Insertion Sort:</strong></p>

<ol>
    <li>Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.</li>
    <li>At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.</li>
    <li>It repeats until no input elements remain.</li>
</ol>

<p><br />

<strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> 4-&gt;2-&gt;1-&gt;3

<strong>Output:</strong> 1-&gt;2-&gt;3-&gt;4

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> -1-&gt;5-&gt;3-&gt;4-&gt;0

<strong>Output:</strong> -1-&gt;0-&gt;3-&gt;4-&gt;5

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
    def insertionSortList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        dummy = ListNode(head.val)
        dummy.next = head
        pre, cur = dummy, head
        while cur:
            if pre.val <= cur.val:
                pre, cur = cur, cur.next
                continue
            p = dummy
            while p.next.val <= cur.val:
                p = p.next
            t = cur.next
            cur.next = p.next
            p.next = cur
            pre.next = t
            cur = t
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(head.val);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur != null) {
            if (pre.val <= cur.val) {
                pre = cur;
                cur = cur.next;
                continue;
            }
            ListNode p = dummy;
            while (p.next.val <= cur.val) {
                p = p.next;
            }
            ListNode t = cur.next;
            cur.next = p.next;
            p.next = cur;
            pre.next = t;
            cur = t;
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
