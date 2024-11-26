class Solution {
    public double maxProbability(
        int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<Pair<Integer, Double>>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            var e = edges[i];
            int a = e[0], b = e[1];
            double p = succProb[i];
            g[a].add(new Pair<>(b, p));
            g[b].add(new Pair<>(a, p));
        }
        double[] dist = new double[n];
        dist[start_node] = 1;
        PriorityQueue<Pair<Integer, Double>> pq
            = new PriorityQueue<>(Comparator.comparingDouble(p -> - p.getValue()));
        pq.offer(new Pair<>(start_node, 1.0));
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int a = p.getKey();
            double w = p.getValue();
            if (dist[a] > w) {
                continue;
            }
            for (var e : g[a]) {
                int b = e.getKey();
                double pab = e.getValue();
                double wab = w * pab;
                if (wab > dist[b]) {
                    dist[b] = wab;
                    pq.offer(new Pair<>(b, wab));
                }
            }
        }
        return dist[end_node];
    }
}
