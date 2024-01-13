class Solution {
public:
    int maximumMinutes(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        bool vis[m][n];
        bool fire[m][n];
        int dirs[5] = {-1, 0, 1, 0, -1};
        auto spread = [&](queue<pair<int, int>>& q) {
            queue<pair<int, int>> nq;
            while (q.size()) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                        fire[x][y] = true;
                        nq.emplace(x, y);
                    }
                }
            }
            return nq;
        };
        auto check = [&](int t) {
            memset(vis, false, sizeof(vis));
            memset(fire, false, sizeof(fire));
            queue<pair<int, int>> q1;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        q1.emplace(i, j);
                        fire[i][j] = true;
                    }
                }
            }
            for (; t && q1.size(); --t) {
                q1 = spread(q1);
            }
            if (fire[0][0]) {
                return false;
            }
            queue<pair<int, int>> q2;
            q2.emplace(0, 0);
            vis[0][0] = true;
            for (; q2.size(); q1 = spread(q1)) {
                for (int d = q2.size(); d; --d) {
                    auto [i, j] = q2.front();
                    q2.pop();
                    if (fire[i][j]) {
                        continue;
                    }
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && !fire[x][y] && grid[x][y] == 0) {
                            if (x == m - 1 && y == n - 1) {
                                return true;
                            }
                            vis[x][y] = true;
                            q2.emplace(x, y);
                        }
                    }
                }
            }
            return false;
        };
        int l = -1, r = m * n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == m * n ? 1e9 : l;
    }
};