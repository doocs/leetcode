class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int shortestPathAllKeys(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        int k = 0;
        int si = 0, sj = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i][j];
                if (islower(c))
                    ++k;
                else if (c == '@')
                    si = i, sj = j;
            }
        }
        queue<tuple<int, int, int>> q{{{si, sj, 0}}};
        vector<vector<vector<bool>>> vis(m, vector<vector<bool>>(n, vector<bool>(1 << k)));
        vis[si][sj][0] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto [i, j, state] = q.front();
                q.pop();
                if (state == (1 << k) - 1) return ans;
                for (int h = 0; h < 4; ++h) {
                    int x = i + dirs[h], y = j + dirs[h + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x][y];
                        if (c == '#' || (isupper(c) && (state >> (c - 'A') & 1) == 0)) continue;
                        int nxt = state;
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