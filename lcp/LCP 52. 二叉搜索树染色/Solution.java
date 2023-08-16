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
    private TreeSet<Integer> ts = new TreeSet<>();

    public int getNumber(TreeNode root, int[][] ops) {
        dfs(root);
        int ans = 0;
        for (int i = ops.length - 1; i >= 0; --i) {
            int t = ops[i][0];
            int x = ops[i][1], y = ops[i][2];
            Integer ceiling = ts.ceiling(x);
            while (ceiling != null && ceiling <= y) {
                ts.remove(ceiling);
                ceiling = ts.ceiling(x);
                ans += t;
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        ts.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}