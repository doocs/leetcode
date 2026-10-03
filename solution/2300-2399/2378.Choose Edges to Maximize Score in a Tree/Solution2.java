class Solution {
    public long maxScore(int[][] edges) {
        int n = edges.length;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p].add(new int[] {i, w});
        }
        long[][] down = new long[n][2];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                stk.push(new int[] {i, 1});
                for (int[] nxt : g[i]) {
                    stk.push(new int[] {nxt[0], 0});
                }
            } else {
                long a = 0, b = 0, t = 0;
                for (int[] nxt : g[i]) {
                    int j = nxt[0], w = nxt[1];
                    long x = down[j][0], y = down[j][1];
                    a += y;
                    b += y;
                    t = Math.max(t, x - y + w);
                }
                b += t;
                down[i][0] = a;
                down[i][1] = b;
            }
        }
        return down[0][1];
    }
}
