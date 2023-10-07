/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public double minimalExecTime(TreeNode root) {
        return dfs(root)[1];
    }

    private double[] dfs(TreeNode root) {
        if (root == null) {
            return new double[] {0, 0};
        }
        double[] left = dfs(root.left);
        double[] right = dfs(root.right);
        double s = left[0] + right[0] + root.val;
        double t = Math.max(Math.max(left[1], right[1]), (left[0] + right[0]) / 2) + root.val;
        return new double[] {s, t};
    }
}