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
    private int cur;
    private int res;

    public int kthLargest(TreeNode root, int k) {
        cur = k;
        res = 0;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.right);
        --cur;
        if (cur == 0) {
            res = root.val;
            return;
        }
        inorder(root.left);
    }
}