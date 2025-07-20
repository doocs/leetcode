class Solution {
public:
    int minMoves(vector<string>& classroom, int energy) {
        int m = classroom.size(), n = classroom[0].size();
        vector<vector<int>> d(m, vector<int>(n, 0));
        int x = 0, y = 0, cnt = 0;
        for (int i = 0; i < m; ++i) {
            string& row = classroom[i];
            for (int j = 0; j < n; ++j) {
                char c = row[j];
                if (c == 'S') {
                    x = i;
                    y = j;
                } else if (c == 'L') {
                    d[i][j] = cnt;
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            return 0;
        }
        vector<vector<vector<vector<bool>>>> vis(m, vector<vector<vector<bool>>>(n, vector<vector<bool>>(energy + 1, vector<bool>(1 << cnt, false))));
        queue<tuple<int, int, int, int>> q;
        q.emplace(x, y, energy, (1 << cnt) - 1);
        vis[x][y][energy][(1 << cnt) - 1] = true;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        while (!q.empty()) {
            int sz = q.size();
            while (sz--) {
                auto [i, j, cur_energy, mask] = q.front();
                q.pop();
                if (mask == 0) {
                    return ans;
                }
                if (cur_energy <= 0) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    int nx = i + dirs[k], ny = j + dirs[k + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && classroom[nx][ny] != 'X') {
                        int nxt_energy = classroom[nx][ny] == 'R' ? energy : cur_energy - 1;
                        int nxt_mask = mask;
                        if (classroom[nx][ny] == 'L') {
                            nxt_mask &= ~(1 << d[nx][ny]);
                        }
                        if (!vis[nx][ny][nxt_energy][nxt_mask]) {
                            vis[nx][ny][nxt_energy][nxt_mask] = true;
                            q.emplace(nx, ny, nxt_energy, nxt_mask);
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }
};
