/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int[] LevelOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Queue<TreeNode> q = new Queue<TreeNode>();
        q.Enqueue(root);
        List<int> ans = new List<int>();
        while (q.Count != 0) {
            int x = q.Count;
            for (int i = 0; i < x; i++) {
                TreeNode node = q.Dequeue();
                ans.Add(node.val);
                if (node.left != null) {
                    q.Enqueue(node.left);
                }
                if (node.right != null) {
                    q.Enqueue(node.right);
                }
            }
        }
        return ans.ToArray();
    }
}