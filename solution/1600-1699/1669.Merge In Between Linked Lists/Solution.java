/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode p = list1, q = list1;
        for (int i = 0; i < a - 1; ++i) {
            p = p.next;
        }
        for (int i = 0; i < b + 1; ++i) {
            q = q.next;
        }
        ListNode t = list2;
        while (t.next != null) {
            t = t.next;
        }
        t.next = q;
        p.next = list2;
        return list1;
    }
}