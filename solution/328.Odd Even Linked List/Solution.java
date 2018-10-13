/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        // 链表结点数小于 3,直接返回
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = head, t = pre.next, cur = t;
        while (cur != null && cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = t;
            pre = pre.next;
            // cur.next可能为空，所以在下一次循环要判断 cur != null 是否满足
            cur = cur.next;
            t = pre.next;
        }
        
        return dummy.next;
    }
}