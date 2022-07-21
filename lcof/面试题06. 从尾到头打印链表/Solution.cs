/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
 public class Solution {
     public int[] ReversePrint(ListNode head) {
         List<int> ans = new List<int>();
         while (head != null) {
             ans.Add(head.val);
             head = head.next;
         }
         ans.Reverse();
         return ans.ToArray();
     }
 }