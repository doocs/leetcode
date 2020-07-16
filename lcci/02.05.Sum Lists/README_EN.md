# [02.05. Sum Lists](https://leetcode-cn.com/problems/sum-lists-lcci)

[中文文档](/lcci/02.05.Sum%20Lists/README.md)

## Description
<p>You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1&#39;s digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.</p>



<p>&nbsp;</p>



<p><strong>Example: </strong></p>



<pre>

<strong>Input: </strong>(7 -&gt; 1 -&gt; 6) + (5 -&gt; 9 -&gt; 2). That is, 617 + 295.

<strong>Output: </strong>2 -&gt; 1 -&gt; 9. That is, 912.

</pre>



<p><strong>Follow Up:&nbsp;</strong>Suppose the digits are stored in forward order. Repeat the above problem.</p>



<p><strong>Example: </strong></p>



<pre>

<strong>Input: </strong>(6 -&gt; 1 -&gt; 7) + (2 -&gt; 9 -&gt; 5). That is, 617 + 295.

<strong>Output: </strong>9 -&gt; 1 -&gt; 2. That is, 912.

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
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        p = ListNode(-1)
        carry, t = 0, p
        while l1 or l2:
            s = (0 if l1 is None else l1.val) + (0 if l2 is None else l2.val) + carry
            carry = 1 if s > 9 else 0
            t.next = ListNode(s % 10)
            t = t.next
            l1 = l1.next if l1 else l1
            l2 = l2.next if l2 else l2
        t.next = None if carry == 0 else ListNode(carry)
        return p.next


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        int carry = 0;
        ListNode t = p;
        while (l1 != null || l2 != null) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            t.next = new ListNode(s % 10);
            carry = s > 9 ? 1 : 0;
            t = t.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        t.next = carry == 0 ? null : new ListNode(carry);
        return p.next;
    }
}
```

### ...
```

```
