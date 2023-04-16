class Solution {
public:
    int minimumTotalPrice(int n, vector<vector<int>>& edges, vector<int>& price, vector<vector<int>>& trips) {
        vector<vector<int>> g(n);
        vector<int> cnt(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<bool(int, int, int)> dfs = [&](int i, int fa, int k) -> bool {
            ++cnt[i];
            if (i == k) {
                return true;
            }
            bool ok = false;
            for (int j : g[i]) {
                if (j != fa) {
                    ok = dfs(j, i, k);
                    if (ok) {
                        break;
                    }
                }
            }
            if (!ok) {
                --cnt[i];
            }
            return ok;
        };
        function<pair<int, int>(int, int)> dfs2 = [&](int i, int fa) -> pair<int, int> {
            int a = cnt[i] * price[i];
            int b = a >> 1;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [x, y] = dfs2(j, i);
                    a += min(x, y);
                    b += x;
                }
            }
            return {a, b};
        };
        for (auto& t : trips) {
            int start = t[0], end = t[1];
            dfs(start, -1, end);
        }
        auto [a, b] = dfs2(0, -1);
        return min(a, b);
    }
};