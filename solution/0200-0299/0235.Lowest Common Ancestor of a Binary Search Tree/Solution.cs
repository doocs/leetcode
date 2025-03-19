/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    public TreeNode LowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val < Math.Min(p.val, q.val)) {
                root = root.right;
            } else if (root.val > Math.Max(p.val, q.val)) {
                root = root.left;
            } else {
                return root;
            }
        }
    }
}
