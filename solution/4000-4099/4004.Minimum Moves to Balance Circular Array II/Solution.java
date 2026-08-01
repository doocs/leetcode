class MinCostMaxFlow {

    static class Edge {
        int to;
        int cap;
        int cost;
        int rev;

        Edge(int to, int cap, int cost, int rev) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.rev = rev;
        }
    }

    private static final int INF = 1 << 29;

    private final int n;
    private final List<Edge>[] graph;

    public MinCostMaxFlow(int n) {
        this.n = n;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int cap, int cost) {
        graph[u].add(new Edge(v, cap, cost, graph[v].size()));
        graph[v].add(new Edge(u, 0, -cost, graph[u].size() - 1));
    }

    public long minCostFlow(int source, int sink, int maxFlow) {
        long totalCost = 0;
        int currentFlow = 0;

        while (currentFlow < maxFlow) {
            int[] dist = new int[n];
            Arrays.fill(dist, INF);

            int[] parentNode = new int[n];
            int[] parentEdge = new int[n];
            boolean[] inQueue = new boolean[n];

            Arrays.fill(parentNode, -1);
            Arrays.fill(parentEdge, -1);

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(source);
            dist[source] = 0;
            inQueue[source] = true;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                inQueue[u] = false;

                for (int i = 0; i < graph[u].size(); i++) {
                    Edge e = graph[u].get(i);
                    if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                        dist[e.to] = dist[u] + e.cost;
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;

                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            queue.offer(e.to);
                        }
                    }
                }
            }

            if (dist[sink] == INF) {
                return -1;
            }

            int pushFlow = maxFlow - currentFlow;

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge e = graph[parentNode[cur]].get(parentEdge[cur]);
                pushFlow = Math.min(pushFlow, e.cap);
            }

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge e = graph[parentNode[cur]].get(parentEdge[cur]);
                e.cap -= pushFlow;
                graph[cur].get(e.rev).cap += pushFlow;
            }

            currentFlow += pushFlow;
            totalCost += 1L * pushFlow * dist[sink];
        }

        return totalCost;
    }
}

class Solution {

    public long minMoves(int[] balance) {
        int totalBalance = 0;
        int totalDeficit = 0;

        for (int x : balance) {
            totalBalance += x;
            if (x < 0) {
                totalDeficit += -x;
            }
        }

        if (totalBalance < 0) {
            return -1;
        }

        if (totalDeficit == 0) {
            return 0;
        }

        int n = balance.length;
        int source = n;
        int sink = n + 1;
        int INF = 1 << 29;

        MinCostMaxFlow mcmf = new MinCostMaxFlow(n + 2);

        for (int i = 0; i < n; i++) {
            if (balance[i] > 0) {
                mcmf.addEdge(source, i, balance[i], 0);
            } else if (balance[i] < 0) {
                mcmf.addEdge(i, sink, -balance[i], 0);
            }

            mcmf.addEdge(i, (i + 1) % n, INF, 1);
            mcmf.addEdge(i, (i - 1 + n) % n, INF, 1);
        }

        return mcmf.minCostFlow(source, sink, totalDeficit);
    }
}