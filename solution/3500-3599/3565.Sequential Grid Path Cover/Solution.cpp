class Solution {
    int m, n;
    unsigned long long st = 0;
    vector<vector<int>> path;
    int dirs[5] = {-1, 0, 1, 0, -1};

    int f(int i, int j) {
        return i * n + j;
    }

    bool dfs(int i, int j, int v, vector<vector<int>>& grid) {
        path.push_back({i, j});
        if (path.size() == static_cast<size_t>(m * n)) {
            return true;
        }
        st |= 1ULL << f(i, j);
        if (grid[i][j] == v) {
            v += 1;
        }
        for (int t = 0; t < 4; ++t) {
            int a = dirs[t], b = dirs[t + 1];
            int x = i + a, y = j + b;
            if (0 <= x && x < m && 0 <= y && y < n && (st & (1ULL << f(x, y))) == 0
                && (grid[x][y] == 0 || grid[x][y] == v)) {
                if (dfs(x, y, v, grid)) {
                    return true;
                }
            }
        }
        path.pop_back();
        st ^= 1ULL << f(i, j);
        return false;
    }

public:
    vector<vector<int>> findPath(vector<vector<int>>& grid, int k) {
        m = grid.size();
        n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 || grid[i][j] == 1) {
                    if (dfs(i, j, 1, grid)) {
                        return path;
                    }
                    path.clear();
                    st = 0;
                }
            }
        }
        return {};
    }
};
