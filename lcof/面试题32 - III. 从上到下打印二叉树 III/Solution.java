/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int cnt = 1;
        int level = 0;
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            ++level;
            int num = 0;
            List<Integer> t = new ArrayList<>();
            while (cnt-- > 0) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                    ++num;
                }
                if (node.right != null) {
                    q.offer(node.right);
                    ++num;
                }
            }
            if ((level & 1) == 0) {
                Collections.reverse(t);
            }
            res.add(t);
            cnt = num;
        }
        return res;
    }
}