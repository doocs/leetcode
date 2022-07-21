/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */

 public class Solution {
     public ListNode DeleteNode(ListNode head, int val) {
         if (head == null) {
             return null;
         }
         if (head.val == val) {
             return head.next;
         }
         ListNode p = head;
         while (p.next != null && p.next.val != val) {
             p = p.next;
         }
         p.next = p.next == null ? null : p.next.next;
         return head;
     }
 }