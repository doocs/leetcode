/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int n = preorder.length;
        return n > 0 ? buildTree(preorder, 0, n - 1, inorder, 0, n - 1) : null;
    }
    
    private TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder,  int s2, int e2) {
        TreeNode node = new TreeNode(preorder[s1]);                           
        if (s1 == e1 && s2 == e2) {
            return node;
        }

        int p = s2;
        while (inorder[p] != preorder[s1]) {
            ++p;
            if (p > e2) {
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        
        node.left = p > s2 ? buildTree(preorder, s1 + 1, s1 - s2 + p, inorder, s2, p - 1) : null;
        node.right = p < e2 ? buildTree(preorder, s1 - s2 + p + 1, e1, inorder, p + 1, e2) : null;
        return node;
    }
}