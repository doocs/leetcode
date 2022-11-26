class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<int[]> q = new LinkedList<>();
        q.offerLast(new int[] {1, 0});
        int[][] dist = new int[n + 1][2];
        for (int i = 0; i < n + 1; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[1][1] = 0;
        while (!q.isEmpty()) {
            int[] e = q.pollFirst();
            int u = e[0], d = e[1];
            for (int v : g[u]) {
                if (d + 1 < dist[v][0]) {
                    dist[v][0] = d + 1;
                    q.offerLast(new int[] {v, d + 1});
                } else if (dist[v][0] < d + 1 && d + 1 < dist[v][1]) {
                    dist[v][1] = d + 1;
                    if (v == n) {
                        break;
                    }
                    q.offerLast(new int[] {v, d + 1});
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < dist[n][1]; ++i) {
            ans += time;
            if (i < dist[n][1] - 1 && (ans / change) % 2 == 1) {
                ans = (ans + change) / change * change;
            }
        }
        return ans;
    }
}