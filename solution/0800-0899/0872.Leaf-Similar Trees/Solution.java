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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = dfs(root1);
        List<Integer> l2 = dfs(root2);
        return l1.equals(l2);
    }

    private List<Integer> dfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = dfs(root.left);
        ans.addAll(dfs(root.right));
        if (ans.isEmpty()) {
            ans.add(root.val);
        }
        return ans;
    }
}