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
        int i;
        int state;

        Frame(TreeNode node, int i, int state) {
            this.node = node;
            this.i = i;
            this.state = state;
        }
    }

    public int levelMedian(TreeNode root, int level) {
        List<Integer> nums = new ArrayList<>();
        if (root == null) {
            return -1;
        }
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, cur.i, 1));
                if (node.left != null) {
                    stk.push(new Frame(node.left, cur.i + 1, 0));
                }
                continue;
            }
            if (cur.i == level) {
                nums.add(node.val);
            }
            if (node.right != null) {
                stk.push(new Frame(node.right, cur.i + 1, 0));
            }
        }
        return nums.isEmpty() ? -1 : nums.get(nums.size() / 2);
    }
}
