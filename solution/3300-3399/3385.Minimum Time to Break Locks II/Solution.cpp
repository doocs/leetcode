class MCFGraph {
public:
    MCFGraph(int n)
        : n(n)
        , g(n) {}

    void addEdge(int src, int dst, int cap, int cost) {
        int i = g[src].size();
        int j = g[dst].size();
        g[src].push_back({dst, cap, cost, j});
        g[dst].push_back({src, 0, -cost, i});
    }

    pair<int, int> flow(int s, int t, int flowLimit) {
        return slope(s, t, flowLimit).back();
    }

private:
    struct _Edge {
        int dst, cap, cost, rev;
    };

    int n;
    vector<vector<_Edge>> g;

    vector<pair<int, int>> slope(int s, int t, int flowLimit) {
        vector<int> dual(n);
        vector<int> pv(n), pe(n);
        auto refineDual = [&]() -> bool {
            vector<int> dist(n, INT_MAX);
            vector<char> vis(n);
            priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
            dist[s] = 0;
            pq.emplace(0, s);
            while (!pq.empty()) {
                auto [distV, v] = pq.top();
                pq.pop();
                if (vis[v]) {
                    continue;
                }
                vis[v] = 1;
                if (v == t) {
                    break;
                }
                int dualV = dual[v];
                for (int i = 0; i < (int) g[v].size(); ++i) {
                    auto& e = g[v][i];
                    int w = e.dst;
                    if (vis[w] || e.cap == 0) {
                        continue;
                    }
                    int newDist = distV + e.cost - dual[w] + dualV;
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                        pv[w] = v;
                        pe[w] = i;
                        pq.emplace(newDist, w);
                    }
                }
            }
            if (!vis[t]) {
                return false;
            }
            int distT = dist[t];
            for (int v = 0; v < n; ++v) {
                if (vis[v]) {
                    dual[v] -= distT - dist[v];
                }
            }
            return true;
        };

        int flow = 0, cost = 0;
        int prevCostPerFlow = INT_MIN;
        vector<pair<int, int>> result = {{0, 0}};
        while (flow < flowLimit) {
            if (!refineDual()) {
                break;
            }
            int f = flowLimit - flow;
            for (int v = t; v != s; v = pv[v]) {
                f = min(f, g[pv[v]][pe[v]].cap);
            }
            for (int v = t; v != s; v = pv[v]) {
                auto& e = g[pv[v]][pe[v]];
                e.cap -= f;
                g[v][e.rev].cap += f;
            }
            int c = -dual[s];
            flow += f;
            cost += f * c;
            if (c == prevCostPerFlow) {
                result.pop_back();
            }
            result.emplace_back(flow, cost);
            prevCostPerFlow = c;
        }
        return result;
    }
};

class Solution {
public:
    int findMinimumTime(vector<int>& strength) {
        int n = strength.size();
        int s = n * 2;
        int t = s + 1;
        MCFGraph g(t + 1);
        for (int i = 0; i < n; ++i) {
            g.addEdge(s, i, 1, 0);
            g.addEdge(i + n, t, 1, 0);
            for (int j = 0; j < n; ++j) {
                g.addEdge(i, j + n, 1, (strength[i] - 1) / (j + 1) + 1);
            }
        }
        return g.flow(s, t, n).second;
    }
};
