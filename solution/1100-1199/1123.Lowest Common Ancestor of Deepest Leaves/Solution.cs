/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val = 0, TreeNode left = null, TreeNode right = null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public TreeNode LcaDeepestLeaves(TreeNode root) {
        (TreeNode, int) Dfs(TreeNode root) {
            if (root == null) {
                return (null, 0);
            }
            
            var l = Dfs(root.left);
            var r = Dfs(root.right);
            int d1 = l.Item2;
            int d2 = r.Item2;

            if (d1 > d2) {
                return (l.Item1, d1 + 1);
            }
            if (d1 < d2) {
                return (r.Item1, d2 + 1);
            }
            return (root, d1 + 1);
        }

        return Dfs(root).Item1;
    }
}
