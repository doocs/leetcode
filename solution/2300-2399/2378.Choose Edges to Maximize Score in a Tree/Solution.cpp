using ll = long long;

class Solution {
public:
    vector<unordered_map<int, int>> g;

    long long maxScore(vector<vector<int>>& edges) {
        int n = edges.size();
        g.resize(n + 1);
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p][i] = w;
        }
        return dfs(0).second;
    }

    pair<ll, ll> dfs(int i) {
        ll a = 0, b = 0;
        ll s = 0;
        for (auto& [j, v] : g[i]) {
            auto t = dfs(j);
            a += t.second;
            b += t.second;
            s = max(s, t.first - t.second + v);
        }
        b += s;
        return {a, b};
    }
};