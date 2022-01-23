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
    private Map<Integer, Integer> indexes = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; ++i) {
            indexes.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = indexes.get(v);
        TreeNode root = new TreeNode(v);
        root.left = dfs(preorder, inorder, i + 1, j, k - j);
        root.right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
        return root;
    }
}