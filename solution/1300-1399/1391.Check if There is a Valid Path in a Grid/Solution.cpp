class Solution {
public:
    vector<int> p;

    bool hasValidPath(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        auto left = [&](int i, int j) {
            if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
                p[find(i * n + j)] = find(i * n + j - 1);
            }
        };
        auto right = [&](int i, int j) {
            if (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
                p[find(i * n + j)] = find(i * n + j + 1);
            }
        };
        auto up = [&](int i, int j) {
            if (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
                p[find(i * n + j)] = find((i - 1) * n + j);
            }
        };
        auto down = [&](int i, int j) {
            if (i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
                p[find(i * n + j)] = find((i + 1) * n + j);
            }
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int e = grid[i][j];
                if (e == 1) {
                    left(i, j);
                    right(i, j);
                } else if (e == 2) {
                    up(i, j);
                    down(i, j);
                } else if (e == 3) {
                    left(i, j);
                    down(i, j);
                } else if (e == 4) {
                    right(i, j);
                    down(i, j);
                } else if (e == 5) {
                    left(i, j);
                    up(i, j);
                } else {
                    right(i, j);
                    up(i, j);
                }
            }
        }
        return find(0) == find(m * n - 1);
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};