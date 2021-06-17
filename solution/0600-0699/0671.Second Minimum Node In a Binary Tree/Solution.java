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
    private int res;
    public int findSecondMinimumValue(TreeNode root) {
        res = -1;
        traverse(root, root.val);
        return res;
    }

    private void traverse(TreeNode root, int min) {
        if (root == null) {
            return;
        }
        traverse(root.left, min);
        traverse(root.right, min);
        if (root.val > min) {
            res = res == -1 ? root.val : Math.min(res, root.val);
        }
    }
}