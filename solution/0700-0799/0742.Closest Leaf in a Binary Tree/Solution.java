/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Map<TreeNode, List<TreeNode>> g = new HashMap<>();

    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root, null);
        Deque<TreeNode> q = new LinkedList<>();
        Set<TreeNode> vis = new HashSet<>(q.size());
        for (TreeNode node : g.keySet()) {
            if (node != null && node.val == k) {
                vis.add(node);
                q.offer(node);
                break;
            }
        }
        while (true) {
            TreeNode node = q.poll();
            if (node != null) {
                if (node.left == node.right) {
                    return node.val;
                }
                for (TreeNode nxt : g.get(node)) {
                    if (vis.add(nxt)) {
                        q.offer(nxt);
                    }
                }
            }
        }
    }

    private void dfs(TreeNode root, TreeNode fa) {
        if (root != null) {
            g.computeIfAbsent(root, k -> new ArrayList<>()).add(fa);
            g.computeIfAbsent(fa, k -> new ArrayList<>()).add(root);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
}