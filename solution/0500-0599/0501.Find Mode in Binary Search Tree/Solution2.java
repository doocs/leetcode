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

    public int[] findMode(TreeNode root) {
        Integer prev = null;
        int mx = 0;
        int cnt = 0;
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            Deque<Frame> stk = new ArrayDeque<>();
            stk.push(new Frame(root, 0));
            while (!stk.isEmpty()) {
                Frame cur = stk.pop();
                TreeNode node = cur.node;
                if (cur.state == 0) {
                    stk.push(new Frame(node, 1));
                    if (node.left != null) {
                        stk.push(new Frame(node.left, 0));
                    }
                    continue;
                }
                cnt = prev != null && prev == node.val ? cnt + 1 : 1;
                if (cnt > mx) {
                    res = new ArrayList<>(Arrays.asList(node.val));
                    mx = cnt;
                } else if (cnt == mx) {
                    res.add(node.val);
                }
                prev = node.val;
                if (node.right != null) {
                    stk.push(new Frame(node.right, 0));
                }
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
