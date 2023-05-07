class Solution {
    private int[] cost;
    private int n;
    private int ans;

    public int minIncrements(int n, int[] cost) {
        this.n = n;
        this.cost = cost;
        dfs(1);
        return ans;
    }

    private int dfs(int i) {
        if ((i << 1) > n) {
            return cost[i - 1];
        }
        int l = dfs(i << 1);
        int r = dfs(i << 1 | 1);
        ans += Math.max(l, r) - Math.min(l, r);
        return cost[i - 1] + Math.max(l, r);
    }
}