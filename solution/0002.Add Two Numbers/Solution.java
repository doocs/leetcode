class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int quotient = 0;
        while (l1 != null || l2 != null || quotient != 0) {
            int t = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + quotient;
            quotient = t / 10;
            ListNode node = new ListNode(t % 10);
            cur.next = node;
            cur = node;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return res.next;
    }
}