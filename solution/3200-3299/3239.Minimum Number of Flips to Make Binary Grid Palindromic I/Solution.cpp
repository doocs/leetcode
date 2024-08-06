class Solution {
public:
    int minFlips(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt1 = 0, cnt2 = 0;
        for (const auto& row : grid) {
            for (int j = 0; j < n / 2; ++j) {
                if (row[j] != row[n - j - 1]) {
                    ++cnt1;
                }
            }
        }
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][j] != grid[m - i - 1][j]) {
                    ++cnt2;
                }
            }
        }
        return min(cnt1, cnt2);
    }
};
