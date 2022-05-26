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
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) return -1;
        int limit = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val != root.val) {
                limit = Math.min(limit, node.val - root.val);
            }
            if (node.left != null) {
                stack.push(node.left);
                stack.push(node.right);
            }
        }
        return limit == Integer.MAX_VALUE ? -1 : root.val + limit;
    }
}