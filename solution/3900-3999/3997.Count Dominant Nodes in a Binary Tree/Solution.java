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

    public int countDominantNodes(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int l = dfs(node.left);
        int r = dfs(node.right);
        int mx = Math.max(Math.max(l, r), node.val);
        if (mx == node.val) {
            ++ans;
        }
        return mx;
    }
}