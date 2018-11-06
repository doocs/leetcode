class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        if (head.val == head.next.val) {
            if (head.next.next == null) {
                return null;
            }
            if (head.val == head.next.next.val) {
                return deleteDuplicates(head.next);
            }
            return deleteDuplicates(head.next.next);
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }
}