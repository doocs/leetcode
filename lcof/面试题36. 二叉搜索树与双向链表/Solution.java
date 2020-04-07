/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    private Node lastNode;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        lastNode = new Node(-1);
        Node head = lastNode;
        dfs(root);
        head = head.right;
        head.left = lastNode;
        lastNode.right = head;
        return head;
    }

    private void dfs(Node cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        cur.left = lastNode;
        if (lastNode != null) {
            lastNode.right = cur;
        }
        lastNode = cur;
        dfs(cur.right);
    }
}