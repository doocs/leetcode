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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> m = new HashMap<>();
        Set<Integer> vis = new HashSet<>();
        for (int[] d : descriptions) {
            int p = d[0], c = d[1], isLeft = d[2];
            if (!m.containsKey(p)) {
                m.put(p, new TreeNode(p));
            }
            if (!m.containsKey(c)) {
                m.put(c, new TreeNode(c));
            }
            if (isLeft == 1) {
                m.get(p).left = m.get(c);
            } else {
                m.get(p).right = m.get(c);
            }
            vis.add(c);
        }
        for (Map.Entry<Integer, TreeNode> entry : m.entrySet()) {
            if (!vis.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}