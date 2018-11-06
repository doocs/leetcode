/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int len = 1;
        ListNode p = head;
        ListNode q = head;
        ListNode t = p.next;
        
        while (q.next != null) {
            ++len;
            q = q.next;
        }
        if (len == 1 || k % len == 0) {
            return head;
        }
        
        k %= len;
        
        // 右移 k 个位置，相当于左移 (len-k) 个位置
        k = len - k;
        
        for (int i = 0; i < k; ++i) {
            q.next = p;
            p.next = null;
            q = q.next;
            p = t;
            t = p.next;
        }
        
        return p;
    }
}