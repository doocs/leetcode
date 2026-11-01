class Solution {
public:
    int uniquePaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int n = grid[0].size();
        vector<array<int, 2>> f(n + 1);
        f[1] = {1, 1};
        for (auto& row : grid) {
            for (int j = 0; j < n; ++j) {
                int r = f[j][0], d = f[j + 1][1];
                if (row[j] == 1) {
                    f[j + 1] = {d, r};
                } else {
                    int s = (r + d) % mod;
                    f[j + 1] = {s, s};
                }
            }
        }
        return f[n][0];
    }
};
