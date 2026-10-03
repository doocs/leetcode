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
    public int pseudoPalindromicPaths(TreeNode root) {
        int ans = 0;
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> masks = new ArrayDeque<>();
        if (root != null) {
            nodes.push(root);
            masks.push(0);
        }
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            int mask = masks.pop() ^ (1 << node.val);
            if (node.left == null && node.right == null) {
                if ((mask & (mask - 1)) == 0) {
                    ++ans;
                }
            } else {
                if (node.right != null) {
                    nodes.push(node.right);
                    masks.push(mask);
                }
                if (node.left != null) {
                    nodes.push(node.left);
                    masks.push(mask);
                }
            }
        }
        return ans;
    }
}
