class Solution {
public:
    vector<int> countPairsOfConnectableServers(vector<vector<int>>& edges, int signalSpeed) {
        int n = edges.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1], w = e[2];
            g[a].emplace_back(b, w);
            g[b].emplace_back(a, w);
        }
        auto count = [&](int start, int fa, int dist) {
            int cnt = 0;
            vector<array<int, 3>> stk{{start, fa, dist}};
            while (!stk.empty()) {
                auto [a, parent, ws] = stk.back();
                stk.pop_back();
                if (ws % signalSpeed == 0) {
                    ++cnt;
                }
                for (auto& [b, w] : g[a]) {
                    if (b != parent) {
                        stk.push_back({b, a, ws + w});
                    }
                }
            }
            return cnt;
        };
        vector<int> ans(n);
        for (int a = 0; a < n; ++a) {
            int s = 0;
            for (auto& [b, w] : g[a]) {
                int t = count(b, a, w);
                ans[a] += s * t;
                s += t;
            }
        }
        return ans;
    }
};
