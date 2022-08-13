class Solution {
public:
    int shortestPath(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        if (k >= m + n - 3) return m + n - 2;
        queue<vector<int>> q;
        q.push({0, 0, k});
        vector<vector<vector<bool>>> vis(m, vector<vector<bool>>(n, vector<bool>(k + 1)));
        vis[0][0][k] = true;
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                auto p = q.front();
                k = p[2];
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j], y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (x == m - 1 && y == n - 1) return ans;
                        if (grid[x][y] == 0 && !vis[x][y][k]) {
                            q.push({x, y, k});
                            vis[x][y][k] = true;
                        } else if (grid[x][y] == 1 && k > 0 && !vis[x][y][k - 1]) {
                            q.push({x, y, k - 1});
                            vis[x][y][k - 1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
};