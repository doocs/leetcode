class Solution {
public:
    vector<vector<int>> allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        queue<vector<int>> q;
        q.push({rCenter, cCenter});
        vector<vector<bool>> vis(rows, vector<bool>(cols));
        vis[rCenter][cCenter] = true;
        vector<vector<int>> ans;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int n = q.size(); n > 0; --n) {
                auto p = q.front();
                q.pop();
                ans.push_back(p);
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !vis[x][y]) {
                        q.push({x, y});
                        vis[x][y] = true;
                    }
                }
            }
        }
        return ans;
    }
};