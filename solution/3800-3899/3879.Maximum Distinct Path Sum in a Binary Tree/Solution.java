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
    Map<TreeNode, List<TreeNode>> g = new HashMap<>();
    Set<Integer> vis = new HashSet<>();

    public int maxSum(TreeNode root) {
        dfs(root, null);

        int ans = Integer.MIN_VALUE;
        for (TreeNode node : g.keySet()) {
            ans = Math.max(ans, dfs2(node));
            vis.clear();
        }
        return ans;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null) {
            return;
        }
        g.computeIfAbsent(node, k -> new ArrayList<>());
        g.get(node).add(p);
        g.get(node).add(node.left);
        g.get(node).add(node.right);

        dfs(node.left, node);
        dfs(node.right, node);
    }

    private int dfs2(TreeNode node) {
        if (node == null || vis.contains(node.val)) {
            return 0;
        }
        vis.add(node.val);
        int res = node.val;
        int best = 0;
        for (TreeNode nxt : g.getOrDefault(node, Collections.emptyList())) {
            best = Math.max(best, dfs2(nxt));
        }
        vis.remove(node.val);
        return res + best;
    }
}
