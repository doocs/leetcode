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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root)[0];
    }

    private TreeNode[] dfs(TreeNode root) {
        if (root == null) {
            return new TreeNode[]{null, new TreeNode(0)};
        }
        TreeNode[] left = dfs(root.left);
        TreeNode[] right = dfs(root.right);
        int d1 = left[1].val, d2 = right[1].val;
        if (d1 > d2) {
            return new TreeNode[]{left[0], new TreeNode(d1 + 1)};
        }
        if (d1 < d2) {
            return new TreeNode[]{right[0], new TreeNode(d2 + 1)};
        }
        return new TreeNode[]{root, new TreeNode(d1 + 1)};
    }
}