class Solution {
public:
    int countSubIslands(vector<vector<int>> &grid1, vector<vector<int>> &grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int count = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n))
                {
                    ++count;
                }
            }
        }
        return count;
    }

private:
    vector<vector<int>> directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    bool dfs(vector<vector<int>> &grid1, vector<vector<int>> &grid2, int i, int j, int m, int n) {
        bool res = grid1[i][j] == 1;
        grid2[i][j] = 0;

        for (auto direction : directions)
        {
            int a = i + direction[0], b = j + direction[1];
            if (a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1)
            {
                if (!dfs(grid1, grid2, a, b, m, n))
                {
                    res = false;
                }
            }
        }
        return res;
    }
};