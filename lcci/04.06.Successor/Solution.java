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
    private TreeNode prev;
    private TreeNode p;
    private TreeNode ans;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        prev = null;
        ans = null;
        this.p = p;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev == p) {
            ans = root;
        }
        prev = root;
        dfs(root.right);
    }
}