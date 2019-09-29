/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return head;
        if (head.next == null)
            return head;

        int size = 1;
        // build ring
        ListNode nodeLast = head;
        while (nodeLast.next != null) {
            nodeLast = nodeLast.next;
            size++;
        }
        nodeLast.next = head;

        // cutting
        k = size - k % size;
        while (k-- > 0) {
            nodeLast = head;
            head = head.next;
        }
        nodeLast.next = null;
        return head;
    }
}
