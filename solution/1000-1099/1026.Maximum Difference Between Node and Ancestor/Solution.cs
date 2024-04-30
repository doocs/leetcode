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
    private int ans;

    public int MaxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int mi, int mx) {
        if (root == null) {
            return;
        }
        int x = Math.Max(Math.Abs(mi - root.val), Math.Abs(mx - root.val));
        ans = Math.Max(ans, x);
        mi = Math.Min(mi, root.val);
        mx = Math.Max(mx, root.val);
        dfs(root.left, mi, mx);
        dfs(root.right, mi, mx);
    }
}