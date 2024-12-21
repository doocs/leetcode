class Solution {
public:
    int maxMoves(int kx, int ky, vector<vector<int>>& positions) {
        int n = positions.size();
        const int m = 50;
        const int dx[8] = {1, 1, 2, 2, -1, -1, -2, -2};
        const int dy[8] = {2, -2, 1, -1, 2, -2, 1, -1};
        int dist[n + 1][m][m];
        memset(dist, -1, sizeof(dist));
        for (int i = 0; i <= n; ++i) {
            int x = (i < n) ? positions[i][0] : kx;
            int y = (i < n) ? positions[i][1] : ky;
            queue<pair<int, int>> q;
            q.push({x, y});
            dist[i][x][y] = 0;
            for (int step = 1; !q.empty(); ++step) {
                for (int k = q.size(); k > 0; --k) {
                    auto [x1, y1] = q.front();
                    q.pop();
                    for (int j = 0; j < 8; ++j) {
                        int x2 = x1 + dx[j], y2 = y1 + dy[j];
                        if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < m && dist[i][x2][y2] == -1) {
                            dist[i][x2][y2] = step;
                            q.push({x2, y2});
                        }
                    }
                }
            }
        }

        int f[n + 1][1 << n][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int last, int state, int k) -> int {
            if (state == 0) {
                return 0;
            }
            if (f[last][state][k] != -1) {
                return f[last][state][k];
            }
            int res = (k == 1) ? 0 : INT_MAX;

            for (int i = 0; i < positions.size(); ++i) {
                int x = positions[i][0], y = positions[i][1];
                if ((state >> i) & 1) {
                    int t = dfs(i, state ^ (1 << i), k ^ 1) + dist[last][x][y];
                    if (k == 1) {
                        res = max(res, t);
                    } else {
                        res = min(res, t);
                    }
                }
            }
            return f[last][state][k] = res;
        };
        return dfs(n, (1 << n) - 1, 1);
    }
};
