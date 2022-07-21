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
        int i = 0;
        while (q.Count > 0) {
            var tmp = new List<int>();
            int x = q.Count;
            for (int j = 0; j < x; j++) {
                var node = q.Dequeue();
                tmp.Add(node.val);
                if (node.left != null) {
                    q.Enqueue(node.left);
                }
                if (node.right != null) {
                    q.Enqueue(node.right);
                }
            }
            if (i % 2 == 1) {
                tmp.Reverse();
            }
            ans.Add(tmp);
            i += 1;
        }
        return ans;
    }
}