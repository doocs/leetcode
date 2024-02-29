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
    private Map<Integer, Integer> d = new HashMap<>();

    public boolean isEvenOddTree(TreeNode root) {
        return dfs(root, 0);
    }

    private boolean dfs(TreeNode root, int i) {
        if (root == null) {
            return true;
        }
        boolean even = i % 2 == 0;
        int prev = d.getOrDefault(i, even ? 0 : 1000001);
        if (even && (root.val % 2 == 0 || prev >= root.val)) {
            return false;
        }
        if (!even && (root.val % 2 == 1 || prev <= root.val)) {
            return false;
        }
        d.put(i, root.val);
        return dfs(root.left, i + 1) && dfs(root.right, i + 1);
    }
}