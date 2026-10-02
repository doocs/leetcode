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
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> order = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            order.add(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Map<TreeNode, int[]> dp = new IdentityHashMap<>();
        for (int i = order.size() - 1; i >= 0; --i) {
            TreeNode node = order.get(i);
            int[] left = node.left == null ? new int[2] : dp.get(node.left);
            int[] right = node.right == null ? new int[2] : dp.get(node.right);
            dp.put(node, new int[] {node.val + left[1] + right[1], Math.max(left[0], left[1]) + Math.max(right[0], right[1])});
        }
        int[] ans = dp.get(root);
        return Math.max(ans[0], ans[1]);
    }
}
