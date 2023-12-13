class Solution {
public:
    bool possibleToStamp(vector<vector<int>>& grid, int stampHeight, int stampWidth) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        vector<vector<int>> d(m + 2, vector<int>(n + 2));
        for (int i = 1; i + stampHeight - 1 <= m; ++i) {
            for (int j = 1; j + stampWidth - 1 <= n; ++j) {
                int x = i + stampHeight - 1, y = j + stampWidth - 1;
                if (s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] == 0) {
                    d[i][j]++;
                    d[i][y + 1]--;
                    d[x + 1][j]--;
                    d[x + 1][y + 1]++;
                }
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
                if (grid[i - 1][j - 1] == 0 && d[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
};