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
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Map<Integer, ListNode> last = new HashMap<>();
        int s = 0;
        ListNode cur = dummy;
        while (cur != null) {
            s += cur.val;
            last.put(s, cur);
            cur = cur.next;
        }
        s = 0;
        cur = dummy;
        while (cur != null) {
            s += cur.val;
            cur.next = last.get(s).next;
            cur = cur.next;
        }
        return dummy.next;
    }
}