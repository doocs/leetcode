public class Solution {
    public ListNode DeleteDuplicates(ListNode head) {
        if (head == null) return null;
        var last = head;
        var current = head.next;
        while (current != null)
        {
            if (current.val != last.val)
            {
                last.next = current;
                last = current;
            }
            else
            {
                last.next = null;
            }
            current = current.next;
        }
        return head;
    }
}