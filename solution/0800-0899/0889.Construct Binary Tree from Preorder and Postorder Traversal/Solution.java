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
        return dfs(0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode dfs(int a, int b, int c, int d) {
        if (a > b) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[a]);
        if (a == b) {
            return root;
        }
        int i = pos.get(preorder[a + 1]);
        int m = i - c + 1;
        root.left = dfs(a + 1, a + m, c, i);
        root.right = dfs(a + m + 1, b, i + 1, d - 1);
        return root;
    }
}