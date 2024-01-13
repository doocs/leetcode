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
        int n = 0;
        ListNode cur = head;
        for (; cur != null; cur = cur.next) {
            ++n;
        }
        int[] ans = new int[n];
        cur = head;
        for (; cur != null; cur = cur.next) {
            ans[--n] = cur.val;
        }
        return ans;
    }
}