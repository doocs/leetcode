class Solution {
    private List<Integer>[] g;
    private Set<Long> gs = new HashSet<>();
    private int ans;
    private int k;
    private int cnt;
    private int n;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (var e : guesses) {
            int a = e[0], b = e[1];
            gs.add(f(a, b));
        }
        dfs1(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs1(int i, int fa) {
        for (int j : g[i]) {
            if (j != fa) {
                cnt += gs.contains(f(i, j)) ? 1 : 0;
                dfs1(j, i);
            }
        }
    }

    private void dfs2(int i, int fa) {
        ans += cnt >= k ? 1 : 0;
        for (int j : g[i]) {
            if (j != fa) {
                boolean a = gs.contains(f(i, j));
                boolean b = gs.contains(f(j, i));
                cnt -= a ? 1 : 0;
                cnt += b ? 1 : 0;
                dfs2(j, i);
                cnt -= b ? 1 : 0;
                cnt += a ? 1 : 0;
            }
        }
    }

    private long f(int i, int j) {
        return 1L * i * n + j;
    }
}