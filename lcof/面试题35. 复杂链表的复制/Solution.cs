/*
// Definition for a Node.
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
}
*/

public class Solution {
    public Node CopyRandomList(Node head) {
        Dictionary<Node, Node> d = new Dictionary<Node, Node>();
        Node dummy = new Node(0);
        Node tail = dummy;
        for (Node cur = head; cur != null; cur = cur.next) {
            tail.next = new Node(cur.val);
            tail = tail.next;
            d[cur] = tail;
        }
        tail = dummy.next;
        for (Node cur = head; cur != null; cur = cur.next) {
            tail.random = cur.random == null ? null : d[cur.random];
            tail = tail.next;
        }
        return dummy.next;
    }
}
