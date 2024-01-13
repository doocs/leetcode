/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public bool IsValidBST(TreeNode root) {
        return dfs(root, long.MinValue, long.MaxValue);
    }

    public bool dfs(TreeNode root, long l, long r) {
        if (root == null) {
            return true;
        }
        if (root.val <= l || root.val >= r) {
            return false;
        }
        return dfs(root.left, l, root.val) && dfs(root.right, root.val, r);
    }
}
