public class Solution {
    private ListNode newHead;
    private ListNode last;
    private ListNode candidate;
    private int count;

    public ListNode DeleteDuplicates(ListNode head) {
        while (head != null)
        {
            if (candidate == null || candidate.val != head.val)
            {
                TryAppend();
                candidate = head;
                count = 1;
            }
            else
            {
                ++count;
            }

            head = head.next;
        }
        TryAppend();
        if (last != null) last.next = null;
        return newHead;
    }

    private void TryAppend()
    {
        if (count == 1)
        {
            if (newHead == null)
            {
                newHead = last = candidate;
            }
            else
            {
                last.next = candidate;
                last = last.next;
            }
        }
    }
}