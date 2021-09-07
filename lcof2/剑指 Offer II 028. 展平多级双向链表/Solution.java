/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    private Node dummy = new Node();
    private Node tail = dummy;

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        preOrder(head);
        dummy.next.prev = null;
        return dummy.next;
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        Node child = node.child;
        tail.next = node;
        node.prev = tail;
        tail = tail.next;
        node.child = null;
        preOrder(child);
        preOrder(next);
    }
}
