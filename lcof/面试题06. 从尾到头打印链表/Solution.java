/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next, ++n);
        int[] ans = new int[n];
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ans[--n] = cur.val;
        }
        return ans;
    }
}