/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val = 0, ListNode next = null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        var dummy = new ListNode(0);
        dummy.next = head;
        var pre = dummy;

        while (pre != null) {
            var cur = pre;
            for (int i = 0; i < k; i++) {
                if (cur.next == null) {
                    return dummy.next;
                }
                cur = cur.next;
            }

            var node = pre.next;
            var nxt = cur.next;
            cur.next = null;
            pre.next = Reverse(node);
            node.next = nxt;
            pre = node;
        }

        return dummy.next;
    }

    private ListNode Reverse(ListNode head) {
        ListNode prev = null;
        var cur = head;
        while (cur != null) {
            var nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }
}
