# [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements)

[中文文档](/solution/0200-0299/0203.Remove%20Linked%20List%20Elements/README.md)

## Description

<p>Remove all elements from a linked list of integers that have value <b><i>val</i></b>.</p>

<p><b>Example:</b></p>

<pre>

<b>Input:</b>  1-&gt;2-&gt;6-&gt;3-&gt;4-&gt;5-&gt;6, <em><b>val</b></em> = 6

<b>Output:</b> 1-&gt;2-&gt;3-&gt;4-&gt;5

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        dummy = ListNode(-1, head)
        pre = dummy
        while pre and pre.next:
            if pre.next.val != val:
                pre = pre.next
            else:
                pre.next = pre.next.next
        return dummy.next
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (pre != null && pre.next != null) {
            if (pre.next.val != val) pre = pre.next;
            else pre.next = pre.next.next;
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
