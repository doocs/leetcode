class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            // 快指针每次循环走两步，慢指针走一步
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null || fast.next == null) {
                return slow;
            }
        }
        return  null;
    }
}