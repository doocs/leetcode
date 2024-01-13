/*
// Definition for a Node.
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
*/

public class Solution {
    public Node Connect(Node root) {
        if (root == null) {
            return null;
        }
        var q = new Queue<Node>();
        q.Enqueue(root);
        while (q.Count > 0) {
            Node p = null;
            for (int i = q.Count; i > 0; --i) {
                var node = q.Dequeue();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.Enqueue(node.left);
                }
                if (node.right != null) {
                    q.Enqueue(node.right);
                }
            }
        }
        return root;
    }
}
