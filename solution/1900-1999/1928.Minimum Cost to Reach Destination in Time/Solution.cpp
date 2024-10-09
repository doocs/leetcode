class Solution {
public:
    int minCost(int maxTime, vector<vector<int>>& edges, vector<int>& passingFees) {
        int m = maxTime, n = passingFees.size();
        const int inf = 1 << 30;
        vector<vector<int>> f(m + 1, vector<int>(n, inf));
        f[0][0] = passingFees[0];
        for (int i = 1; i <= m; ++i) {
            for (const auto& e : edges) {
                int x = e[0], y = e[1], t = e[2];
                if (t <= i) {
                    f[i][x] = min(f[i][x], f[i - t][y] + passingFees[x]);
                    f[i][y] = min(f[i][y], f[i - t][x] + passingFees[y]);
                }
            }
        }
        int ans = inf;
        for (int i = 1; i <= m; ++i) {
            ans = min(ans, f[i][n - 1]);
        }
        return ans == inf ? -1 : ans;
    }
};
