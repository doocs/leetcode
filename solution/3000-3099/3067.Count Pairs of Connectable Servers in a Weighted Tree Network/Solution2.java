class Solution {
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }
        int[] ans = new int[n];
        for (int a = 0; a < n; ++a) {
            int s = 0;
            for (var e : g[a]) {
                int t = count(g, e[0], a, e[1], signalSpeed);
                ans[a] += s * t;
                s += t;
            }
        }
        return ans;
    }

    private int count(List<int[]>[] g, int start, int fa, int dist, int signalSpeed) {
        int cnt = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {start, fa, dist});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int a = cur[0], parent = cur[1], ws = cur[2];
            if (ws % signalSpeed == 0) {
                ++cnt;
            }
            for (var e : g[a]) {
                int b = e[0], w = e[1];
                if (b != parent) {
                    stk.push(new int[] {b, a, ws + w});
                }
            }
        }
        return cnt;
    }
}
