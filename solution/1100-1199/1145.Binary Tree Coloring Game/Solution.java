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
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = dfs(root, x);
        int l = count(node.left);
        int r = count(node.right);
        int m = Math.max(Math.max(l, r), n - l - r - 1);
        return m > n - m;
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }

    private TreeNode dfs(TreeNode root, int x) {
        if (root == null || root.val == x) {
            return root;
        }
        TreeNode l = dfs(root.left, x);
        if (l != null) {
            return l;
        }
        return dfs(root.right, x);
    }
}