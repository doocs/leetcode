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
    public int[] ReversePrint(ListNode head) {
        List<int> ans = new List<int>();
        for (; head != null; head = head.next) {
            ans.Add(head.val);
        }
        ans.Reverse();
        return ans.ToArray();
    }
}