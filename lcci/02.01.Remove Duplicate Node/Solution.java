/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> s = new HashSet<>();
        s.add(head.val);
        ListNode cur = head;
        for (ListNode p = head.next; p != null; p = p.next) {
            if (!s.contains(p.val)) {
                cur.next = p;
                cur = cur.next;
                s.add(p.val);
            }
        }
        cur.next = null;
        return head;
    }
}