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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stk = new ArrayDeque<>();
        for (int v : nums) {
            TreeNode node = new TreeNode(v);
            TreeNode last = null;
            while (!stk.isEmpty() && stk.peek().val < v) {
                last = stk.pop();
            }
            node.left = last;
            if (!stk.isEmpty()) {
                stk.peek().right = node;
            }
            stk.push(node);
        }
        return stk.getLast();
    }
}