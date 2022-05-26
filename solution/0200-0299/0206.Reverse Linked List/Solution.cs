public class Solution {
    public ListNode ReverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null)
        {
            var next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}