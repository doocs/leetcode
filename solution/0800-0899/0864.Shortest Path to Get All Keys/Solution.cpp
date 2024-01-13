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
                // 累加钥匙数量
                if (islower(c)) ++k;
                // 起点
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
                // 找到所有钥匙，返回当前步数
                if (state == (1 << k) - 1) return ans;
                // 往四个方向搜索
                for (int h = 0; h < 4; ++h) {
                    int x = i + dirs[h], y = j + dirs[h + 1];
                    // 在边界范围内
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x][y];
                        // 是墙，或者是锁，但此时没有对应的钥匙，无法通过
                        if (c == '#' || (isupper(c) && (state >> (c - 'A') & 1) == 0)) continue;
                        int nxt = state;
                        // 是钥匙，更新状态
                        if (islower(c)) nxt |= 1 << (c - 'a');
                        // 此状态未访问过，入队
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.push({x, y, nxt});
                        }
                    }
                }
            }
            // 步数加一
            ++ans;
        }
        return -1;
    }
};