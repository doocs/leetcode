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
    public int bfs(TreeNode root, int max, int min) {
        if (root == null) {
            return 0;
        }
        int res = Math.max(max - root.val, root.val - min);
        int mx = Math.max(root.val, max);
        int mn = Math.min(root.val, min);
        res = Math.max(res, bfs(root.left, mx, mn));
        res = Math.max(res, bfs(root.right, mx, mn));
        return res;
    }

    public int maxAncestorDiff(TreeNode root) {
        return bfs(root, root.val, root.val);
    }
}
