class Solution {
public:
    int largestMagicSquare(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid.size();
        vector<vector<int>> rowsum(m + 1, vector<int>(n + 1));
        vector<vector<int>> colsum(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                rowsum[i][j] = rowsum[i][j - 1] + grid[i - 1][j - 1];
                colsum[i][j] = colsum[i - 1][j] + grid[i - 1][j - 1];
            }
        }
        for (int k = min(m, n); k > 1; --k) {
            for (int i = 0; i + k - 1 < m; ++i) {
                for (int j = 0; j + k - 1 < n; ++j) {
                    int i2 = i + k - 1, j2 = j + k - 1;
                    if (check(grid, rowsum, colsum, i, j, i2, j2))
                        return k;
                }
            }
        }
        return 1;
    }

    bool check(vector<vector<int>>& grid, vector<vector<int>>& rowsum, vector<vector<int>>& colsum, int x1, int y1, int x2, int y2) {
        int val = rowsum[x1 + 1][y2 + 1] - rowsum[x1 + 1][y1];
        for (int i = x1 + 1; i <= x2; ++i)
            if (rowsum[i + 1][y2 + 1] - rowsum[i + 1][y1] != val)
                return false;
        for (int j = y1; j <= y2; ++j)
            if (colsum[x2 + 1][j + 1] - colsum[x1][j + 1] != val)
                return false;
        int s = 0;
        for (int i = x1, j = y1; i <= x2; ++i, ++j)
            s += grid[i][j];
        if (s != val)
            return false;
        s = 0;
        for (int i = x1, j = y2; i <= x2; ++i, --j)
            s += grid[i][j];
        if (s != val)
            return false;
        return true;
    }
};