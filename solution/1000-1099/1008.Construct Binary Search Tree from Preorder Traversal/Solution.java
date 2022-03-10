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
    public TreeNode bstFromPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int i, int j) {
        if (i > j || i >= preorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        int left = i + 1, right = j + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preorder[mid] > preorder[i]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        root.left = dfs(preorder, i + 1, left - 1);
        root.right = dfs(preorder, left, j);
        return root;
    }
}