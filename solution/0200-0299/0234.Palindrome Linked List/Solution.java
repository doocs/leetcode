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
        if (head == null) {
            return true;
        }
        ListNode mid = findMidNode(head);
        ListNode secondHalfList = reverseList(mid.next);
        boolean result = true;
        ListNode p = head, q = secondHalfList;
        while (result && q != null) {
            if (p.val != q.val) {
                result = false;
            } else {
                p = p.next;
                q = q.next;
            }
        }
        mid.next = reverseList(secondHalfList);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }

    private ListNode findMidNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}