class MCFGraph {
    static class Edge {
        int src, dst, cap, flow, cost;

        Edge(int src, int dst, int cap, int flow, int cost) {
            this.src = src;
            this.dst = dst;
            this.cap = cap;
            this.flow = flow;
            this.cost = cost;
        }
    }

    static class _Edge {
        int dst, cap, cost;
        _Edge rev;

        _Edge(int dst, int cap, int cost) {
            this.dst = dst;
            this.cap = cap;
            this.cost = cost;
            this.rev = null;
        }
    }

    private int n;
    private List<List<_Edge>> graph;
    private List<_Edge> edges;

    public MCFGraph(int n) {
        this.n = n;
        this.graph = new ArrayList<>();
        this.edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public int addEdge(int src, int dst, int cap, int cost) {
        assert (0 <= src && src < n);
        assert (0 <= dst && dst < n);
        assert (0 <= cap);

        int m = edges.size();
        _Edge e = new _Edge(dst, cap, cost);
        _Edge re = new _Edge(src, 0, -cost);
        e.rev = re;
        re.rev = e;

        graph.get(src).add(e);
        graph.get(dst).add(re);
        edges.add(e);
        return m;
    }

    public Edge getEdge(int i) {
        assert (0 <= i && i < edges.size());
        _Edge e = edges.get(i);
        _Edge re = e.rev;
        return new Edge(re.dst, e.dst, e.cap + re.cap, re.cap, e.cost);
    }

    public List<Edge> edges() {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            result.add(getEdge(i));
        }
        return result;
    }

    public int[] flow(int s, int t, Integer flowLimit) {
        List<int[]> result = slope(s, t, flowLimit);
        return result.get(result.size() - 1);
    }

    public List<int[]> slope(int s, int t, Integer flowLimit) {
        assert (0 <= s && s < n);
        assert (0 <= t && t < n);
        assert (s != t);

        if (flowLimit == null) {
            flowLimit = graph.get(s).stream().mapToInt(e -> e.cap).sum();
        }

        int[] dual = new int[n];
        Tuple[] prev = new Tuple[n];

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {0, 0});

        while (true) {
            if (!refineDual(s, t, dual, prev)) {
                break;
            }

            int f = flowLimit;
            int v = t;
            while (prev[v] != null) {
                Tuple tuple = prev[v];
                int u = tuple.first;
                _Edge e = tuple.second;
                f = Math.min(f, e.cap);
                v = u;
            }

            v = t;
            while (prev[v] != null) {
                Tuple tuple = prev[v];
                int u = tuple.first;
                _Edge e = tuple.second;
                e.cap -= f;
                e.rev.cap += f;
                v = u;
            }

            int c = -dual[s];
            result.add(new int[] {
                result.get(result.size() - 1)[0] + f, result.get(result.size() - 1)[1] + f * c});

            if (c == result.get(result.size() - 2)[1]) {
                result.remove(result.size() - 2);
            }
        }

        return result;
    }

    private boolean refineDual(int s, int t, int[] dual, Tuple[] prev) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0, s});
        boolean[] visited = new boolean[n];
        Integer[] dist = new Integer[n];
        Arrays.fill(dist, null);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int distV = current[0];
            int v = current[1];

            if (visited[v]) continue;
            visited[v] = true;

            if (v == t) break;

            int dualV = dual[v];
            for (_Edge e : graph.get(v)) {
                int w = e.dst;
                if (visited[w] || e.cap == 0) continue;

                int reducedCost = e.cost - dual[w] + dualV;
                int newDist = distV + reducedCost;
                Integer distW = dist[w];

                if (distW == null || newDist < distW) {
                    dist[w] = newDist;
                    prev[w] = new Tuple(v, e);
                    pq.add(new int[] {newDist, w});
                }
            }
        }

        if (!visited[t]) return false;

        int distT = dist[t];
        for (int v = 0; v < n; v++) {
            if (visited[v]) {
                dual[v] -= distT - dist[v];
            }
        }

        return true;
    }

    static class Tuple {
        int first;
        _Edge second;

        Tuple(int first, _Edge second) {
            this.first = first;
            this.second = second;
        }
    }
}

class Solution {
    public int findMinimumTime(int[] strength) {
        int n = strength.length;
        int s = n * 2;
        int t = s + 1;
        MCFGraph g = new MCFGraph(t + 1);

        for (int i = 0; i < n; i++) {
            g.addEdge(s, i, 1, 0);
            g.addEdge(i + n, t, 1, 0);
            for (int j = 0; j < n; j++) {
                g.addEdge(i, j + n, 1, (strength[i] - 1) / (j + 1) + 1);
            }
        }

        return g.flow(s, t, n)[1];
    }
}
