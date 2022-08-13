class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    int row, col;

    int latestDayToCross(int row, int col, vector<vector<int>>& cells) {
        int n = row * col;
        this->row = row;
        this->col = col;
        p.resize(n + 2);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<bool>> grid(row, vector<bool>(col, false));
        int top = n, bottom = n + 1;
        for (int k = cells.size() - 1; k >= 0; --k) {
            int i = cells[k][0] - 1, j = cells[k][1] - 1;
            grid[i][j] = true;
            for (auto e : dirs) {
                if (check(i + e[0], j + e[1], grid)) {
                    p[find(i * col + j)] = find((i + e[0]) * col + j + e[1]);
                }
            }
            if (i == 0) p[find(i * col + j)] = find(top);
            if (i == row - 1) p[find(i * col + j)] = find(bottom);
            if (find(top) == find(bottom)) return k;
        }
        return 0;
    }

    bool check(int i, int j, vector<vector<bool>>& grid) {
        return i >= 0 && i < row && j >= 0 && j < col && grid[i][j];
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};