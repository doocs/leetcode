class Solution {
public:
    vector<vector<int>> allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        queue<pair<int, int>> q;
        q.emplace(rCenter, cCenter);
        vector<vector<int>> ans;
        bool vis[rows][cols];
        memset(vis, false, sizeof(vis));
        vis[rCenter][cCenter] = true;
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                auto [i, j] = q.front();
                q.pop();
                ans.push_back({i, j});
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k];
                    int y = j + dirs[k + 1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !vis[x][y]) {
                        vis[x][y] = true;
                        q.emplace(x, y);
                    }
                }
            }
        }
        return ans;
    }
};