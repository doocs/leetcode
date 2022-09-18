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
    public TreeNode reverseOddLevels(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 0;
        while (!q.isEmpty()) {
            List<TreeNode> t = new ArrayList<>();
            for (int n = q.size(); n > 0; --n) {
                TreeNode node = q.pollFirst();
                if (i % 2 == 1) {
                    t.add(node);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (!t.isEmpty()) {
                int j = 0, k = t.size() - 1;
                for (; j < k; ++j, --k) {
                    int v = t.get(j).val;
                    t.get(j).val = t.get(k).val;
                    t.get(k).val = v;
                }
            }
            ++i;
        }
        return root;
    }
}