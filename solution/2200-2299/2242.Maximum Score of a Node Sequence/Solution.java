class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            g[i].sort((a, b) -> scores[b] - scores[a]);
            g[i] = g[i].subList(0, Math.min(3, g[i].size()));
        }
        int ans = -1;
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            for (int c : g[a]) {
                for (int d : g[b]) {
                    if (c != b && c != d && a != d) {
                        int t = scores[a] + scores[b] + scores[c] + scores[d];
                        ans = Math.max(ans, t);
                    }
                }
            }
        }
        return ans;
    }
}