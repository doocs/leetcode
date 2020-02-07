/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        ListNode res = new ListNode(0);
        ListNode slow = head, fast = head, index = res;
        int i = 1;
        while (fast != null) {
            ListNode temp = fast.next;
            if (i ++ % k == 0) {
                fast.next = null;
                ListNode zou = slow;
                while (slow != null) {
                    ListNode itme = slow.next;
                    slow.next = index.next;
                    index.next = slow;
                    slow = itme;
                }
                index = zou;
                slow = temp;
            }
            fast = temp;
        }
        index.next = slow;
        return res.next;
    }
}