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
        int[] p = new int[110];
        int[] d = new int[110];
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            while (n-- > 0) {
                TreeNode node = q.poll();
                d[node.val] = i;
                if (node.left != null) {
                    q.offer(node.left);
                    p[node.left.val] = node.val;
                }
                if (node.right != null) {
                    q.offer(node.right);
                    p[node.right.val] = node.val;
                }
            }
            ++i;
        }
        return p[x] != p[y] && d[x] == d[y];
    }
}