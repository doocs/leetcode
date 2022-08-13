class Solution {
public:
    vector<int> p;

    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        int ans = min(grid[0][0], grid[m - 1][n - 1]);
        vector<vector<bool>> vis(m, vector<bool>(n));
        vis[0][0] = true;
        vis[m - 1][n - 1] = true;
        vector<tuple<int, int, int>> scores;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                scores.emplace_back(grid[i][j], i, j);
        sort(scores.begin(), scores.end());
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (find(0) != find(m * n - 1)) {
            auto [score, i, j] = scores.back();
            scores.pop_back();
            ans = min(ans, score);
            vis[i][j] = true;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y])
                    p[find(x * n + y)] = find(i * n + j);
            }
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};