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