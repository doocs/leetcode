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
    public List<Long> zigzagLevelSum(TreeNode root) {
        List<Long> ans = new ArrayList<>();
        List<TreeNode> q = new ArrayList<>();
        q.add(root);
        boolean left = true;
        while (!q.isEmpty()) {
            List<TreeNode> nq = new ArrayList<>();
            for (TreeNode node : q) {
                if (node.left != null) {
                    nq.add(node.left);
                }
                if (node.right != null) {
                    nq.add(node.right);
                }
            }
            int m = q.size();
            long s = 0;
            for (int i = 0; i < m; i++) {
                TreeNode node = left ? q.get(i) : q.get(m - i - 1);
                TreeNode child = left ? node.left : node.right;
                if (child == null) {
                    break;
                }
                s += node.val;
            }
            ans.add(s);
            left = !left;
            q = nq;
        }
        return ans;
    }
}
