/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode SwapPairs(ListNode head) {
        if (head is null || head.next is null) {
            return head;
        }
        ListNode t = SwapPairs(head.next.next);
        ListNode p = head.next;
        p.next = head;
        head.next = t;
        return p;
    }
}