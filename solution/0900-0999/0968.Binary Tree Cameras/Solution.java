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
    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1 << 29, 0, 0};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
        int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
        int c = l[1] + r[1];
        return new int[] {a, b, c};
    }
}