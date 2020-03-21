/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow.next;
        slow.next = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = slow.next;
            slow.next = cur;
            cur = t;
        }
        ListNode t = slow.next;
        while (t != null) {
            if (head.val != t.val) {
                return false;
            }
            head = head.next;
            t = t.next;
        }
        return true;
    }
}