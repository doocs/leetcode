class neighborSum {
public:
    neighborSum(vector<vector<int>>& grid) {
        this->grid = grid;
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d[grid[i][j]] = {i, j};
            }
        }
    }

    int adjacentSum(int value) {
        return cal(value, 0);
    }

    int diagonalSum(int value) {
        return cal(value, 1);
    }

private:
    vector<vector<int>> grid;
    unordered_map<int, pair<int, int>> d;
    int dirs[2][5] = {{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}};

    int cal(int value, int k) {
        auto [i, j] = d[value];
        int s = 0;
        for (int q = 0; q < 4; ++q) {
            int x = i + dirs[k][q], y = j + dirs[k][q + 1];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size()) {
                s += grid[x][y];
            }
        }
        return s;
    }
};

/**
 * Your neighborSum object will be instantiated and called as such:
 * neighborSum* obj = new neighborSum(grid);
 * int param_1 = obj->adjacentSum(value);
 * int param_2 = obj->diagonalSum(value);
 */
