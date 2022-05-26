/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
         ListNode fast = head, slow = head;
         while (fast != null && fast.next != null) {
             fast = fast.next.next;
             slow = slow.next;
             if (fast == slow) break;
         }
         if (fast == null || fast.next == null) return null;
         ListNode p = head;
         while (p != slow) {
             p = p.next;
             slow = slow.next;
         }
         return p;
    }
}