class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair<Integer, Double>>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].add(new Pair<>(b, s));
            g[b].add(new Pair<>(a, s));
        }
        double[] d = new double[n];
        d[start] = 1.0;
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        vis[start] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            vis[i] = false;
            for (Pair<Integer, Double> ne : g[i]) {
                int j = ne.getKey();
                double s = ne.getValue();
                if (d[j] < d[i] * s) {
                    d[j] = d[i] * s;
                    if (!vis[j]) {
                        q.offer(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return d[end];
    }
}