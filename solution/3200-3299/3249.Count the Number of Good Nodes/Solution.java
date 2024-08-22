class Solution {
    private int ans;
    private List<Integer>[] g;

    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ans;
    }

    private int dfs(int a, int fa) {
        int pre = -1, cnt = 1, ok = 1;
        for (int b : g[a]) {
            if (b != fa) {
                int cur = dfs(b, a);
                cnt += cur;
                if (pre < 0) {
                    pre = cur;
                } else if (pre != cur) {
                    ok = 0;
                }
            }
        }
        ans += ok;
        return cnt;
    }
}
