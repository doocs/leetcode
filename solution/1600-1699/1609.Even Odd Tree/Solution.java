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
    public boolean isEvenOddTree(TreeNode root) {
        boolean even = true;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int prev = even ? 0 : 1000000;
            for (int n = q.size(); n > 0; --n) {
                root = q.pollFirst();
                if (even && (root.val % 2 == 0 || prev >= root.val)) {
                    return false;
                }
                if (!even && (root.val % 2 == 1 || prev <= root.val)) {
                    return false;
                }
                prev = root.val;
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
            even = !even;
        }
        return true;
    }
}