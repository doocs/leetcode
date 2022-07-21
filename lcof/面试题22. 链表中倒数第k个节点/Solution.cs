/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode GetKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next;
            k -= 1;
            if (k < 0) {
                slow = slow.next;
            }
        }
        return slow;
    }
}