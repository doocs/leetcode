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
    private int cnt;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        cnt = 0;
        dfs(root);
        return cnt;
    }

    private boolean dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            ++cnt;
            return true;
        }
        boolean res = true;
        if (root.left != null) {
            // exec dfs(root.left) first
            res = dfs(root.left) && res && root.val == root.left.val;
        }
        if (root.right != null) {
            // exec dfs(root.right) first
            res = dfs(root.right) && res && root.val == root.right.val;
        }
        if (res) {
            ++cnt;
        }
        return res;
    }
}