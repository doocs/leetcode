class Solution {
public:
    int numDistinctIslands(vector<vector<int>>& grid) {
        unordered_set<string> paths;
        string path;
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j] == 1) {
                    path = "";
                    dfs(i, j, 0, grid, path);
                    paths.insert(path);
                }
            }
        }
        return paths.size();
    }

    void dfs(int i, int j, int direction, vector<vector<int>>& grid, string& path) {
        grid[i][j] = 0;
        path += to_string(direction);
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 1; k < 5; ++k) {
            int x = i + dirs[k - 1], y = j + dirs[k];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == 1)
                dfs(x, y, k, grid, path);
        }
        path += to_string(direction);
    }
};