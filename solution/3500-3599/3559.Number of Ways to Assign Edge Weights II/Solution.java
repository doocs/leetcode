class Solution {
    static final int MOD = 1000000007;
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int lg = 17;
        while ((1 << lg) <= n) lg++;
        int[][] up = new int[lg][n + 1];
        int[] dep = new int[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        up[0][1] = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : g[u]) {
                if (v == up[0][u]) continue;
                dep[v] = dep[u] + 1;
                up[0][v] = u;
                q.offer(v);
            }
        }
        for (int j = 1; j < lg; j++) {
            for (int i = 1; i <= n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }
        int mx = 0;
        for (int[] x : queries) {
            int u = x[0];
            int v = x[1];
            int w = lca(u, v, up, dep, lg);
            int d = dep[u] + dep[v] - 2 * dep[w];
            mx = Math.max(mx, d);
        }
        long[] p = new long[mx + 1];
        p[0] = 1;
        for (int i = 1; i <= mx; i++) {
            p[i] = (p[i - 1] * 2) % MOD;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int w = lca(u, v, up, dep, lg);
            int d = dep[u] + dep[v] - 2 * dep[w];
            if (d == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) p[d - 1];
            }
        }
        return ans;
    }
    private int lca(int a, int b, int[][] up, int[] dep, int lg) {
        if (dep[a] < dep[b]) {
            int t = a;
            a = b;
            b = t;
        }
        int diff = dep[a] - dep[b];
        for (int i = 0; i < lg; i++) {
            if (((diff >> i) & 1) == 1) {
                a = up[i][a];
            }
        }
        if (a == b) return a;
        for (int i = lg - 1; i >= 0; i--) {
            if (up[i][a] != up[i][b]) {
                a = up[i][a];
                b = up[i][b];
            }
        }
        return up[0][a];
    }
}
