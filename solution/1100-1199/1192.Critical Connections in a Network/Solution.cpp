class Solution {
public:
    int count = 0;
    vector<int> dfn, low;
    vector<vector<int>> graph;
    vector<vector<int>> res;
    void tarjan(int u, int fa) {
        dfn[u] = low[u] = ++count;
        for (auto& v : graph[u]) {
            if (v == fa)
                continue;
            if (!dfn[v]) {
                tarjan(v, u);
                low[u] = min(low[u], low[v]);
                if (dfn[u] < low[v])
                    res.push_back({u, v});
            } else {
                low[u] = min(dfn[v], low[u]);
            }
        }
    }

    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        dfn.resize(n);
        low.resize(n);
        graph.resize(n);
        for (auto& edge : connections) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (!dfn[i])
                tarjan(i, -1);
        }
        return res;
    }
};
