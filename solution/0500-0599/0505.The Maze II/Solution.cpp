class Solution {
public:
    int shortestDistance(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        int m = maze.size(), n = maze[0].size();
        int dist[m][n];
        memset(dist, 0x3f, sizeof(dist));
        int si = start[0], sj = start[1];
        int di = destination[0], dj = destination[1];
        dist[si][sj] = 0;
        queue<pair<int, int>> q;
        q.emplace(si, sj);
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int d = 0; d < 4; ++d) {
                int x = i, y = j, k = dist[i][j];
                int a = dirs[d], b = dirs[d + 1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                    ++k;
                }
                if (k < dist[x][y]) {
                    dist[x][y] = k;
                    q.emplace(x, y);
                }
            }
        }
        return dist[di][dj] == 0x3f3f3f3f ? -1 : dist[di][dj];
    }
};