class Solution {
    private static final int INF = 0x3f3f3f3f;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        int[] backup = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        for (int i = 0; i < k + 1; ++i) {
            System.arraycopy(dist, 0, backup, 0, n);
            for (int[] e : flights) {
                int f = e[0], t = e[1], p = e[2];
                dist[t] = Math.min(dist[t], backup[f] + p);
            }
        }
        return dist[dst] == INF ? -1 : dist[dst];
    }
}