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
    private int i;
    private boolean ok;
    private int[] voyage;
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        ok = true;
        dfs(root);
        return ok ? ans : List.of(-1);
    }

    private void dfs(TreeNode root) {
        if (root == null || !ok) {
            return;
        }
        if (root.val != voyage[i]) {
            ok = false;
            return;
        }
        ++i;
        if (root.left == null || root.left.val == voyage[i]) {
            dfs(root.left);
            dfs(root.right);
        } else {
            ans.add(root.val);
            dfs(root.right);
            dfs(root.left);
        }
    }
}