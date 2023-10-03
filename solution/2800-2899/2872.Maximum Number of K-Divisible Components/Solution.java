class Solution {
    private int ans;
    private List<Integer>[] g;
    private int[] values;
    private int k;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        this.values = values;
        this.k = k;
        dfs(0, -1);
        return ans;
    }

    private long dfs(int i, int fa) {
        long s = values[i];
        for (int j : g[i]) {
            if (j != fa) {
                s += dfs(j, i);
            }
        }
        ans += s % k == 0 ? 1 : 0;
        return s;
    }
}