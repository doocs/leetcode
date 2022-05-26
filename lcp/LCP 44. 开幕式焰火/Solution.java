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
    private Set<Integer> s;

    public int numColor(TreeNode root) {
        s = new HashSet<>();
        dfs(root);
        return s.size();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        s.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}