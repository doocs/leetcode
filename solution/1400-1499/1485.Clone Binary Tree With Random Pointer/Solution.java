/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    private Map<Node, NodeCopy> mp;

    public NodeCopy copyRandomBinaryTree(Node root) {
        mp = new HashMap<>();
        return dfs(root);    
    }

    private NodeCopy dfs(Node root) {
        if (root == null) {
            return null;
        }
        if (mp.containsKey(root)) {
            return mp.get(root);
        }
        NodeCopy copy = new NodeCopy(root.val);
        mp.put(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    }
}