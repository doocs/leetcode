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
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return -1001;
        }

        int ans = -1001;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<TreeNode> postorder = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            postorder.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Map<TreeNode, Integer> gains = new IdentityHashMap<>();
        while (!postorder.isEmpty()) {
            TreeNode node = postorder.pop();
            int left = node.left == null ? 0 : Math.max(0, gains.get(node.left));
            int right = node.right == null ? 0 : Math.max(0, gains.get(node.right));
            ans = Math.max(ans, node.val + left + right);
            gains.put(node, node.val + Math.max(left, right));
        }
        return ans;
    }
}
