class Solution {
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        int now = 0;
        vector<int> dfn(n);
        vector<int> low(n);
        vector<int> g[n];
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<vector<int>> ans;
        function<void(int, int)> tarjan = [&](int a, int fa) -> void {
            dfn[a] = low[a] = ++now;
            for (int b : g[a]) {
                if (b == fa) {
                    continue;
                }
                if (!dfn[b]) {
                    tarjan(b, a);
                    low[a] = min(low[a], low[b]);
                    if (low[b] > dfn[a]) {
                        ans.push_back({a, b});
                    }
                } else {
                    low[a] = min(low[a], dfn[b]);
                }
            }
        };
        tarjan(0, -1);
        return ans;
    }
};