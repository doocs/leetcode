class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        int len = lists.length;
        if (len == 1) {
            return lists[0];
        }
       
        // 合并前后两个链表，结果放在后一个链表位置上，依次循环下去
        for (int i = 0; i < len - 1; ++i) {
            lists[i + 1] = mergeTwoLists(lists[i], lists[i + 1]);
        }
        return lists[len - 1];
        
    }
    
    /**
     * 合并两个有序链表
     * @param l1 
     * @param l2
     * @return listNode
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}