class Solution {
public:
    vector<vector<int>> grid;
    vector<int> dirs = {-1, 0, 1, 0, -1};

    int getMaximumGold(vector<vector<int>>& grid) {
        this->grid = grid;
        int ans = 0;
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                ans = max(ans, dfs(i, j));
        return ans;
    }

    int dfs(int i, int j) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid[0].size() || grid[i][j] == 0) return 0;
        int t = grid[i][j];
        grid[i][j] = 0;
        int res = 0;
        for (int k = 0; k < 4; ++k) res = max(res, t + dfs(i + dirs[k], j + dirs[k + 1]));
        grid[i][j] = t;
        return res;
    }
};