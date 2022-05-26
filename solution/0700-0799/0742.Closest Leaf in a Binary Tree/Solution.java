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
    private Map<TreeNode, List<TreeNode>> g;

    public int findClosestLeaf(TreeNode root, int k) {
        g = new HashMap<>();
        dfs(root, null);
        Deque<TreeNode> q = new LinkedList<>();
        for (Map.Entry<TreeNode, List<TreeNode>> entry : g.entrySet()) {
            if (entry.getKey() != null && entry.getKey().val == k) {
                q.offer(entry.getKey());
                break;
            }
        }
        Set<TreeNode> seen = new HashSet<>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            seen.add(node);
            if (node != null) {
                if (node.left == null && node.right == null) {
                    return node.val;
                }
                for (TreeNode next : g.get(node)) {
                    if (!seen.contains(next)) {
                        q.offer(next);
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root != null) {
            g.computeIfAbsent(root, k -> new ArrayList<>()).add(p);
            g.computeIfAbsent(p, k -> new ArrayList<>()).add(root);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
}