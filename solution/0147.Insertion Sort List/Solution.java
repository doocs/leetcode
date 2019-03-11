/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) {
            return null;
        }
        return insertionOneNode(head, head);
    }

    private ListNode insertionOneNode(ListNode head, ListNode node) {
        if(head == null || node == null || node.next == null) {
            return head;
        }

        ListNode perNode = node;
        ListNode curNode = node.next;
        ListNode nextNode = curNode.next;

        if(node.val <= curNode.val) {
            return insertionOneNode(head, curNode);
        }
        else {
            node.next = nextNode;
        }

        ListNode pNode = new ListNode(0);
        pNode.next = head;
        head = pNode;
        while(pNode.next.val <= curNode.val) {
            pNode = pNode.next;
        }
        ListNode nNode = pNode.next;
        pNode.next = curNode;
        curNode.next = nNode;

        return insertionOneNode(head.next, perNode);
    }
}

