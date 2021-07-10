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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth > rightDepth) {
            return (1 << rightDepth) + countNodes(root.left);
        }
        return (1 << leftDepth) + countNodes(root.right);
    }

    private int depth(TreeNode root) {
        int res = 0;
        while (root != null) {
            ++res;
            root = root.left;
        }
        return res;
    }
}