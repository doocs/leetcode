public class Solution {
    private int mx;
    private int cnt;
    private TreeNode prev;
    private List<int> res;

    public int[] FindMode(TreeNode root) {
        res = new List<int>();
        Dfs(root);
        int[] ans = new int[res.Count];
        for (int i = 0; i < res.Count; ++i) {
            ans[i] = res[i];
        }
        return ans;
    }

    private void Dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Dfs(root.left);
        cnt = prev != null && prev.val == root.val ? cnt + 1 : 1;
        if (cnt > mx) {
            res = new List<int>(new int[] { root.val });
            mx = cnt;
        } else if (cnt == mx) {
            res.Add(root.val);
        }
        prev = root;
        Dfs(root.right);
    }
}
