/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head, q = head;
        while (k-- > 0) {
            p = p.next;
        }
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        return q;
    }
}