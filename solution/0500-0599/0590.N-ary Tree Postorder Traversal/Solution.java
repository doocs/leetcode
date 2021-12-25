/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<Node> stk = new ArrayDeque<>();
        stk.offerLast(root);
        LinkedList<Integer> ans = new LinkedList<>();
        while (!stk.isEmpty()) {
            Node node = stk.pollLast();
            ans.addFirst(node.val);
            if (node.children != null) {
                for (Node child : node.children) {
                    stk.offerLast(child);
                }
            }
        }
        return ans;
    }
}