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
    private int k;

    public int maxValue(TreeNode root, int k) {
        this.k = k;
        return Arrays.stream(dfs(root)).max().getAsInt();
    }

    private int[] dfs(TreeNode root) {
        int[] ans = new int[k + 1];
        if (root == null) {
            return ans;
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        ans[0] = Arrays.stream(l).max().getAsInt() + Arrays.stream(r).max().getAsInt();
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < k - i; ++j) {
                ans[i + j + 1] = Math.max(ans[i + j + 1], root.val + l[i] + r[j]);
            }
        }
        return ans;
    }
}