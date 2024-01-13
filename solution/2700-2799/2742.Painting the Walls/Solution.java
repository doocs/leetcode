class Solution {
    private int n;
    private int[] cost;
    private int[] time;
    private Integer[][] f;

    public int paintWalls(int[] cost, int[] time) {
        n = cost.length;
        this.cost = cost;
        this.time = time;
        f = new Integer[n][n << 1 | 1];
        return dfs(0, n);
    }

    private int dfs(int i, int j) {
        if (n - i <= j - n) {
            return 0;
        }
        if (i >= n) {
            return 1 << 30;
        }
        if (f[i][j] == null) {
            f[i][j] = Math.min(dfs(i + 1, j + time[i]) + cost[i], dfs(i + 1, j - 1));
        }
        return f[i][j];
    }
}