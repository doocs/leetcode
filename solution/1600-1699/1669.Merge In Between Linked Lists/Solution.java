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
        ListNode p = list1;
        for (int i = 0; i < a - 1; ++i) {
            p = p.next;
        }
        ListNode q = list1;
        for (int i = 0; i < b + 1; ++i) {
            q = q.next;
        }
        p.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = q;
        return list1;
    }
}