public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        if (k < 2) return head;
        ListNode newHead = null;
        ListNode newTail = null;
        var current = head;
        while (current != null)
        {
            ListNode segmentHead = null;
            ListNode segmentTail = null;
            var count = 0;
            while (current != null && count < k)
            {
                if (segmentHead == null) segmentHead = current;
                segmentTail = current;
                current = current.next;
                ++count;
            }
            segmentTail.next = null;
            if (count == k)
            {
                segmentTail = segmentHead;
                segmentHead = ReverseList(segmentHead);
            }
            if (newHead == null)
            {
                newHead = segmentHead;
                newTail = segmentTail;
            }
            else
            {
                newTail.next = segmentHead;
                newTail = segmentTail;
            }
        }
        return newHead;
    }

    private ListNode ReverseList(ListNode head)
    {
        var current = head;
        head = null;
        while (current != null)
        {
            var next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        return head;
    }
}