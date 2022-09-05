class Solution {
    int ans = 0;
    public int pathSum(TreeNode root, int sum) {
        traverse(root, sum);
        return ans;
    }

    void traverse(TreeNode root, int sum) {
        if (root == null) return;
        ans += dfs(root, sum, 0);
        traverse(root.left, sum);
        traverse(root.right, sum);
    }

    // check if sum of path is sum.
    int dfs(TreeNode root, int sum, int cur) {
        if (root == null) return 0;
        cur += root.val;
        int res = 0;
        if (cur == sum) res++;
        res += dfs(root.left, sum, cur);
        res += dfs(root.right, sum, cur);
        return res;
    }
}