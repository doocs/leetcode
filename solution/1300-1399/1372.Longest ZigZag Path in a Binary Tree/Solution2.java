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
    private static class Frame {
        TreeNode node;
        int l;
        int r;

        Frame(TreeNode node, int l, int r) {
            this.node = node;
            this.l = l;
            this.r = r;
        }
    }

    public int longestZigZag(TreeNode root) {
        int ans = 0;
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, 0, 0));
        }
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            ans = Math.max(ans, Math.max(cur.l, cur.r));
            if (cur.node.right != null) {
                stk.push(new Frame(cur.node.right, 0, cur.l + 1));
            }
            if (cur.node.left != null) {
                stk.push(new Frame(cur.node.left, cur.r + 1, 0));
            }
        }
        return ans;
    }
}
