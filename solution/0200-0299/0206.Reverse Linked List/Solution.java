/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode reverse = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            if (reverse == null) {
                reverse = temp;
                temp.next = null;
            } else {
                temp.next = reverse;
                reverse = temp;
            }
        }
        return reverse;
    }
}