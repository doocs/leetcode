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
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int equalToDescendants(TreeNode root) {
        int ans = 0;
        Map<TreeNode, Long> sub = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, 0));
        }
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
            long l = node.left == null ? 0 : sub.get(node.left);
            long r = node.right == null ? 0 : sub.get(node.right);
            if (l + r == node.val) {
                ++ans;
            }
            sub.put(node, node.val + l + r);
        }
        return ans;
    }
}
