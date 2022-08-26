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

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode root, int l, int r) {
        if (root == null) {
            return;
        }
        ans = Math.max(ans, Math.max(l, r));
        dfs(root.left, r + 1, 0);
        dfs(root.right, 0, l + 1);
    }
}