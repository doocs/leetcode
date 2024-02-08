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
        Deque<TreeNode[]> q = new ArrayDeque<>();
        q.offer(new TreeNode[] {root, null});
        int d1 = 0, d2 = 0;
        TreeNode p1 = null, p2 = null;
        for (int depth = 0; !q.isEmpty(); ++depth) {
            for (int n = q.size(); n > 0; --n) {
                TreeNode[] t = q.poll();
                TreeNode node = t[0], parent = t[1];
                if (node.val == x) {
                    d1 = depth;
                    p1 = parent;
                } else if (node.val == y) {
                    d2 = depth;
                    p2 = parent;
                }
                if (node.left != null) {
                    q.offer(new TreeNode[] {node.left, node});
                }
                if (node.right != null) {
                    q.offer(new TreeNode[] {node.right, node});
                }
            }
        }
        return p1 != p2 && d1 == d2;
    }
}