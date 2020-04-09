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
    private int t;
    private int res;
    public int kthLargest(TreeNode root, int k) {
        t = k;
        traverse(root);
        return res;
    }

    private void traverse(TreeNode node) {
        if (node != null) {
            traverse(node.right);
            --t;
            if (t == 0) {
                res = node.val;
                return;
            }
            traverse(node.left);
        }
    }
}