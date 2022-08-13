class Solution {
public:
    int matrixScore(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; ++j) grid[i][j] ^= 1;
            }
        }
        int res = 0;
        for (int j = 0; j < n; ++j) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) cnt += grid[i][j];
            res += max(cnt, m - cnt) * (1 << (n - j - 1));
        }
        return res;
    }
};