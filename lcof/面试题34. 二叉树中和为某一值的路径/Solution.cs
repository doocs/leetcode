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
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();

    public IList<IList<int>> PathSum(TreeNode root, int target) {
        dfs(root, target);
        return ans;
    }

    private void dfs(TreeNode root, int s) {
        if (root == null) {
            return;
        }
        t.Add(root.val);
        s -= root.val;
        if (root.left == null && root.right == null && s == 0) {
            ans.Add(new List<int>(t));
        }
        dfs(root.left, s);
        dfs(root.right, s);
        t.RemoveAt(t.Count - 1);
    }
}
