/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    private TreeNode target;

    public TreeNode GetTargetCopy(TreeNode original, TreeNode cloned, TreeNode target) {
        this.target = target;
        return dfs(original, cloned);
    }

    private TreeNode dfs(TreeNode original, TreeNode cloned) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = dfs(original.left, cloned.left);
        return left == null ? dfs(original.right, cloned.right) : left;
    }
}
