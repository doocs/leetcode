class Solution {
    private int n;
    private int[][] edges;
    private int k;

    public int minCost(int n, int[][] edges, int k) {
        this.n = n;
        this.edges = edges;
        this.k = k;
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int l = 0, r = edges.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return check(l) ? edges[l][2] : -1;
    }

    private boolean check(int idx) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i <= idx; ++i) {
            int u = edges[i][0], v = edges[i][1];
            g[u].add(v);
            g[v].add(u);
        }
        List<Integer> q = new ArrayList<>();
        q.add(0);
        int dist = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;
        while (!q.isEmpty()) {
            List<Integer> nq = new ArrayList<>();
            for (int u : q) {
                if (u == n - 1) {
                    return dist <= k;
                }
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nq.add(v);
                    }
                }
            }
            q = nq;
            ++dist;
        }
        return false;
    }
}
