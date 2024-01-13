class Solution {
    public int minCostConnectPoints(int[][] points) {
        final int inf = 1 << 30;
        int n = points.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int t = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                g[i][j] = t;
                g[j][i] = t;
            }
        }
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(dist, inf);
        dist[0] = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = -1;
            for (int k = 0; k < n; ++k) {
                if (!vis[k] && (j == -1 || dist[k] < dist[j])) {
                    j = k;
                }
            }
            vis[j] = true;
            ans += dist[j];
            for (int k = 0; k < n; ++k) {
                if (!vis[k]) {
                    dist[k] = Math.min(dist[k], g[j][k]);
                }
            }
        }
        return ans;
    }
}