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
        int count = 0;
        if (head != null) {
            while(head != null){
                count = (count << 1) + head.val;
                head = head.next;
            }
        }
        return count;
    }
}