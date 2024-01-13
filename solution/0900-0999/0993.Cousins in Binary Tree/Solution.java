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
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode p1 = null, p2 = null;
        int d1 = 0, d2 = 0;
        Deque<TreeNode[]> q = new ArrayDeque<>();
        q.offer(new TreeNode[] {root, null});
        int d = 0;
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                var p = q.poll();
                TreeNode node = p[0], fa = p[1];
                if (node.val == x) {
                    p1 = fa;
                    d1 = d;
                }
                if (node.val == y) {
                    p2 = fa;
                    d2 = d;
                }
                if (node.left != null) {
                    q.offer(new TreeNode[] {node.left, node});
                }
                if (node.right != null) {
                    q.offer(new TreeNode[] {node.right, node});
                }
            }
            ++d;
        }
        return p1 != p2 && d1 == d2;
    }
}