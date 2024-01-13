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
    private List<Integer> s = new ArrayList<>();

    public int maxLevelSum(TreeNode root) {
        dfs(root, 0);
        int mx = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (mx < s.get(i)) {
                mx = s.get(i);
                ans = i + 1;
            }
        }
        return ans;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (i == s.size()) {
            s.add(root.val);
        } else {
            s.set(i, s.get(i) + root.val);
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
}