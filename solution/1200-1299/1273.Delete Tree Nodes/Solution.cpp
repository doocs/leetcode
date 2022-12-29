class Solution {
public:
    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        vector<vector<int>> g(nodes);
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].emplace_back(i);
        }
        function<pair<int, int>(int)> dfs = [&](int i) -> pair<int, int> {
            int s = value[i], m = 1;
            for (int j : g[i]) {
                auto [t, n] = dfs(j);
                s += t;
                m += n;
            }
            if (s == 0) {
                m = 0;
            }
            return pair<int, int>{s, m};
        };
        return dfs(0).second;
    }
};