class Solution {
public:
    long long sumRemoteness(vector<vector<int>>& grid) {
        using pli = pair<long long, int>;
        int n = grid.size();
        int cnt = 0;
        for (auto& row : grid) {
            for (int x : row) {
                cnt += x > 0;
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<pli(int, int)> dfs = [&](int i, int j) {
            long long s = grid[i][j];
            int t = 1;
            grid[i][j] = 0;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0) {
                    auto [ss, tt] = dfs(x, y);
                    s += ss;
                    t += tt;
                }
            }
            return pli(s, t);
        };
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    auto [s, t] = dfs(i, j);
                    ans += (cnt - t) * s;
                }
            }
        }
        return ans;
    }
};