class Solution {
public:
    int connectTwoGroups(vector<vector<int>>& cost) {
        int m = cost.size(), n = cost[0].size();
        const int inf = 1 << 30;
        vector<int> f(1 << n, inf);
        f[0] = 0;
        vector<int> g = f;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                g[j] = inf;
                for (int k = 0; k < n; ++k) {
                    if (j >> k & 1) {
                        int c = cost[i - 1][k];
                        int x = min({g[j ^ (1 << k)], f[j], f[j ^ (1 << k)]}) + c;
                        g[j] = min(g[j], x);
                    }
                }
            }
            f.swap(g);
        }
        return f[(1 << n) - 1];
    }
};