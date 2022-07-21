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
    List<IList<int>> res;
    List<int> path;

    public IList<IList<int>> PathSum(TreeNode root, int target) {
        res = new List<IList<int>>();
        path = new List<int>();
        if (root == null) {
            return res;
        }
        dfs(root, target);
        return res;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.Add(root.val);
        if (root.val == target && root.left is null && root.right is null) {
            res.Add(new List<int>(path));
        }
        dfs(root.left, target - root.val);
        dfs(root.right, target - root.val);
        path.RemoveAt(path.Count - 1);
    }
}