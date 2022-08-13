class Solution {
public:
    int countUnguarded(int m, int n, vector<vector<int>>& guards, vector<vector<int>>& walls) {
        vector<vector<char>> g(m, vector<char>(n));
        for (auto& e : guards) g[e[0]][e[1]] = 'g';
        for (auto& e : walls) g[e[0]][e[1]] = 'w';
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& p : guards) {
            for (auto& dir : dirs) {
                int a = dir[0], b = dir[1];
                int x = p[0], y = p[1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && g[x + a][y + b] != 'w' && g[x + a][y + b] != 'g') {
                    x += a;
                    y += b;
                    g[x][y] = 'v';
                }
            }
        }
        int ans = 0;
        for (auto& row : g)
            for (auto& v : row)
                ans += v == 0;
        return ans;
    }
};