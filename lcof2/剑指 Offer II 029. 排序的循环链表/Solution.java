/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node p = head;
        for (;;) {
            if (p.val <= insertVal && insertVal <= p.next.val
                || p.val > p.next.val && (insertVal <= p.next.val || insertVal >= p.val)
                || p.next == head) {
                node.next = p.next;
                p.next = node;
                break;
            }
            p = p.next;
        }
        return head;
    }
}
