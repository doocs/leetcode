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
        return dfs(root).getKey();
    }

    private Pair<TreeNode, Integer> dfs(TreeNode root) {
        if (root == null) {
            return new Pair<>(null, 0);
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int ld = l.getValue(), rd = r.getValue();
        if (ld > rd) {
            return new Pair<>(l.getKey(), ld + 1);
        }
        if (ld < rd) {
            return new Pair<>(r.getKey(), rd + 1);
        }
        return new Pair<>(root, ld + 1);
    }
}
