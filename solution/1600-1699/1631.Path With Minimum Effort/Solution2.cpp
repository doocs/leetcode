class Solution {
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
        auto check = [&](int h) {
            int m = heights.size(), n = heights[0].size();
            bool vis[m][n];
            memset(vis, false, sizeof(vis));
            queue<pair<int, int>> q{{{0, 0}}};
            vis[0][0] = true;
            int dirs[5] = {-1, 0, 1, 0, -1};
            while (!q.empty()) {
                auto [i, j] = q.front();
                q.pop();
                if (i == m - 1 && j == n - 1) {
                    return true;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && abs(heights[x][y] - heights[i][j]) <= h) {
                        q.push({x, y});
                        vis[x][y] = true;
                    }
                }
            }
            return false;
        };
        int l = 0, r = 1e6;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};