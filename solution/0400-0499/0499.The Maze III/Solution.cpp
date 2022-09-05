class Solution {
public:
    string findShortestWay(vector<vector<int>>& maze, vector<int>& ball, vector<int>& hole) {
        int m = maze.size();
        int n = maze[0].size();
        int r = ball[0], c = ball[1];
        int rh = hole[0], ch = hole[1];
        queue<pair<int, int>> q;
        q.push({r, c});
        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        dist[r][c] = 0;
        vector<vector<string>> path(m, vector<string>(n, ""));
        vector<vector<int>> dirs = {{-1, 0, 'u'}, {1, 0, 'd'}, {0, -1, 'l'}, {0, 1, 'r'}};
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            int i = p.first, j = p.second;
            for (auto& dir : dirs) {
                int a = dir[0], b = dir[1];
                char d = (char) dir[2];
                int x = i, y = j;
                int step = dist[i][j];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0 && (x != rh || y != ch)) {
                    x += a;
                    y += b;
                    ++step;
                }
                if (dist[x][y] > step || (dist[x][y] == step && (path[i][j] + d < path[x][y]))) {
                    dist[x][y] = step;
                    path[x][y] = path[i][j] + d;
                    if (x != rh || y != ch) q.push({x, y});
                }
            }
        }
        return path[rh][ch] == "" ? "impossible" : path[rh][ch];
    }
};