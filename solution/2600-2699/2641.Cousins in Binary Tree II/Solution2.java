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
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        List<TreeNode> q = List.of(root);
        while (!q.isEmpty()) {
            List<TreeNode> t = new ArrayList<>();
            int s = 0;
            for (TreeNode node : q) {
                if (node.left != null) {
                    t.add(node.left);
                    s += node.left.val;
                }
                if (node.right != null) {
                    t.add(node.right);
                    s += node.right.val;
                }
            }
            for (TreeNode node : q) {
                int sub = (node.left == null ? 0 : node.left.val)
                    + (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = s - sub;
                }
                if (node.right != null) {
                    node.right.val = s - sub;
                }
            }
            q = t;
        }
        return root;
    }
}