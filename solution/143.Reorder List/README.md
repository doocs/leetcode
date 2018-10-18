## 重排链表
### 题目描述

给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

**示例 1:**
```
给定链表 1->2->3->4, 重新排列为 1->4->2->3.
```

**示例 2:**
```
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```

### 解法
先利用快慢指针找到链表的中间节点，之后对右半部分的链表进行`reverse`。最后对两个链表进行合并。注意边界的判断。


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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            pre = pre.next;
        }
        
        pre.next = null;
        
        // 将后半段的链表进行 reverse
        ListNode rightPre = new ListNode(-1);
        rightPre.next = null;
        ListNode t = null;
        while (slow != null) {
            t = slow.next;
            slow.next = rightPre.next;
            rightPre.next = slow;
            slow = t;
        }
        
        ListNode p1 = dummy.next;
        ListNode p2 = p1.next;
        ListNode p3 = rightPre.next;
        ListNode p4 = p3.next;
        while (p1 != null) {
            p3.next = p1.next;
            p1.next = p3;
            if (p2 == null) {
                break;
            }
            p1 = p2;
            p2 = p1.next;
            p3 = p4;
            p4 = p3.next;
            
        }
        
        if (p4 != null) {
            p1.next.next = p4;
        }
        head = dummy.next;
        
        
        
    }
}
```