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
    private List<TreeNode>[] f;

    public IList<TreeNode> AllPossibleFBT(int n) {
        f = new List<TreeNode>[n + 1];
        return Dfs(n);
    }

    private IList<TreeNode> Dfs(int n) {
        if (f[n] != null) {
            return f[n];
        }
        
        if (n == 1) {
            return new List<TreeNode> { new TreeNode() };
        }
        
        List<TreeNode> ans = new List<TreeNode>();
        for (int i = 0; i < n - 1; ++i) {
            int j = n - 1 - i;
            foreach (var left in Dfs(i)) {
                foreach (var right in Dfs(j)) {
                    ans.Add(new TreeNode(0, left, right));
                }
            }
        }
        f[n] = ans;
        return ans;
    }
}