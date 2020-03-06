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