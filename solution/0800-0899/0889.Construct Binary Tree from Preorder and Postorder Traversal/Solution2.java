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
    private Map<Integer, Integer> pos = new HashMap<>();
    private int[] preorder;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        for (int i = 0; i < postorder.length; ++i) {
            pos.put(postorder[i], i);
        }
        return dfs(0, 0, preorder.length);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        if (n == 1) {
            return root;
        }
        int k = pos.get(preorder[i + 1]);
        int m = k - j + 1;
        root.left = dfs(i + 1, j, m);
        root.right = dfs(i + m + 1, k + 1, n - m - 1);
        return root;
    }
}