class Solution {
public:
    vector<int> findBall(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> ans(n);
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i == m) {
                return j;
            }
            if (j == 0 && grid[i][j] == -1) {
                return -1;
            }
            if (j == n - 1 && grid[i][j] == 1) {
                return -1;
            }
            if (grid[i][j] == 1 && grid[i][j + 1] == -1) {
                return -1;
            }
            if (grid[i][j] == -1 && grid[i][j - 1] == 1) {
                return -1;
            }
            return grid[i][j] == 1 ? dfs(i + 1, j + 1) : dfs(i + 1, j - 1);

        };
        for (int j = 0; j < n; ++j) {
            ans[j] = dfs(0, j);
        }
        return ans;
    }
};