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
    private Map<Long, Integer> cnt = new HashMap<>();
    private int target;

    public int pathSum(TreeNode root, int sum) {
        cnt.put(0L, 1);
        target = sum;
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, long s) {
        if (root == null) {
            return 0;
        }
        s += root.val;
        int ans = cnt.getOrDefault(s - target, 0);
        cnt.merge(s, 1, Integer::sum);
        ans += dfs(root.left, s);
        ans += dfs(root.right, s);
        cnt.merge(s, -1, Integer::sum);
        return ans;
    }
}