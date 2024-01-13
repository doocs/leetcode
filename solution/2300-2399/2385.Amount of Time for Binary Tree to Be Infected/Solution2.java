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
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root);
        return dfs(start, -1);
    }

    private int dfs(int i, int fa) {
        int ans = 0;
        for (int j : g.getOrDefault(i, Collections.emptyList())) {
            if (j != fa) {
                ans = Math.max(ans, 1 + dfs(j, i));
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            g.computeIfAbsent(root.left.val, k -> new ArrayList<>()).add(root.val);
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.left.val);
        }
        if (root.right != null) {
            g.computeIfAbsent(root.right.val, k -> new ArrayList<>()).add(root.val);
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.right.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}