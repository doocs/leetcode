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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> rootA = new ArrayList<>();
        List<Integer> rootB = new ArrayList<>();
        dfs(root1, rootA);
        dfs(root2, rootB);
        return rootA.equals(rootB);
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                res.add(root.val);
            }
            dfs(root.left, res);
            dfs(root.right, res);
        }
    }
}