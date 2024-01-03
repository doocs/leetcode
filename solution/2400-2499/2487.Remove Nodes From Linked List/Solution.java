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
    public ListNode removeNodes(ListNode head) {
        ListNode dummy = new ListNode(1 << 30, head);
        Deque<ListNode> stk = new ArrayDeque<>();
        stk.offerLast(dummy);
        for (ListNode cur = head; cur != null; cur = cur.next) {
            while (stk.peekLast().val < cur.val) {
                stk.pollLast();
            }
            stk.peekLast().next = cur;
            stk.offerLast(cur);
        }
        return dummy.next;
    }
}