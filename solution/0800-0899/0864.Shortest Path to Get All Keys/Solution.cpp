class Solution {
public:
    int shortestPathAllKeys(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt = 0;
        int sx = 0, sy = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i][j];
                if (islower(c))
                    ++cnt;
                else if (c == '@') {
                    sx = i;
                    sy = j;
                }
            }
        }
        queue<vector<int>> q;
        q.push({sx, sy, 0});
        int mask = (1 << cnt) - 1;
        vector<vector<vector<bool>>> vis(m, vector<vector<bool>>(n, vector<bool>(1 << cnt)));
        vis[sx][sy][0] = true;
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto p = q.front();
                q.pop();
                int i = p[0], j = p[1], state = p[2];
                if (state == mask) return ans;
                for (int k = 0; k < 4; ++k) {
                    int nxt = state;
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x][y];
                        if (c == '#' || (isupper(c) && (nxt & (1 << (c - 'A'))) == 0)) continue;
                        if (islower(c)) nxt |= 1 << (c - 'a');
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.push({x, y, nxt});
                        }
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};