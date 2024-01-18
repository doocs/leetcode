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
    private int x, y;
    private TreeNode p1, p2;
    private int d1, d2;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, null, 0);
        return p1 != p2 && d1 == d2;
    }

    private void dfs(TreeNode root, TreeNode p, int d) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            p1 = p;
            d1 = d;
        }
        if (root.val == y) {
            p2 = p;
            d2 = d;
        }
        dfs(root.left, root, d + 1);
        dfs(root.right, root, d + 1);
    }
}