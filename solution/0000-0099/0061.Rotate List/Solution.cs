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
        ListNode cur = head;
        var n = 0;
        while (cur != null)
        {
            cur = cur.next;
            ++n;
        }
        k %= n;
        if (k == 0)
        {
            return head;
        }
        ListNode p = head, q = head;
        while (k-- > 0)
        {
            q = q.next;
        }
        while (q.next != null)
        {
            p = p.next;
            q = q.next;
        }
        ListNode start = p.next;
        p.next = null;
        q.next = head;
        return start;

    }
}