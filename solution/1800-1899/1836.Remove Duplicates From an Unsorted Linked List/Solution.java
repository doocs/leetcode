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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode cur = head;
        Map<Integer, Integer> counter = new HashMap<>();
        for (; cur != null; cur = cur.next) {
            counter.put(cur.val, counter.getOrDefault(cur.val, 0) + 1);
        }

        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (cur = head; cur != null; cur = cur.next) {
            if (counter.get(cur.val) > 1) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
        }
        return dummy.next;
    }
}