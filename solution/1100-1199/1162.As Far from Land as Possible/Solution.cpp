class Solution {
public:
    int maxDistance(vector<vector<int>>& grid) {
        int n = grid.size();
        typedef pair<int, int> pii;
        queue<pii> q;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1)
                    q.push({i, j});
        int ans = -1;
        bool valid = false;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                pii p = q.front();
                q.pop();
                for (int i = 0; i < 4; ++i) {
                    int x = p.first + dirs[i];
                    int y = p.second + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        valid = true;
                        grid[x][y] = 1;
                        q.push({x, y});
                    }
                }
            }
        }
        return valid ? ans : -1;
    }
};