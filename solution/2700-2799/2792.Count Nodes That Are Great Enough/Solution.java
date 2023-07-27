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
    private int ans;
    private int k;

    public int countGreatEnoughNodes(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    private PriorityQueue<Integer> dfs(TreeNode root) {
        if (root == null) {
            return new PriorityQueue<>(Comparator.reverseOrder());
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        for (int x : r) {
            l.offer(x);
            if (l.size() > k) {
                l.poll();
            }
        }
        if (l.size() == k && l.peek() < root.val) {
            ++ans;
        }
        l.offer(root.val);
        if (l.size() > k) {
            l.poll();
        }
        return l;
    }
}