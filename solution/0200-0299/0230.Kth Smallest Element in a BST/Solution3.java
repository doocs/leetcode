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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack();
        int res = 0;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (k-- == 0 || stack.isEmpty()) break;
            TreeNode node = stack.pop();
            res = node.val;
            root = node.right;
        }
        return res;
    }
}