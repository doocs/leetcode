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
        int d = 0;
        vector<array<int, 3>> stk{{1, 0, 0}};
        while (!stk.empty()) {
            auto [i, fa, dep] = stk.back();
            stk.pop_back();
            d = max(d, dep);
            for (int j : g[i]) {
                if (j != fa) {
                    stk.push_back({j, i, dep + 1});
                }
            }
        }
        return pow(2, d - 1, 1000000007);
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
