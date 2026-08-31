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

class Codec {
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        List<String> ans = new ArrayList<>();
        Deque<Node> q = new ArrayDeque<>();
        ans.add(String.valueOf(root.val));
        q.offer(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.children != null) {
                for (Node child : node.children) {
                    ans.add(String.valueOf(child.val));
                    q.offer(child);
                }
            }
            ans.add("#");
        }
        return String.join(",", ans);
    }

    public Node deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String[] vals = data.split(",");
        Node root = new Node(Integer.parseInt(vals[0]), new ArrayList<>());
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty()) {
            Node node = q.poll();
            while (!"#".equals(vals[i])) {
                Node child = new Node(Integer.parseInt(vals[i++]), new ArrayList<>());
                node.children.add(child);
                q.offer(child);
            }
            ++i;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
