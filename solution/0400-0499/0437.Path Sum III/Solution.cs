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
    public int PathSum(TreeNode root, int targetSum) {
        Dictionary<long, int> cnt = new Dictionary<long, int>();
        
        int Dfs(TreeNode node, long s) {
            if (node == null) {
                return 0;
            }
            s += node.val;
            int ans = cnt.GetValueOrDefault(s - targetSum, 0);
            cnt[s] = cnt.GetValueOrDefault(s, 0) + 1;
            ans += Dfs(node.left, s);
            ans += Dfs(node.right, s);
            cnt[s]--;
            return ans;
        }
        
        cnt[0] = 1;
        return Dfs(root, 0);
    }
}
