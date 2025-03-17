/*
// Definition for a Node.
public class Node {
    public int val;
    public IList<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new List<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new List<Node>();
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

public class Solution {
    public Node CloneGraph(Node node) {
        var g = new Dictionary<Node, Node>();
        Node Dfs(Node n) {
            if (n == null) {
                return null;
            }
            if (g.ContainsKey(n)) {
                return g[n];
            }
            var cloned = new Node(n.val);
            g[n] = cloned;
            foreach (var neighbor in n.neighbors) {
                cloned.neighbors.Add(Dfs(neighbor));
            }
            return cloned;
        }
        return Dfs(node);
    }
}
