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
    private static final int INF = 1 << 29;

    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, int[]> sub = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, 1));
                if (node.right != null) {
                    stk.push(new Frame(node.right, 0));
                }
                if (node.left != null) {
                    stk.push(new Frame(node.left, 0));
                }
                continue;
            }
            int[] l = node.left == null ? new int[] {INF, 0, 0} : sub.get(node.left);
            int[] r = node.right == null ? new int[] {INF, 0, 0} : sub.get(node.right);
            int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
            int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
            int c = l[1] + r[1];
            sub.put(node, new int[] {a, b, c});
        }
        int[] ans = sub.get(root);
        return Math.min(ans[0], ans[1]);
    }
}
