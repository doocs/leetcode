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
    private TreeNode prev, first, second;

    public void RecoverTree(TreeNode root) {
        dfs(root);
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

    private void dfs(TreeNode root) {
        if (root != null)
        {
            dfs(root.left);
            if (prev != null)
            {
                if (first == null && prev.val > root.val)
                {
                    first = prev;
                }
                if (first != null && prev.val > root.val)
                {
                    second = root;
                }
            }
            prev = root;
            dfs(root.right);
        }
    }
}