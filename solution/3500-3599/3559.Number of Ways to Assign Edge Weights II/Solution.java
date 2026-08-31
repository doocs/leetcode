class Solution {
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        int m = 32 - Integer.numberOfLeadingZeros(n);
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[][] f = new int[n + 1][m];
        int[] p = new int[n + 1];
        int[] depth = new int[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while (!q.isEmpty()) {
            int i = q.poll();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (int j : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    q.offer(j);
                }
            }
        }
        final int mod = (int) 1e9 + 7;
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; ++i) {
            pow2[i] = (int) (pow2[i - 1] * 2L % mod);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int u = queries[i][0], v = queries[i][1];
            int x = u, y = v;
            if (depth[x] < depth[y]) {
                int t = x;
                x = y;
                y = t;
            }
            for (int j = m - 1; j >= 0; --j) {
                if (depth[x] - depth[y] >= (1 << j)) {
                    x = f[x][j];
                }
            }
            for (int j = m - 1; j >= 0; --j) {
                if (f[x][j] != f[y][j]) {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if (x != y) {
                x = p[x];
            }
            int d = depth[u] + depth[v] - 2 * depth[x];
            ans[i] = d == 0 ? 0 : pow2[d - 1];
        }
        return ans;
    }
}
