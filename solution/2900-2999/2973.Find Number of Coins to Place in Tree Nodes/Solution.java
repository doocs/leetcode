class Solution {
    private int[] cost;
    private List<Integer>[] g;
    private long[] ans;

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        this.cost = cost;
        ans = new long[n];
        g = new List[n];
        Arrays.fill(ans, 1);
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ans;
    }

    private List<Integer> dfs(int a, int fa) {
        List<Integer> res = new ArrayList<>();
        res.add(cost[a]);
        for (int b : g[a]) {
            if (b != fa) {
                res.addAll(dfs(b, a));
            }
        }
        Collections.sort(res);
        int m = res.size();
        if (m >= 3) {
            long x = (long) res.get(m - 1) * res.get(m - 2) * res.get(m - 3);
            long y = (long) res.get(0) * res.get(1) * res.get(m - 1);
            ans[a] = Math.max(0, Math.max(x, y));
        }
        if (m >= 5) {
            res = List.of(res.get(0), res.get(1), res.get(m - 3), res.get(m - 2), res.get(m - 1));
        }
        return res;
    }
}