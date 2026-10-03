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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> vis = new HashSet<>();
        Deque<TreeNode> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(root);
        }
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            if (vis.contains(k - node.val)) {
                return true;
            }
            vis.add(node.val);
            if (node.right != null) {
                stk.push(node.right);
            }
            if (node.left != null) {
                stk.push(node.left);
            }
        }
        return false;
    }
}
