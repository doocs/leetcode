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
    public int maxLevelSum(TreeNode root) {
        int[] ans = new int[]{Integer.MIN_VALUE, 0};
        int l = 0;
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            ++l;
            int s = 0;
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.pollFirst();
                s += node.val;
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            if (s > ans[0]) {
                ans[0] = s;
                ans[1] = l;
            }
        }
        return ans[1];
    }
}