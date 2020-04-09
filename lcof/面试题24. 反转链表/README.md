# [面试题24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

## 题目描述
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

**示例:**
```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

**限制：**

- `0 <= 节点个数 <= 5000`

## 解法
### Python3
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if not head:
            return head
        dummy = ListNode(0)
        p = head
        while p:
            q = p.next
            p.next = dummy.next
            dummy.next = p
            p = q
        return dummy.next
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = q;
        }
        return dummy.next;
    }
}
```

### ...
```

```
