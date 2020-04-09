class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        int len = 0;
        ListNode cur = head;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        ListNode left = head,right = head;
        for(int i = 0; i < len/2 - 1; i++) right = right.next;
        ListNode leftTail = right;
        right = right.next;
        leftTail.next = null;
        left = sortList(left);
        right = sortList(right);
        ListNode dummy = new ListNode(0);
        cur = dummy;
        while(left != null && right != null){
            if(left.val < right.val){
                cur.next = left;
                left = left.next;
            }else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if(left != null) cur.next = left;
        else if(right != null) cur.next = right;
        else cur.next = null;
        return dummy.next;
    }
}