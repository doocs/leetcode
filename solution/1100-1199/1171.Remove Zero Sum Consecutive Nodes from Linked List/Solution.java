/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> preSumNode = new HashMap<>();
        int s = 0;
        for (ListNode cur = dummy; cur != null; cur = cur.next) {
            s += cur.val;
            preSumNode.put(s, cur);
        }
        s = 0;
        for (ListNode cur = dummy; cur != null; cur = cur.next) {
            s += cur.val;
            cur.next = preSumNode.get(s).next;
        }
        return dummy.next;
    }
}