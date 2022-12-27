class Solution {
public:
    int largest1BorderedSquare(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int down[m][n];
        int right[m][n];
        memset(down, 0, sizeof down);
        memset(right, 0, sizeof right);
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    down[i][j] = i + 1 < m ? down[i + 1][j] + 1 : 1;
                    right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int k = min(m, n); k > 0; --k) {
            for (int i = 0; i <= m - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (down[i][j] >= k && right[i][j] >= k && right[i + k - 1][j] >= k
                        && down[i][j + k - 1] >= k) {
                        return k * k;
                    }
                }
            }
        }
        return 0;
    }
};