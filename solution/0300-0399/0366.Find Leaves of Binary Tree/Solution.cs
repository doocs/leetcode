/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public IList<IList<int>> FindLeaves(TreeNode root) {
        var ans = new List<IList<int>>();

        int Dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int l = Dfs(node.left);
            int r = Dfs(node.right);
            int h = Math.Max(l, r);
            if (ans.Count == h) {
                ans.Add(new List<int>());
            }
            ans[h].Add(node.val);
            return h + 1;
        }

        Dfs(root);
        return ans;
    }
}