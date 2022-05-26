class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(-1);
        ListNode rightDummy = new ListNode(-1);

        ListNode leftCur = leftDummy;
        ListNode rightCur = rightDummy;

        while (head != null) {
            if (head.val < x) {
                leftCur.next = head;
                leftCur = leftCur.next;
            } else {
                rightCur.next = head;
                rightCur = rightCur.next;
            }
            head = head.next;
        }

        leftCur.next = rightDummy.next;
        rightCur.next = null;
        return leftDummy.next;

    }
}