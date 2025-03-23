class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        g = new List[n];
        Set<Integer>[] ss = new Set[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        Arrays.setAll(ss, i -> new HashSet<>());
        for (int i = 0; i < n; ++i) {
            for (int x : properties[i]) {
                ss[i].add(x);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int cnt = 0;
                for (int x : ss[i]) {
                    if (ss[j].contains(x)) {
                        ++cnt;
                    }
                }
                if (cnt >= k) {
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }

        int ans = 0;
        vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }

    private void dfs(int i) {
        vis[i] = true;
        for (int j : g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    }
}
