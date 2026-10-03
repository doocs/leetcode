/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Map<Node, Integer> height = new HashMap<>();
        Deque<Node> nodes = new ArrayDeque<>();
        Deque<Integer> states = new ArrayDeque<>();
        nodes.push(root);
        states.push(0);
        while (!nodes.isEmpty()) {
            Node node = nodes.pop();
            int state = states.pop();
            if (state == 0) {
                nodes.push(node);
                states.push(1);
                List<Node> children = node.children;
                for (int i = children.size() - 1; i >= 0; --i) {
                    Node child = children.get(i);
                    if (child != null) {
                        nodes.push(child);
                        states.push(0);
                    }
                }
            } else {
                int m1 = 0, m2 = 0;
                for (Node child : node.children) {
                    int t = height.getOrDefault(child, 0);
                    if (t > m1) {
                        m2 = m1;
                        m1 = t;
                    } else if (t > m2) {
                        m2 = t;
                    }
                }
                ans = Math.max(ans, m1 + m2);
                height.put(node, m1 + 1);
            }
        }
        return ans;
    }
}
