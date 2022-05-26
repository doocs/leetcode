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
    private List<Integer> res;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        res = new ArrayList<>();

        // root
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // left boundary
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            t = t.left == null ? t.right : t.left;
        }

        // leaves
        addLeaves(root);

        // right boundary(reverse order)
        Deque<Integer> s = new ArrayDeque<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.offer(t.val);
            }
            t = t.right == null ? t.left : t.right;
        }
        while (!s.isEmpty()) {
            res.add(s.pollLast());
        }

        // output
        return res;
    }

    private void addLeaves(TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        if (root.left != null) {
            addLeaves(root.left);
        }
        if (root.right != null) {
            addLeaves(root.right);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}