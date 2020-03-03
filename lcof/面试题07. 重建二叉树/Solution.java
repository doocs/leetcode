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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder == null || preorder.length == 0 || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if (s1 > e1 || s2 > e2) {
            return null;
        }
        int index = findIndex(inorder, s2, e2, preorder[s1]);
        TreeNode tree = new TreeNode(preorder[s1]);
        tree.left = buildTree(preorder, s1 + 1, index + s1 - s2, inorder, s2, index - 1);
        tree.right = buildTree(preorder, index + s1 - s2 + 1, e1, inorder, index + 1, e2);
        return tree;
    }

    public int findIndex(int[] order, int s, int e, int val) {
        for (int i = s; i <= e; ++i) {
            if (order[i] == val) {
                return i;
            }
        }
        return -1;
    }
}