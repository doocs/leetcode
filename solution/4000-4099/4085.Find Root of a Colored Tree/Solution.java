class Solution {
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();
        int[] d = new int[128];
        d['W'] = 0;
        d['R'] = 1;
        d['B'] = 2;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int ans = -1;
        for (int u = 0; u < n; ++u) {
            List<Integer> vs = g[u];
            if (vs.size() > 3) {
                return -1;
            }
            int[] cnt = new int[3];
            for (int v : vs) {
                ++cnt[d[colors.charAt(v)]];
            }
            int c = d[colors.charAt(u)];
            if (cnt[c] > 0 || cnt[(c + 2) % 3] > 1) {
                return -1;
            }
            if (vs.size() < 3 && cnt[(c + 2) % 3] == 0) {
                ans = u;
            }
        }
        return ans;
    }
}
