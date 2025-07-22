/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre != null) {
            ListNode cur = pre;
            for (int i = 0; i < k; i++) {
                cur = cur.next;
                if (cur == null) {
                    return dummy.next;
                }
            }
            ListNode node = pre.next;
            ListNode nxt = cur.next;
            cur.next = null;
            pre.next = reverse(node);
            node.next = nxt;
            pre = node;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = nxt;
        }
        return dummy.next;
    }
}
