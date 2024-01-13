class Solution {
public:
    int flipChess(vector<string>& chessboard) {
        int m = chessboard.size();
        int n = chessboard[0].size();
        auto bfs = [&](int i, int j) -> int {
            queue<pair<int, int>> q;
            q.emplace(i, j);
            auto g = chessboard;
            g[i][j] = 'X';
            int cnt = 0;
            while (q.size()) {
                auto p = q.front();
                q.pop();
                i = p.first;
                j = p.second;
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        if (a == 0 && b == 0) {
                            continue;
                        }
                        int x = i + a, y = j + b;
                        while (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'O') {
                            x += a;
                            y += b;
                        }
                        if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'X') {
                            x -= a;
                            y -= b;
                            cnt += max(abs(x - i), abs(y - j));
                            while (x != i || y != j) {
                                g[x][y] = 'X';
                                q.emplace(x, y);
                                x -= a;
                                y -= b;
                            }
                        }
                    }
                }
            }
            return cnt;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (chessboard[i][j] == '.') {
                    ans = max(ans, bfs(i, j));
                }
            }
        }
        return ans;
    }
};