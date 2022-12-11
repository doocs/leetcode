class Solution {
public:
    int maxStarSum(vector<int>& vals, vector<vector<int>>& edges, int k) {
        int n = vals.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (vals[b] > 0) g[a].emplace_back(vals[b]);
            if (vals[a] > 0) g[b].emplace_back(vals[a]);
        }
        for (auto& e : g) sort(e.rbegin(), e.rend());
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int v = vals[i];
            for (int j = 0; j < min((int) g[i].size(), k); ++j) v += g[i][j];
            ans = max(ans, v);
        }
        return ans;
    }
};