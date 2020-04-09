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