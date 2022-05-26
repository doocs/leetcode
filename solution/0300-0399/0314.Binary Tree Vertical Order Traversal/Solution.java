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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> offsetVals = new TreeMap<>();
        Map<TreeNode, Integer> nodeOffsets = new HashMap<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        nodeOffsets.put(root, 0);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int offset = nodeOffsets.get(node);
            offsetVals.computeIfAbsent(offset, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                q.offer(node.left);
                nodeOffsets.put(node.left, offset - 1);
            }
            if (node.right != null) {
                q.offer(node.right);
                nodeOffsets.put(node.right, offset + 1);
            }
        }
        return new ArrayList<>(offsetVals.values());
    }
}