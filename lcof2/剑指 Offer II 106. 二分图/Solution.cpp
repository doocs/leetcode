class Solution {
public:
    vector<int> p;

    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (int u = 0; u < n; ++u) {
            auto& g = graph[u];
            for (int v : g) {
                if (find(u) == find(v)) return 0;
                p[find(v)] = find(g[0]);
            }
        }
        return 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};