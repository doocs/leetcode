class Solution {
public:
    int assignEdgeWeights(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n + 1);

        for (auto& e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }

        auto dfs = [&](this auto&& dfs, int i, int fa) -> int {
            int res = 0;
            for (int j : g[i]) {
                if (j != fa) {
                    res = max(res, dfs(j, i) + 1);
                }
            }
            return res;
        };

        return pow(2, dfs(1, 0) - 1, 1000000007);
    }

private:
    long long pow(long long a, int n, int mod) {
        long long res = 1;
        while (n > 0) {
            if (n & 1) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }
};