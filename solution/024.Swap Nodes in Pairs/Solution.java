class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head.next;
        ListNode p = head;
        ListNode q = head.next;
        
        while (q != null) {
            ListNode t = q.next;
            q.next = p;
            if (t == null || t.next == null) {
                p.next = t;
                break;
            }
            p.next = t.next;
            p = t;
            q = p.next;
        }
        
        return pre;
    }
}