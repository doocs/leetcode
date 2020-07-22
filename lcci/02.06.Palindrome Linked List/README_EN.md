# [02.06. Palindrome Linked List](https://leetcode-cn.com/problems/palindrome-linked-list-lcci)

[中文文档](/lcci/02.06.Palindrome%20Linked%20List/README.md)

## Description
<p>Implement a function to check if a linked list is a palindrome.</p>



<p>&nbsp;</p>



<p><strong>Example 1: </strong></p>



<pre>

<strong>Input:  </strong>1-&gt;2

<strong>Output: </strong> false 

</pre>



<p><strong>Example 2: </strong></p>



<pre>

<strong>Input:  </strong>1-&gt;2-&gt;2-&gt;1

<strong>Output: </strong> true 

</pre>



<p>&nbsp;</p>



<p><b>Follow up:</b><br />

Could you do it in O(n) time and O(1) space?</p>

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
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None or head.next is None:
            return True
        slow, fast = head, head.next
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        cur = slow.next
        slow.next = None
        while cur:
            t = cur.next
            cur.next = slow.next
            slow.next = cur
            cur = t
        t = slow.next
        while t:
            if head.val != t.val:
                return False
            t = t.next
            head = head.next
        return True
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow.next;
        slow.next = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = slow.next;
            slow.next = cur;
            cur = t;
        }
        ListNode t = slow.next;
        while (t != null) {
            if (head.val != t.val) {
                return false;
            }
            head = head.next;
            t = t.next;
        }
        return true;
    }
}
```

### **...**
```

```

<!-- tabs:end -->