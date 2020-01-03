/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode res = root;
        while (root.next != null) {
            if (root.next.val == val) {
                root.next = root.next.next;
            } else {
                root = root.next;
            }
        }
        return res.next;
    }
}