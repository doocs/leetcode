class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        int[] backup = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            System.arraycopy(dist, 0, backup, 0, n);
            for (int[] t : times) {
                int u = t[0] - 1, v = t[1] - 1, w = t[2];
                dist[v] = Math.min(dist[v], backup[u] + w);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}