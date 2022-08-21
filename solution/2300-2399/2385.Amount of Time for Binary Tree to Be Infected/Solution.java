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
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root);
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        q.offer(start);
        int ans = -1;
        while (!q.isEmpty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                int i = q.pollFirst();
                vis.add(i);
                if (g.containsKey(i)) {
                    for (int j : g.get(i)) {
                        if (!vis.contains(j)) {
                            q.offer(j);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.left.val);
            g.computeIfAbsent(root.left.val, k -> new ArrayList<>()).add(root.val);
        }
        if (root.right != null) {
            g.computeIfAbsent(root.val, k -> new ArrayList<>()).add(root.right.val);
            g.computeIfAbsent(root.right.val, k -> new ArrayList<>()).add(root.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}