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
    public int widthOfBinaryTree(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> q = new ArrayDeque<>();
        q.offerLast(new Pair<>(root, 1));
        int ans = 0;
        while (!q.isEmpty()) {
            ans = Math.max(ans, q.peekLast().getValue() - q.peekFirst().getValue() + 1);
            for (int i = 0, n = q.size(); i < n; ++i) {
                Pair<TreeNode, Integer> node = q.pollFirst();
                if (node.getKey().left != null) {
                    q.offerLast(new Pair<>(node.getKey().left, node.getValue() * 2));
                }
                if (node.getKey().right != null) {
                    q.offerLast(new Pair<>(node.getKey().right, node.getValue() * 2 + 1));
                }
            }
        }
        return ans;
    }
}