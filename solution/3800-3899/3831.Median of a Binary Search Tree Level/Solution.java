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
    private List<Integer> nums = new ArrayList<>();
    private int level;

    public int levelMedian(TreeNode root, int level) {
        this.level = level;
        dfs(root, 0);
        return nums.isEmpty() ? -1 : nums.get(nums.size() / 2);
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        dfs(root.left, i + 1);
        if (i == level) {
            nums.add(root.val);
        }
        dfs(root.right, i + 1);
    }
}
