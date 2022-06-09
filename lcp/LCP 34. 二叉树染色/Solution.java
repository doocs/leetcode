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
    public int maxValue(TreeNode root, int k) {
        int[] t = dfs(root, k);
        int ans = 0;
        for (int v : t) {
            ans = Math.max(ans, v);
        }
        return ans;
    }

    private int[] dfs(TreeNode root, int k) {
        int[] ans = new int[k + 1];
        if (root == null) {
            return ans;
        }
        int[] l = dfs(root.left, k);
        int[] r = dfs(root.right, k);
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < k - i; ++j) {
                ans[i + j + 1] = Math.max(ans[i + j + 1], l[i] + r[j] + root.val);
            }
        }
        for (int i = 0; i <= k; ++i) {
            for (int j = 0; j <= k; ++j) {
                ans[0] = Math.max(ans[0], l[i] + r[j]);
            }
        }
        return ans;
    }
}
