/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode dummy = pre, p = head;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
                break;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return dummy.next;
    }
}