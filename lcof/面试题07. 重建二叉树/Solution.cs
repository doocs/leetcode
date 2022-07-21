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
    public TreeNode BuildTree(int[] preorder, int[] inorder) {
        if (preorder.Length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int idx = Array.IndexOf(inorder, root.val);
        root.left = BuildTree(preorder[1..(index+1)], inorder[0..idx]);
        root.right = BuildTree(preorder[(index+1)..], inorder[(idx+1)..]);
        return root;
    }
}
