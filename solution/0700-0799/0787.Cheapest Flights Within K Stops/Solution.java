class Solution {
    private int[][] memo;
    private int[][] g;
    private int dst;
    private static final int INF = (int) 1e6;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        n += 10;
        memo = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(memo[i], -1);
        }
        g = new int[n][n];
        for (int[] e : flights) {
            g[e[0]][e[1]] = e[2];
        }
        this.dst = dst;
        int ans = dfs(src, k + 1);
        return ans >= INF ? -1 : ans;
    }

    private int dfs(int u, int k) {
        if (memo[u][k] != -1) {
            return memo[u][k];
        }
        if (u == dst) {
            return 0;
        }
        if (k <= 0) {
            return INF;
        }
        int ans = INF;
        for (int v = 0; v < g[u].length; ++v) {
            if (g[u][v] > 0) {
                ans = Math.min(ans, dfs(v, k - 1) + g[u][v]);
            }
        }
        memo[u][k] = ans;
        return ans;
    }
}