class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> f(n);
        const int inf = 1e9;
        for (const auto& row : grid) {
            vector<int> g = row;
            for (int i = 0; i < n; ++i) {
                int t = inf;
                for (int j = 0; j < n; ++j) {
                    if (j != i) {
                        t = min(t, f[j]);
                    }
                }
                g[i] += (t == inf ? 0 : t);
            }
            f = move(g);
        }
        return ranges::min(f);
    }
};
