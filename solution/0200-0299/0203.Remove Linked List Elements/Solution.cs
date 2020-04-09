public class Solution {
    public ListNode RemoveElements(ListNode head, int val) {
        ListNode newHead = null;
        ListNode newTail = null;
        var current = head;
        while (current != null)
        {
            if (current.val != val)
            {
                if (newHead == null)
                {
                    newHead = newTail = current;
                }
                else
                {
                    newTail.next = current;
                    newTail = current;
                }
            }
            current = current.next;
        }
        if (newTail != null) newTail.next = null;
        return newHead;
    }
}