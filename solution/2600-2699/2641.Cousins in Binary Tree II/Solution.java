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
    private List<Integer> s = new ArrayList<>();

    public TreeNode replaceValueInTree(TreeNode root) {
        dfs1(root, 0);
        root.val = 0;
        dfs2(root, 1);
        return root;
    }

    private void dfs1(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (s.size() <= d) {
            s.add(0);
        }
        s.set(d, s.get(d) + root.val);
        dfs1(root.left, d + 1);
        dfs1(root.right, d + 1);
    }

    private void dfs2(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        int l = root.left == null ? 0 : root.left.val;
        int r = root.right == null ? 0 : root.right.val;
        if (root.left != null) {
            root.left.val = s.get(d) - l - r;
        }
        if (root.right != null) {
            root.right.val = s.get(d) - l - r;
        }
        dfs2(root.left, d + 1);
        dfs2(root.right, d + 1);
    }
}