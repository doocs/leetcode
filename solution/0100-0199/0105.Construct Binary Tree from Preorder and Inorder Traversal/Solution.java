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
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2) {
        if (p1 > p2 || i1 > i2) return null;
        int rootVal = preorder[p1];
        int pos = find(inorder, rootVal, i1, i2);
        TreeNode root = new TreeNode(rootVal);
        root.left = pos == i1 ? null : buildTree(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1);
        root.right = pos == i2 ? null : buildTree(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2);
        return root;
    }

    private int find(int[] order, int val, int p, int q) {
        for (int i = p; i <= q; ++i) {
            if (order[i] == val) return i;
        }
        return -1;
    }
}