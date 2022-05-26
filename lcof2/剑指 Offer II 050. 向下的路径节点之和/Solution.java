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

    private final Map<Integer, Integer> preSum = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        preSum.put(0, 1);
        return dfs(root, 0, targetSum);
    }

    private int dfs(TreeNode node, int cur, int targetSum) {
        if (node == null) {
            return 0;
        }

        cur += node.val;
        int ret = preSum.getOrDefault(cur - targetSum, 0);

        preSum.merge(cur, 1, Integer::sum);
        ret += dfs(node.left, cur, targetSum);
        ret += dfs(node.right, cur, targetSum);
        preSum.merge(cur, -1, Integer::sum);

        return ret;
    }
}
