class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        for (; i < m - 1; ++i) {
            pre = pre.next;
        }
        
        ListNode head2 = pre;
        pre = pre.next;
        ListNode cur = pre.next;
        for (; i < n - 1; ++i) {
            pre.next = cur.next;
            cur.next = head2.next;
            head2.next = cur;
            cur = pre.next;
        }
        
        return dummy.next;
        
        
    }
}