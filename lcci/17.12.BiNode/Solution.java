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
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        TreeNode left = convertBiNode(root.left);
        TreeNode right = convertBiNode(root.right);
        if (left == null) {
            root.right = right;
            return root;
        }
        TreeNode res = left;
        while (left != null && left.right != null) {
            left = left.right;
        }
        left.right = root;
        root.right = right;
        root.left = null;
        return res;
    }
}