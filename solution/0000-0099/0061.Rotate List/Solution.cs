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
    public ListNode RotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        var cur = head;
        int n = 0;
        while (cur != null) {
            cur = cur.next;
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        var fast = head;
        var slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        var ans = slow.next;
        slow.next = null;
        fast.next = head;
        return ans;
    }
}