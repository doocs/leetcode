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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode p = new TreeNode(0, root, null);
        dfs(root, p, target);
        return p.left;
    }

    private void dfs(TreeNode root, TreeNode prev, int target) {
        if (root == null) {
            return;
        }
        dfs(root.left, root, target);
        dfs(root.right, root, target);
        if (root.left == null && root.right == null && root.val == target) {
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
    }
}