# [面试题25. 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

## 题目描述
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

**示例1：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

**限制：**

- `0 <= 链表长度 <= 1000`

## 解法
### Python3
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        if l1 is None or l2 is None:
            return l1 or l2
        head = ListNode(0)
        p = head
        while l1 and l2:
            if l1.val < l2.val:
                t = l1.next
                p.next = l1
                p = l1
                l1 = t
            else:
                t = l2.next
                p.next = l2
                p = l2
                l2 = t

        p.next = l1 or l2
        return head.next
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode t = l1.next;
                p.next = l1;
                p = l1;
                l1 = t;
            } else {
                ListNode t = l2.next;
                p.next = l2;
                p = l2;
                l2 = t;
            }
        }
        p.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
```

### ...
```

```
