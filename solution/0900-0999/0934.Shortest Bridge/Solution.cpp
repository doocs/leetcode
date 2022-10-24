class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int shortestBridge(vector<vector<int>>& grid) {
        int n = grid.size();
        queue<pair<int, int>> q;
        function<void(int, int)> dfs = [&](int i, int j) {
            grid[i][j] = 2;
            q.emplace(i, j);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                    dfs(x, y);
                }
            }
        };
        for (int i = 0, x = 1; i < n && x; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    dfs(i, j);
                    x = 0;
                    break;
                }
            }
        }
        int ans = 0;
        while (1) {
            for (int h = q.size(); h; --h) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        if (grid[x][y] == 1) return ans;
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.emplace(x, y);
                        }
                    }
                }
            }
            ++ans;
        }
    }
};