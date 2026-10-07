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
    public int goodNodes(TreeNode root) {
        int ans = 0;
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> limits = new ArrayDeque<>();
        if (root != null) {
            nodes.push(root);
            limits.push(-100000);
        }
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            int mx = limits.pop();
            if (mx <= node.val) {
                ++ans;
                mx = node.val;
            }
            if (node.right != null) {
                nodes.push(node.right);
                limits.push(mx);
            }
            if (node.left != null) {
                nodes.push(node.left);
                limits.push(mx);
            }
        }
        return ans;
    }
}
