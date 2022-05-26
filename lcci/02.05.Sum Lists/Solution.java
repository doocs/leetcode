/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        int carry = 0;
        ListNode t = p;
        while (l1 != null || l2 != null) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            t.next = new ListNode(s % 10);
            carry = s > 9 ? 1 : 0;
            t = t.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        t.next = carry == 0 ? null : new ListNode(carry);
        return p.next;
    }
}