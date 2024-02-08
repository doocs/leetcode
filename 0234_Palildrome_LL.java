 /*
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head.next == null){
            return true;
        }
        
        ListNode middleNode = getMiddleNode(head);
        ListNode head1 = head;
        ListNode head2 = middleNode.next; 
        middleNode.next = null; 
        head2 = reverseLinkedList(head2); 

        while(head1!=null && head2!=null){
            if(head1.val != head2.val){
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        return true;
    }

    public ListNode getMiddleNode(ListNode node){
        ListNode slow = node;
        ListNode fast = node.next;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode reverseLinkedList(ListNode node){
        if(node==null || node.next == null){
            return node;
        }

        ListNode newHead = reverseLinkedList(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }
}
