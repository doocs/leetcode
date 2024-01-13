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
    public IList<IList<int>> LevelOrder(TreeNode root) {
        var ans = new List<IList<int>>();
        if (root == null) {
            return ans;
        }
        var q = new Queue<TreeNode>();
        q.Enqueue(root);
        while (q.Count > 0) {
            var t = new List<int>();
            for (int n = q.Count; n > 0; --n) {
                var node = q.Dequeue();
                t.Add(node.val);
                if (node.left != null) {
                    q.Enqueue(node.left);
                }
                if (node.right != null) {
                    q.Enqueue(node.right);
                }
            }
            if (ans.Count % 2 == 1) {
                t.Reverse();
            }
            ans.Add(t);
        }
        return ans;
    }
}
