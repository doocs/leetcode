class Solution {
public:
    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<tuple<int, int, int>> q;
        vector<int> p(m * n);
        iota(p.begin(), p.end(), 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.emplace_back(grid[i][j], i, j);
            }
        }
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        sort(q.begin(), q.end(), greater<tuple<int, int, int>>());
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        for (auto& [v, i, j] : q) {
            vis[i][j] = true;
            ans = v;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    p[find(x * n + y)] = find(i * n + j);
                }
            }
            if (find(0) == find(m * n - 1)) {
                break;
            }
        }
        return ans;
    }
};