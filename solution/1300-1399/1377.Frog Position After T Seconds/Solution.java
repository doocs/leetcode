class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<Pair<Integer, Double>> q = new ArrayDeque<>();
        q.offer(new Pair<>(1, 1.0));
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        while (!q.isEmpty() && t >= 0) {
            for (int k = q.size(); k > 0; --k) {
                Pair<Integer, Double> x = q.poll();
                int u = x.getKey();
                double p = x.getValue();
                List<Integer> nxt = new ArrayList<>();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        nxt.add(v);
                        vis[v] = true;
                    }
                }
                if (u == target && (nxt.isEmpty() || t == 0)) {
                    return p;
                }
                for (int v : nxt) {
                    q.offer(new Pair<>(v, p / nxt.size()));
                }
            }
            --t;
        }
        return 0;
    }
}