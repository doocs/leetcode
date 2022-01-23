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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0, n = q.size(); i < n; ++i) {
                TreeNode node = q.pollFirst();
                t.add(node.val);
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            ans.addFirst(t);
        }
        return ans;
    }
}