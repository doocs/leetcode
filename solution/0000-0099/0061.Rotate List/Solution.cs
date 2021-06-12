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
        if (k == 0 || head == null || head.next == null)
        {
            return head;
        }
        var n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next)
        {
            ++n;
        }
        k %= n;
        if (k == 0)
        {
            return head;
        }
        ListNode slow = head, fast = head;
        while (k-- > 0)
        {
            fast = fast.next;
        }
        while (fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode start = slow.next;
        slow.next = null;
        fast.next = head;
        return start;
    }
}