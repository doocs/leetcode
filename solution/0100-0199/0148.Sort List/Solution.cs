public class Solution {
    public ListNode SortList(ListNode head) {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode p1 = null;
        var p2 = head;
        while (p2 != null)
        {
            p2 = p2.next;
            if (p2 != null)
            {
                p2 = p2.next;
                p1 = p1 == null ? head : p1.next;
            }
        }

        p2 = p1.next;
        p1.next = null;
        p1 = head;
        p1 = SortList(p1);
        p2 = SortList(p2);
        ListNode newHead = null;
        ListNode newTail = null;
        while (p1 != null || p2 != null)
        {
            if (p1 == null || (p2 != null && p1.val > p2.val))
            {
                var temp = p1;
                p1 = p2;
                p2 = temp;
            }
            var next = p1;
            p1 = p1.next;
            next.next = null;
            if (newTail == null)
            {
                newHead = newTail = next;
            }
            else
            {
                newTail.next = next;
                newTail = next;
            }
        }
        return newHead;
    }
}