class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size();
        int n = grid1[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid2[i][j] == 1 && dfs(i, j, m, n, grid1, grid2))
                    ++ans;
        return ans;
    }

    bool dfs(int i, int j, int m, int n, vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        bool ans = grid1[i][j];
        grid2[i][j] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] && !dfs(x, y, m, n, grid1, grid2))
                ans = false;
        }
        return ans;
    }
};