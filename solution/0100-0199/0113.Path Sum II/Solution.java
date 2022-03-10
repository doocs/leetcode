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
    private List<List<Integer>> ans;
    private List<Integer> t;
    private int target;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        t = new ArrayList<>();
        target = targetSum;
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int s) {
        if (root == null) {
            return;
        }
        t.add(root.val);
        s += root.val;
        if (root.left == null && root.right == null) {
            if (s == target) {
                ans.add(new ArrayList<>(t));
            }
        }
        dfs(root.left, s);
        dfs(root.right, s);
        t.remove(t.size() - 1);
    }
}