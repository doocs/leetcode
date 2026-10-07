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
        Map<Node, List<Node>> g = new HashMap<>();
        Set<Node> seen = new HashSet<>();
        Deque<Node> stk = new ArrayDeque<>();
        stk.push(root);
        seen.add(root);
        while (!stk.isEmpty()) {
            Node u = stk.pop();
            for (Node child : u.children) {
                if (child == null || !seen.add(child)) {
                    continue;
                }
                g.computeIfAbsent(u, k -> new ArrayList<>()).add(child);
                g.computeIfAbsent(child, k -> new ArrayList<>()).add(u);
                stk.push(child);
            }
        }
        Node[] nxt = new Node[] {root};
        farthest(g, root, nxt);
        return farthest(g, nxt[0], nxt);
    }

    private int farthest(Map<Node, List<Node>> g, Node start, Node[] nxt) {
        Set<Node> vis = new HashSet<>();
        Deque<Node> nodes = new ArrayDeque<>();
        Deque<Integer> dist = new ArrayDeque<>();
        nodes.push(start);
        dist.push(0);
        vis.add(start);
        int best = 0;
        nxt[0] = start;
        while (!nodes.isEmpty()) {
            Node u = nodes.pop();
            int t = dist.pop();
            if (t > best) {
                best = t;
                nxt[0] = u;
            }
            List<Node> vs = g.get(u);
            if (vs == null) {
                continue;
            }
            for (Node v : vs) {
                if (vis.add(v)) {
                    nodes.push(v);
                    dist.push(t + 1);
                }
            }
        }
        return best;
    }
}
