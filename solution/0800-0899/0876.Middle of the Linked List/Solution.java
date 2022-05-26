public ListNode middleNode(ListNode head) {
    ListNode low = head, first = head;
    while (first != null && first.next != null) {
        low = low.next;
        first = first.next.next;
    }
    return low;
}