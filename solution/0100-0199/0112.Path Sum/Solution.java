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
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return hasPathSum(root, 0, sum);
    }

    public static boolean hasPathSum(TreeNode root, int sum, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum + root.val == target;
        }

        sum += root.val;
        return hasPathSum(root.left, sum, target) || hasPathSum(root.right, sum, target);
    }
}