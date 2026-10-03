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
        int n = nums.length;
        TreeNode root = null;
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(0, n - 1, null, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            if (cur.l > cur.r) {
                continue;
            }
            int i = cur.l;
            for (int j = cur.l; j <= cur.r; ++j) {
                if (nums[i] < nums[j]) {
                    i = j;
                }
            }
            TreeNode node = new TreeNode(nums[i]);
            if (cur.parent == null) {
                root = node;
            } else if (cur.side == 0) {
                cur.parent.left = node;
            } else {
                cur.parent.right = node;
            }
            stk.push(new Frame(i + 1, cur.r, node, 1));
            stk.push(new Frame(cur.l, i - 1, node, 0));
        }
        return root;
    }

    private static class Frame {
        int l, r, side;
        TreeNode parent;

        Frame(int l, int r, TreeNode parent, int side) {
            this.l = l;
            this.r = r;
            this.parent = parent;
            this.side = side;
        }
    }
}
