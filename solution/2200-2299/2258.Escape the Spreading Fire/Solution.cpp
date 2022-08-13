class Solution {
public:
    vector<int> dirs = {-1, 0, 1, 0, -1};

    int maximumMinutes(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int left = -1, right = m * n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid, grid))
                left = mid;
            else
                right = mid - 1;
        }
        return left == m * n ? 1e9 : left;
    }

    bool check(int t, vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> fire(m, vector<bool>(n));
        queue<vector<int>> f;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    fire[i][j] = true;
                    f.push({i, j});
                }
            }
        }
        while (t-- && f.size()) f = spread(fire, f, grid);
        queue<vector<int>> q;
        vector<vector<bool>> vis(m, vector<bool>(n));
        q.push({0, 0});
        vis[0][0] = true;
        while (!q.empty()) {
            for (int i = q.size(); i > 0; --i) {
                auto p = q.front();
                q.pop();
                if (fire[p[0]][p[1]]) continue;
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y] && grid[x][y] == 0) {
                        if (x == m - 1 && y == n - 1) return true;
                        vis[x][y] = true;
                        q.push({x, y});
                    }
                }
            }
            f = spread(fire, f, grid);
        }
        return false;
    }

    queue<vector<int>> spread(vector<vector<bool>>& fire, queue<vector<int>>& f, vector<vector<int>>& grid) {
        queue<vector<int>> nf;
        int m = grid.size(), n = grid[0].size();
        while (!f.empty()) {
            auto p = f.front();
            f.pop();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                    fire[x][y] = true;
                    nf.push({x, y});
                }
            }
        }
        return nf;
    }
};