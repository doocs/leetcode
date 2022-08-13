class Solution {
public:
    int shortestDistance(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        typedef pair<int, int> pii;
        queue<pii> q;
        int total = 0;
        vector<vector<int>> cnt(m, vector<int>(n));
        vector<vector<int>> dist(m, vector<int>(n));
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++total;
                    q.push({i, j});
                    vector<vector<bool>> vis(m, vector<bool>(n));
                    int d = 0;
                    while (!q.empty()) {
                        ++d;
                        for (int k = q.size(); k > 0; --k) {
                            auto p = q.front();
                            q.pop();
                            for (int l = 0; l < 4; ++l) {
                                int x = p.first + dirs[l];
                                int y = p.second + dirs[l + 1];
                                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !vis[x][y]) {
                                    ++cnt[x][y];
                                    dist[x][y] += d;
                                    q.push({x, y});
                                    vis[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        int ans = INT_MAX;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 0 && cnt[i][j] == total)
                    ans = min(ans, dist[i][j]);
        return ans == INT_MAX ? -1 : ans;
    }
};