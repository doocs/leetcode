/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> cache = new HashSet<>();
        // 初始化参数
        ListNode pre = head, cur = pre.next;
        cache.add(head.val);

        while (cur != null) {
            if (cache.contains(cur.val)) {
                pre.next = cur.next;
                cur = pre.next;
            } else {
                cache.add(cur.val);
                pre = cur;
                cur = cur.next;
            }
        }
        
        return head;
    }
}