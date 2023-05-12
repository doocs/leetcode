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
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode a = list, b = list;
        while (b.next != list && b.next.next != list) {
            a = a.next;
            b = b.next.next;
        }
        if (b.next != list) {
            b = b.next;
        }
        ListNode list2 = a.next;
        b.next = list2;
        a.next = list;
        return new ListNode[] {list, list2};
    }
}