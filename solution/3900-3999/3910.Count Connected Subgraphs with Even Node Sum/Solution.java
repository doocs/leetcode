class Solution {
    private int vis;
    private int m;
    private List<Integer>[] g;

    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        m = (1 << n) - 1;
        int ans = 0;
        for (int sub = 1; sub <= m; sub++) {
            int s = 0;
            for (int i = 0; i < n; i++) {
                if (((sub >> i) & 1) == 1) {
                    s += nums[i];
                }
            }
            if (s % 2 != 0) {
                continue;
            }
            vis = m ^ sub;
            dfs(Integer.numberOfTrailingZeros(sub));
            if (vis == m) {
                ans++;
            }
        }
        return ans;
    }

    private void dfs(int u) {
        vis |= 1 << u;
        for (int v : g[u]) {
            if (((vis >> v) & 1) == 0) {
                dfs(v);
            }
        }
    }
}
