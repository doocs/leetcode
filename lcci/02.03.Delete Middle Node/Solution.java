/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode p = node.next; //p is the node under delete.
        node.next = p.next;
        p.next = null;   // put the node under delete's next to null;
        p = null;    //put the node under delete to null to let JVM release the memory;
    }
}
