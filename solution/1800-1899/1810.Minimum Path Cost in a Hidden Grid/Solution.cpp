/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *   public:
 *     bool canMove(char direction);
 *     int move(char direction);
 *     boolean isTarget();
 * };
 */

class Solution {
public:
    int findShortestPath(GridMaster& master) {
        const int m = 200, n = 200;
        const int sx = 100, sy = 100;
        const int INF = INT_MAX / 2;
        int dirs[5] = {-1, 0, 1, 0, -1};
        char s[4] = {'U', 'R', 'D', 'L'};

        vector<vector<int>> g(m, vector<int>(n, -1));
        pair<int, int> target = {-1, -1};

        auto dfs = [&](this auto& dfs, int x, int y) -> void {
            if (master.isTarget()) {
                target = {x, y};
            }
            for (int k = 0; k < 4; ++k) {
                int dx = dirs[k], dy = dirs[k + 1];
                int nx = x + dx, ny = y + dy;
                if (0 <= nx && nx < m && 0 <= ny && ny < n && g[nx][ny] == -1 && master.canMove(s[k])) {
                    g[nx][ny] = master.move(s[k]);
                    dfs(nx, ny);
                    master.move(s[(k + 2) % 4]);
                }
            }
        };

        g[sx][sy] = 0;
        dfs(sx, sy);

        if (target.first == -1 && target.second == -1) {
            return -1;
        }

        vector<vector<int>> dist(m, vector<int>(n, INF));
        dist[sx][sy] = 0;

        using Node = tuple<int, int, int>;
        priority_queue<Node, vector<Node>, greater<Node>> pq;
        pq.emplace(0, sx, sy);

        while (!pq.empty()) {
            auto [w, x, y] = pq.top();
            pq.pop();
            if (x == target.first && y == target.second) {
                return w;
            }
            if (w > dist[x][y]) {
                continue;
            }

            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && g[nx][ny] != -1) {
                    int nd = w + g[nx][ny];
                    if (nd < dist[nx][ny]) {
                        dist[nx][ny] = nd;
                        pq.emplace(nd, nx, ny);
                    }
                }
            }
        }

        return -1;
    }
};
