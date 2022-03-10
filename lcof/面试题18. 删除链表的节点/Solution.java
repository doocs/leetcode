/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (; pre.next != null && pre.next.val != val; pre = pre.next);
        pre.next = pre.next == null ? null : pre.next.next;
        return dummy.next;
    }
}