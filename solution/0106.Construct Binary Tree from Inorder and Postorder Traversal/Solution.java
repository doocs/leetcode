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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        int n = inorder.length;
        return n > 0 ? buildTree(inorder, 0, n - 1, postorder, 0, n - 1) : null;
    }
    
    private TreeNode buildTree(int[] inorder, int s1, int e1, int[] postorder, int s2, int e2) {
        TreeNode node = new TreeNode(postorder[e2]);
        if (s2 == e2 && s1 == e1) {
            return node;
        }
        
        int p = s1;
        while (inorder[p] != postorder[e2]) {
            ++p;
            if (p > e1) {
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        
        node.left = p > s1 ? buildTree(inorder, s1, p - 1, postorder, s2, p - 1 + s2 - s1) : null;
        node.right = p < e1 ? buildTree(inorder, p + 1, e1, postorder, p + s2 - s1, e2 - 1) : null;
        return node;
        
    }
}