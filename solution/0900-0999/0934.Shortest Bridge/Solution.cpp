class Solution {
public:
    vector<int> dirs = {-1, 0, 1, 0, -1};

    int shortestBridge(vector<vector<int>>& grid) {
        vector<int> start = find(grid);
        queue<vector<int>> q;
        dfs(start[0], start[1], q, grid);
        int ans = -1;
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                auto p = q.front();
                q.pop();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size()) {
                        if (grid[x][y] == 1) return ans;
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.push({x, y});
                        }
                    }
                }
            }
        }
        return 0;
    }

    void dfs(int i, int j, queue<vector<int>>& q, vector<vector<int>>& grid) {
        grid[i][j] = 2;
        q.push({i, j});
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == 1)
                dfs(x, y, q, grid);
        }
    }

    vector<int> find(vector<vector<int>>& grid) {
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                if (grid[i][j] == 1)
                    return {i, j};
        return {0, 0};
    }
};