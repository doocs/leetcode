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
    public int CountNodes(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth > rightDepth)
        {
            return (1 << rightDepth) + CountNodes(root.left);
        }
        return (1 << leftDepth) + CountNodes(root.right);
    }

    private int depth(TreeNode root) {
        int res = 0;
        while (root != null)
        {
            ++res;
            root = root.left;
        }
        return res;
    }
}