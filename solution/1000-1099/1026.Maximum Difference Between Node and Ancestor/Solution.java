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
    private int ans;

    public int maxAncestorDiff(TreeNode root) {
        ans = 0;
        dfs(root, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int mx, int mi) {
        if (root == null) {
            return;
        }
        int t = Math.max(Math.abs(root.val - mx), Math.abs(root.val - mi));
        ans = Math.max(ans, t);
        mx = Math.max(mx, root.val);
        mi = Math.min(mi, root.val);
        dfs(root.left, mx, mi);
        dfs(root.right, mx, mi);
    }
}