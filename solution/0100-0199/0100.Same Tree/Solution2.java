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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<TreeNode> q2 = new ArrayDeque<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            p = q1.poll();
            q = q2.poll();
            if (p.val != q.val) {
                return false;
            }
            TreeNode la = p.left, ra = p.right;
            TreeNode lb = q.left, rb = q.right;
            if ((la != null && lb == null) || (lb != null && la == null)) {
                return false;
            }
            if ((ra != null && rb == null) || (rb != null && ra == null)) {
                return false;
            }
            if (la != null) {
                q1.offer(la);
                q2.offer(lb);
            }
            if (ra != null) {
                q1.offer(ra);
                q2.offer(rb);
            }
        }
        return true;
    }
}