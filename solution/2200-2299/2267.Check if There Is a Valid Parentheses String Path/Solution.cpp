bool vis[100][100][200];
int dirs[3] = {1, 0, 1};

class Solution {
public:
    bool hasValidPath(vector<vector<char>>& grid) {
        memset(vis, 0, sizeof(vis));
        return dfs(0, 0, 0, grid);
    }

    bool dfs(int i, int j, int t, vector<vector<char>>& grid) {
        if (vis[i][j][t]) return false;
        vis[i][j][t] = true;
        t += grid[i][j] == '(' ? 1 : -1;
        if (t < 0) return false;
        int m = grid.size(), n = grid[0].size();
        if (i == m - 1 && j == n - 1) return t == 0;
        for (int k = 0; k < 2; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < m && y < n && dfs(x, y, t, grid)) return true;
        }
        return false;
    }
};