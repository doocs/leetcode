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
    private List<int> nums = new List<int>();

    public IList<IList<int>> ClosestNodes(TreeNode root, IList<int> queries) {
        Dfs(root);
        List<IList<int>> ans = new List<IList<int>>();
        foreach (int x in queries) {
            int i = nums.BinarySearch(x + 1);
            int j = nums.BinarySearch(x);
            i = i < 0 ? -i - 2 : i - 1;
            j = j < 0 ? -j - 1 : j;
            int mi = i >= 0 && i < nums.Count ? nums[i] : -1;
            int mx = j >= 0 && j < nums.Count ? nums[j] : -1;
            ans.Add(new List<int> {mi, mx});
        }
        return ans;
    }

    private void Dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Dfs(root.left);
        nums.Add(root.val);
        Dfs(root.right);
    }
}