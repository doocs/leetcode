class Solution {
    public long maximizeSumOfWeights(int[][] edges, int k) {
        int n = edges.length + 1;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        long[] keep = new long[n];
        long[] reserve = new long[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int u = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {u, fa, 1});
                for (var e : g[u]) {
                    if (e[0] != fa) {
                        stk.push(new int[] {e[0], u, 0});
                    }
                }
            } else {
                long s = 0;
                List<Long> t = new ArrayList<>();
                for (var e : g[u]) {
                    int v = e[0], w = e[1];
                    if (v == fa) {
                        continue;
                    }
                    s += keep[v];
                    long d = w + reserve[v] - keep[v];
                    if (d > 0) {
                        t.add(d);
                    }
                }
                t.sort(Comparator.reverseOrder());
                for (int i = 0; i < Math.min(t.size(), k - 1); ++i) {
                    s += t.get(i);
                }
                reserve[u] = s;
                keep[u] = s + (t.size() >= k ? t.get(k - 1) : 0);
            }
        }
        return Math.max(keep[0], reserve[0]);
    }
}
