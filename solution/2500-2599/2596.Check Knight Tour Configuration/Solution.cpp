class Solution {
public:
    bool checkValidGrid(vector<vector<int>>& grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.size();
        vector<pair<int, int>> pos(n * n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[grid[i][j]] = {i, j};
            }
        }
        for (int i = 1; i < n * n; ++i) {
            auto [x1, y1] = pos[i - 1];
            auto [x2, y2] = pos[i];
            int dx = abs(x1 - x2);
            int dy = abs(y1 - y2);
            bool ok = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
            if (!ok) {
                return false;
            }
        }
        return true;
    }
};