class MinCostMaxFlow {
public:
    struct Edge {
        int to, cap, cost, rev;

        Edge(int to, int cap, int cost, int rev)
            : to(to)
            , cap(cap)
            , cost(cost)
            , rev(rev) {}
    };

    static constexpr int INF = 1e9;

    int n;
    vector<vector<Edge>> graph;

    MinCostMaxFlow(int n)
        : n(n)
        , graph(n) {}

    void addEdge(int u, int v, int cap, int cost) {
        graph[u].emplace_back(v, cap, cost, graph[v].size());
        graph[v].emplace_back(u, 0, -cost, graph[u].size() - 1);
    }

    long long minCostFlow(int source, int sink, int maxFlow) {
        long long totalCost = 0;
        int currentFlow = 0;

        while (currentFlow < maxFlow) {
            vector<int> dist(n, INF);
            vector<int> parentNode(n, -1);
            vector<int> parentEdge(n, -1);
            vector<bool> inQueue(n, false);

            queue<int> q;
            q.push(source);
            dist[source] = 0;
            inQueue[source] = true;

            while (!q.empty()) {
                int u = q.front();
                q.pop();
                inQueue[u] = false;

                for (int i = 0; i < graph[u].size(); i++) {
                    Edge& e = graph[u][i];
                    if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                        dist[e.to] = dist[u] + e.cost;
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;

                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            q.push(e.to);
                        }
                    }
                }
            }

            if (dist[sink] == INF) {
                return -1;
            }

            int pushFlow = maxFlow - currentFlow;

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge& e = graph[parentNode[cur]][parentEdge[cur]];
                pushFlow = min(pushFlow, e.cap);
            }

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge& e = graph[parentNode[cur]][parentEdge[cur]];
                e.cap -= pushFlow;
                graph[cur][e.rev].cap += pushFlow;
            }

            currentFlow += pushFlow;
            totalCost += 1LL * pushFlow * dist[sink];
        }

        return totalCost;
    }
};

class Solution {
public:
    long long minMoves(vector<int>& balance) {
        int totalBalance = accumulate(balance.begin(), balance.end(), 0);
        if (totalBalance < 0) {
            return -1;
        }

        int totalDeficit = 0;
        for (int x : balance) {
            if (x < 0) {
                totalDeficit += -x;
            }
        }

        if (totalDeficit == 0) {
            return 0;
        }

        int n = balance.size();
        int source = n;
        int sink = n + 1;

        MinCostMaxFlow mcmf(n + 2);

        for (int i = 0; i < n; i++) {
            if (balance[i] > 0) {
                mcmf.addEdge(source, i, balance[i], 0);
            } else if (balance[i] < 0) {
                mcmf.addEdge(i, sink, -balance[i], 0);
            }

            mcmf.addEdge(i, (i + 1) % n, MinCostMaxFlow::INF, 1);
            mcmf.addEdge(i, (i - 1 + n) % n, MinCostMaxFlow::INF, 1);
        }

        return mcmf.minCostFlow(source, sink, totalDeficit);
    }
};