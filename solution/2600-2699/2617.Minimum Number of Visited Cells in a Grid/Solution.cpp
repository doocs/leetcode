class Solution {
public:
    int minimumVisitedCells(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int row[m], col[n];
        memset(row, 0, sizeof(row));
        memset(col, 0, sizeof(col));
        queue<tuple<int, int, int>> q;
        q.emplace(1, 0, 0);
        while (!q.empty()) {
            auto [dist, i, j] = q.front();
            q.pop();
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            for (int k = max(row[i], j) + 1; k < min(n, j + grid[i][j] + 1); ++k) {
                q.emplace(dist + 1, i, k);
                row[i] = k;
            }
            for (int k = max(col[j], i) + 1; k < min(m, i + grid[i][j] + 1); ++k) {
                q.emplace(dist + 1, k, j);
                col[j] = k;
            }
        }
        return -1;
    }
};