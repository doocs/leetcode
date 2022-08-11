/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<TreeNode>[] f = new List[21];

    public List<TreeNode> allPossibleFBT(int n) {
        return dfs(n);
    }

    private List<TreeNode> dfs(int n) {
        if (f[n] != null) {
            return f[n];
        }
        if (n == 1) {
            return Collections.singletonList(new TreeNode());
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            int j = n - i - 1;
            for (TreeNode left : dfs(i)) {
                for (TreeNode right : dfs(j)) {
                    res.add(new TreeNode(0, left, right));
                }
            }
        }
        f[n] = res;
        return res;
    }
}