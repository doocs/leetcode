class Solution {
public:
    int shortestDistance(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        int m = maze.size();
        int n = maze[0].size();
        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        dist[start[0]][start[1]] = 0;
        queue<vector<int>> q{{start}};
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i, y = j, step = dist[i][j];
                int a = dirs[k], b = dirs[k + 1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                    ++step;
                }
                if (step < dist[x][y]) {
                    dist[x][y] = step;
                    q.push({x, y});
                }
            }
        }
        return dist[destination[0]][destination[1]] == INT_MAX ? -1 : dist[destination[0]][destination[1]];
    }
};