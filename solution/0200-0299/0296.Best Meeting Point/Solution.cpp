class Solution {
public:
    int minTotalDistance(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rows;
        vector<int> cols;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    rows.emplace_back(i);
                    cols.emplace_back(j);
                }
            }
        }
        sort(cols.begin(), cols.end());
        int i = rows[rows.size() / 2];
        int j = cols[cols.size() / 2];
        auto f = [](vector<int>& arr, int x) {
            int s = 0;
            for (int v : arr) {
                s += abs(v - x);
            }
            return s;
        };
        return f(rows, i) + f(cols, j);
    }
};