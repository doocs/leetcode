/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++n;
        }
        int res = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            --n;
            if (cur.val == 1) {
                res += Math.pow(2, n);
            }
        }
        return res;
    }
}