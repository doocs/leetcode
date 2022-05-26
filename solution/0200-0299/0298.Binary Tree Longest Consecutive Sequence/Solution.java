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

    public int longestConsecutive(TreeNode root) {
        ans = 1;
        dfs(root, null, 1);
        return ans;
    }

    private void dfs(TreeNode root, TreeNode p, int t) {
        if (root == null) {
            return;
        }
        t = p != null && p.val + 1 == root.val ? t + 1 : 1;
        ans = Math.max(ans, t);
        dfs(root.left, root, t);
        dfs(root.right, root, t);
    }
}