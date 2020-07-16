# [02.04. Partition List](https://leetcode-cn.com/problems/partition-list-lcci)

[中文文档](/lcci/02.04.Partition%20List/README.md)

## Description
<p>Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x. If x is contained within the list, the values of x only need to be after the elements less than x (see below). The partition element x can appear anywhere in the &quot;right partition&quot;; it does not need to appear between the left and right partitions.</p>



<p><strong>Example:</strong></p>



<pre>

<strong>Input:</strong> head = 3-&gt;5-&gt;8-&gt;5-&gt;10-&gt;2-&gt;1, <em>x</em> = 5

<strong>Output:</strong> 3-&gt;1-&gt;2-&gt;10-&gt;5-&gt;5-&gt;8

</pre>




## Solutions


### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        if head is None or head.next is None:
            return head
        left, right = ListNode(-1), ListNode(-1)
        p, q = left, right
        while head:
            t = head.next
            head.next = None
            if head.val < x:
                p.next = head
                p = p.next
            else:
                q.next = head
                q = q.next
            head = t
        p.next = right.next
        return left.next
```

### Java

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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode p = left, q = right;
        while (head != null) {
            ListNode t = head.next;
            head.next = null;
            if (head.val < x) {
                p.next = head;
                p = p.next;
            } else {
                q.next = head;
                q = q.next;
            }
            head = t;
        }
        p.next = right.next;
        return left.next;
    }
}
```

### ...
```

```
