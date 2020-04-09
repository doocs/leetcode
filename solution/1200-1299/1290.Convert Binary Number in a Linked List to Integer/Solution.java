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
        int sum = 0;
        StringBuilder sb = new StringBuilder("0");
        while (head != null) {
            sum += head.val;
            if (sum != 0) {
                sb.append(head.val);
            }
            head = head.next;
        }
        return Integer.valueOf(sb.toString(), 2);
    }
}