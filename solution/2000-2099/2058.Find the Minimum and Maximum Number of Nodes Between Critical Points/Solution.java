/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;
        int first = 0, last = 0;
        int i = 1;
        int[] ans = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
        while (curr.next != null) {
            if (curr.val < Math.min(prev.val, curr.next.val)
                || curr.val > Math.max(prev.val, curr.next.val)) {
                if (last == 0) {
                    first = i;
                    last = i;
                } else {
                    ans[0] = Math.min(ans[0], i - last);
                    ans[1] = i - first;
                    last = i;
                }
            }
            ++i;
            prev = curr;
            curr = curr.next;
        }
        return first == last ? new int[] {-1, -1} : ans;
    }
}