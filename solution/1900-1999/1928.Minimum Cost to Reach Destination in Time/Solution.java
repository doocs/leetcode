class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int m = maxTime, n = passingFees.length;
        int[][] f = new int[m + 1][n];
        final int inf = 1 << 30;
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = passingFees[0];
        for (int i = 1; i <= m; ++i) {
            for (var e : edges) {
                int x = e[0], y = e[1], t = e[2];
                if (t <= i) {
                    f[i][x] = Math.min(f[i][x], f[i - t][y] + passingFees[x]);
                    f[i][y] = Math.min(f[i][y], f[i - t][x] + passingFees[y]);
                }
            }
        }
        int ans = inf;
        for (int i = 0; i <= m; ++i) {
            ans = Math.min(ans, f[i][n - 1]);
        }
        return ans == inf ? -1 : ans;
    }
}
