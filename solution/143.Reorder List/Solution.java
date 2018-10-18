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