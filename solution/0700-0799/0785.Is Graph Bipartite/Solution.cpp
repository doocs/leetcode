class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> color(n);
        for (int i = 0; i < n; ++i)
            if (!color[i] && !dfs(i, 1, color, graph))
                return false;
        return true;
    }

    bool dfs(int u, int c, vector<int>& color, vector<vector<int>>& g) {
        color[u] = c;
        for (int& v : g[u]) {
            if (!color[v]) {
                if (!dfs(v, 3 - c, color, g)) return false;
            } else if (color[v] == c)
                return false;
        }
        return true;
    }
};