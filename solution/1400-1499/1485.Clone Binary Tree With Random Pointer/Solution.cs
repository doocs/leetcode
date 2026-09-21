/*
// Definition for a Node.
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node random;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _random) {
        val = _val;
        left = _left;
        right = _right;
        random = _random;
    }
}
*/

public class Solution {
    public NodeCopy CopyRandomBinaryTree(Node root) {
        var seen = new Dictionary<Node, NodeCopy>();
        NodeCopy Dfs(Node root) {
            if (root == null) {
                return null;
            }
            if (seen.ContainsKey(root)) {
                return seen[root];
            }
            var copy = new NodeCopy(root.val);
            seen[root] = copy;
            copy.left = Dfs(root.left);
            copy.right = Dfs(root.right);
            copy.random = Dfs(root.random);
            return copy;
        }
        return Dfs(root);
    }
}
