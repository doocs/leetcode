class Solution {
public:
    vector<vector<int>> maze;
    vector<vector<bool>> vis;
    vector<int> d;
    int m;
    int n;

    bool hasPath(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        m = maze.size();
        n = maze[0].size();
        d = destination;
        vis.resize(m, vector<bool>(n, false));
        this->maze = maze;
        dfs(start[0], start[1]);
        return vis[d[0]][d[1]];
    }

    void dfs(int i, int j) {
        if (vis[i][j]) return;
        vis[i][j] = true;
        if (i == d[0] && j == d[1]) return;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i, y = j;
            int a = dirs[k], b = dirs[k + 1];
            while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                x += a;
                y += b;
            }
            dfs(x, y);
        }
    }
};