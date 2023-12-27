class Solution {
public:
    vector<long long> placedCoins(vector<vector<int>>& edges, vector<int>& cost) {
        int n = cost.size();
        vector<long long> ans(n, 1);
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<vector<int>(int, int)> dfs = [&](int a, int fa) -> vector<int> {
            vector<int> res = {cost[a]};
            for (int b : g[a]) {
                if (b != fa) {
                    auto t = dfs(b, a);
                    res.insert(res.end(), t.begin(), t.end());
                }
            }
            sort(res.begin(), res.end());
            int m = res.size();
            if (m >= 3) {
                long long x = 1LL * res[m - 1] * res[m - 2] * res[m - 3];
                long long y = 1LL * res[0] * res[1] * res[m - 1];
                ans[a] = max({0LL, x, y});
            }
            if (m >= 5) {
                res = {res[0], res[1], res[m - 1], res[m - 2], res[m - 3]};
            }
            return res;
        };
        dfs(0, -1);
        return ans;
    }
};